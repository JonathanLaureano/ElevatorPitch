import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ElevatorAssistanceService, UserRegistrationRequest } from '../../services/elevator-assistance';

@Component({
  selector: 'app-user-registration',
  imports: [CommonModule, FormsModule],
  templateUrl: './disclaimer.html',
  styleUrl: './disclaimer.css'
})
export class Disclaimer {
  name: string = '';
  phoneNumber: string = '';
  isLoading: boolean = false;
  error: string = '';

  constructor(
    private router: Router, 
    private elevatorAssistanceService: ElevatorAssistanceService
  ) {}

  submitRegistration(): void {
    if (!this.name.trim()) {
      this.error = 'Please enter your name';
      return;
    }

    this.isLoading = true;
    this.error = '';

    const request: UserRegistrationRequest = {
      name: this.name.trim(),
      phoneNumber: this.phoneNumber.trim()
    };

    this.elevatorAssistanceService.registerUser(request).subscribe({
      next: (user) => {
        // Navigate to first question with unique link
        this.router.navigate(['/question', 1], { queryParams: { uniqueLink: user.uniqueLink } });
      },
      error: (error) => {
        console.error('Registration failed:', error);
        this.error = 'Registration failed. Please try again.';
        this.isLoading = false;
      }
    });
  }
}
