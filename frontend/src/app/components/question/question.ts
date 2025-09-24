import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { ElevatorAssistanceService, Question, AnswerRequest, NextPageResponse } from '../../services/elevator-assistance';

@Component({
  selector: 'app-question',
  imports: [CommonModule],
  templateUrl: './question.html',
  styleUrl: './question.css'
})
export class QuestionComponent implements OnInit {
  currentQuestion: Question | null = null;
  uniqueLink: string = '';
  selectedOption: string = '';
  loading: boolean = true;
  submitting: boolean = false;
  error: string = '';
  showPopup: boolean = false;
  popupMessage: string = '';

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private elevatorAssistanceService: ElevatorAssistanceService
  ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.uniqueLink = params['uniqueLink'];
      if (!this.uniqueLink) {
        this.router.navigate(['/']);
        return;
      }
    });

    this.route.params.subscribe(params => {
      const questionId = +params['questionNumber'];
      this.loadQuestion(questionId);
    });
  }

  loadQuestion(questionId: number): void {
    this.loading = true;
    this.error = '';
    
    this.elevatorAssistanceService.getQuestionById(questionId).subscribe({
      next: (question) => {
        this.currentQuestion = question;
        this.loading = false;
        this.selectedOption = ''; // Reset selection
      },
      error: (error) => {
        this.error = 'Failed to load question';
        this.loading = false;
        console.error('Error loading question:', error);
      }
    });
  }

  selectOption(option: string): void {
    this.selectedOption = option;
  }

  submitAnswer(): void {
    if (!this.selectedOption || !this.currentQuestion || this.submitting) {
      return;
    }

    this.submitting = true;
    this.error = '';

    const answerRequest: AnswerRequest = {
      uniqueLink: this.uniqueLink,
      questionId: this.currentQuestion.id,
      selectedOption: this.selectedOption
    };

    this.elevatorAssistanceService.submitAnswer(answerRequest).subscribe({
      next: (response: NextPageResponse) => {
        this.submitting = false;
        this.handleNextPageResponse(response);
      },
      error: (error) => {
        this.error = 'Failed to submit answer';
        this.submitting = false;
        console.error('Error submitting answer:', error);
      }
    });
  }

  handleNextPageResponse(response: NextPageResponse): void {
    if (response.message) {
      // Show popup for messages like "return to site when home"
      this.popupMessage = response.message;
      this.showPopup = true;
      return;
    }

    // Navigate based on next page
    if (response.nextPage === 'home') {
      this.router.navigate(['/']);
    } else if (response.nextPage === 'send-tech') {
      this.router.navigate(['/send-tech']);
    } else if (response.nextPage === 'elevator-type-help') {
      this.router.navigate(['/elevator-type-help'], 
        { queryParams: { uniqueLink: this.uniqueLink } });
    } else if (response.nextPage.startsWith('question/')) {
      const questionId = response.nextPage.split('/')[1];
      this.router.navigate(['/question', questionId], 
        { queryParams: { uniqueLink: this.uniqueLink } });
    } else {
      // Default fallback
      this.error = 'Unexpected navigation response';
    }
  }

  closePopup(): void {
    this.showPopup = false;
    this.router.navigate(['/']); // Return to home
  }

  isOptionSelected(option: string): boolean {
    return this.selectedOption === option;
  }
}
