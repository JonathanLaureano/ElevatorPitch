import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-allen-bradley-elevator',
  imports: [CommonModule],
  templateUrl: './allen-bradley-elevator.html',
  styleUrl: './allen-bradley-elevator.css'
})
export class AllenBradleyElevatorComponent {
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
    // Return to the next question with the unique link
    // Assuming this comes after question 3 and goes to question 4
    this.router.navigate(['/question', 4], { queryParams: { uniqueLink: this.uniqueLink } });
  }

  requestMoreHelp(): void {
    this.needsHelp = true;
    // In a real application, this would flag the user for manual assistance
    // For now, we'll just show a message and let them continue
  }
}