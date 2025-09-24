import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { ElevatorAssistanceService, TroubleshootingResponse, Answer, User } from '../../services/elevator-assistance';

@Component({
  selector: 'app-result',
  imports: [CommonModule],
  templateUrl: './result.html',
  styleUrl: './result.css'
})
export class ResultComponent implements OnInit {
  uniqueLink: string = '';
  user: User | null = null;
  answers: Answer[] = [];
  loading: boolean = true;
  error: string = '';

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
      this.loadResults();
    });
  }

  loadResults(): void {
    this.loading = true;
    
    // Load user info and answers
    Promise.all([
      this.elevatorAssistanceService.getUserByUniqueLink(this.uniqueLink).toPromise(),
      this.elevatorAssistanceService.getUserAnswers(this.uniqueLink).toPromise()
    ]).then(([user, answers]) => {
      this.user = user!;
      this.answers = answers!;
      this.loading = false;
    }).catch(error => {
      this.error = 'Failed to load troubleshooting results';
      this.loading = false;
      console.error('Error loading results:', error);
    });
  }

  startOver(): void {
    this.router.navigate(['/']);
  }

  getAnswerSummary(): string {
    if (this.answers.length === 0) return 'No troubleshooting responses recorded.';
    
    let summary = 'Troubleshooting Summary:\n\n';
    this.answers.forEach(answer => {
      summary += `Q${answer.question.order}: ${answer.question.text}\n`;
      summary += `Answer: ${answer.selectedOption}\n\n`;
    });
    
    return summary;
  }

  copyToClipboard(): void {
    const summary = this.getAnswerSummary();
    navigator.clipboard.writeText(summary).then(() => {
      alert('Summary copied to clipboard!');
    });
  }
}
