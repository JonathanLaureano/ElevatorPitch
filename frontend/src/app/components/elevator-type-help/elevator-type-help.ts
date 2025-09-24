import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-elevator-type-help',
  imports: [CommonModule],
  templateUrl: './elevator-type-help.html',
  styleUrl: './elevator-type-help.css'
})
export class ElevatorTypeHelpComponent {
  uniqueLink: string = '';
  needsHelp: boolean = false;

  constructor(
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.route.queryParams.subscribe(params => {
      this.uniqueLink = params['uniqueLink'] || '';
    });
  }

  acknowledgeHelp(): void {
    // Return to question 3 with the unique link
    this.router.navigate(['/question', 3], { queryParams: { uniqueLink: this.uniqueLink } });
  }

  requestMoreHelp(): void {
    this.needsHelp = true;
    // In a real application, this would flag the user for manual assistance
    // For now, we'll just show a message and let them continue
  }
}