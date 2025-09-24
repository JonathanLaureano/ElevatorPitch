import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { ElevatorPitch, Question, AnswerRequest } from '../../services/elevator-pitch';

@Component({
  selector: 'app-question',
  imports: [CommonModule],
  templateUrl: './question.html',
  styleUrl: './question.css'
})
export class QuestionComponent implements OnInit {
  questions: Question[] = [];
  currentQuestion: Question | null = null;
  currentQuestionIndex: number = 0;
  sessionId: string = '';
  selectedOption: string = '';
  loading: boolean = true;
  error: string = '';

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private elevatorPitchService: ElevatorPitch
  ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.sessionId = params['sessionId'];
      if (!this.sessionId) {
        this.router.navigate(['/']);
        return;
      }
    });

    this.route.params.subscribe(params => {
      const questionNumber = +params['questionNumber'];
      this.loadQuestions(questionNumber);
    });
  }

  loadQuestions(questionNumber: number): void {
    this.loading = true;
    this.elevatorPitchService.getAllQuestions().subscribe({
      next: (questions) => {
        this.questions = questions;
        this.currentQuestionIndex = questionNumber - 1;
        if (this.currentQuestionIndex >= 0 && this.currentQuestionIndex < this.questions.length) {
          this.currentQuestion = this.questions[this.currentQuestionIndex];
        } else {
          this.error = 'Question not found';
        }
        this.loading = false;
      },
      error: (error) => {
        this.error = 'Failed to load questions';
        this.loading = false;
        console.error('Error loading questions:', error);
      }
    });
  }

  selectOption(option: string): void {
    this.selectedOption = option;
  }

  submitAnswer(): void {
    if (!this.selectedOption || !this.currentQuestion) {
      return;
    }

    const answerRequest: AnswerRequest = {
      sessionId: this.sessionId,
      questionId: this.currentQuestion.id,
      selectedOption: this.selectedOption
    };

    this.elevatorPitchService.submitAnswer(answerRequest).subscribe({
      next: (response) => {
        this.navigateToNextQuestion();
      },
      error: (error) => {
        this.error = 'Failed to submit answer';
        console.error('Error submitting answer:', error);
      }
    });
  }

  navigateToNextQuestion(): void {
    const nextQuestionIndex = this.currentQuestionIndex + 1;
    if (nextQuestionIndex < this.questions.length) {
      // Navigate to next question
      this.router.navigate(['/question', nextQuestionIndex + 1], 
        { queryParams: { sessionId: this.sessionId } });
    } else {
      // Navigate to results page
      this.router.navigate(['/result'], 
        { queryParams: { sessionId: this.sessionId } });
    }
  }

  getProgressPercentage(): number {
    if (this.questions.length === 0) return 0;
    return ((this.currentQuestionIndex + 1) / this.questions.length) * 100;
  }
}
