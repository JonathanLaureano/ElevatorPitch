import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { ElevatorPitch, OutlineResponse, UserResponse } from '../../services/elevator-pitch';

@Component({
  selector: 'app-result',
  imports: [CommonModule],
  templateUrl: './result.html',
  styleUrl: './result.css'
})
export class ResultComponent implements OnInit {
  sessionId: string = '';
  outline: OutlineResponse | null = null;
  responses: UserResponse[] = [];
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
      this.loadResults();
    });
  }

  loadResults(): void {
    this.loading = true;
    
    // Load outline and responses
    Promise.all([
      this.elevatorPitchService.generateOutline(this.sessionId).toPromise(),
      this.elevatorPitchService.getSessionResponses(this.sessionId).toPromise()
    ]).then(([outline, responses]) => {
      this.outline = outline!;
      this.responses = responses!;
      this.loading = false;
    }).catch(error => {
      this.error = 'Failed to load results';
      this.loading = false;
      console.error('Error loading results:', error);
    });
  }

  startOver(): void {
    this.router.navigate(['/']);
  }

  copyToClipboard(): void {
    if (this.outline?.outline) {
      navigator.clipboard.writeText(this.outline.outline).then(() => {
        alert('Outline copied to clipboard!');
      });
    }
  }
}
