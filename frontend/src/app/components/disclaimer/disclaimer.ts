import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ElevatorPitch } from '../../services/elevator-pitch';

@Component({
  selector: 'app-disclaimer',
  imports: [CommonModule],
  templateUrl: './disclaimer.html',
  styleUrl: './disclaimer.css'
})
export class Disclaimer {
  sessionId: string = '';

  constructor(
    private router: Router, 
    private elevatorPitchService: ElevatorPitch
  ) {
    this.sessionId = this.elevatorPitchService.generateSessionId();
  }

  acceptTerms(): void {
    // Navigate to first question with sessionId
    this.router.navigate(['/question', 1], { queryParams: { sessionId: this.sessionId } });
  }
}
