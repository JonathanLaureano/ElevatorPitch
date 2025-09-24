import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { ElevatorAssistanceService, AdminTicketView, Answer, User } from '../../services/elevator-assistance';

@Component({
  selector: 'app-admin-dashboard',
  imports: [CommonModule],
  templateUrl: './admin-dashboard.html',
  styleUrl: './admin-dashboard.css'
})
export class AdminDashboardComponent implements OnInit {
  tickets: AdminTicketView[] = [];
  selectedTicket: AdminTicketView | null = null;
  selectedTicketAnswers: Answer[] = [];
  selectedTicketUser: User | null = null;
  isLoading: boolean = true;
  error: string = '';

  constructor(
    private elevatorAssistanceService: ElevatorAssistanceService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadTickets();
  }

  loadTickets(): void {
    this.isLoading = true;
    this.elevatorAssistanceService.getAllTickets().subscribe({
      next: (tickets) => {
        this.tickets = tickets;
        this.isLoading = false;
      },
      error: (error) => {
        console.error('Failed to load tickets:', error);
        this.error = 'Failed to load tickets';
        this.isLoading = false;
      }
    });
  }

  selectTicket(ticket: AdminTicketView): void {
    this.selectedTicket = ticket;
    this.loadTicketDetails(ticket.uniqueLink);
  }

  loadTicketDetails(uniqueLink: string): void {
    // Load user details and answers
    this.elevatorAssistanceService.getUserByUniqueLink(uniqueLink).subscribe({
      next: (user) => {
        this.selectedTicketUser = user;
      },
      error: (error) => {
        console.error('Failed to load user details:', error);
      }
    });

    this.elevatorAssistanceService.getUserAnswers(uniqueLink).subscribe({
      next: (answers) => {
        this.selectedTicketAnswers = answers;
      },
      error: (error) => {
        console.error('Failed to load answers:', error);
      }
    });
  }

  closeDetailsPanel(): void {
    this.selectedTicket = null;
    this.selectedTicketAnswers = [];
    this.selectedTicketUser = null;
  }

  getStatusClass(isOpen: boolean): string {
    return isOpen ? 'status-open' : 'status-closed';
  }

  getStatusText(isOpen: boolean): string {
    return isOpen ? 'Open' : 'Closed';
  }

  formatDate(dateString: string): string {
    return new Date(dateString).toLocaleDateString() + ' ' + new Date(dateString).toLocaleTimeString();
  }
}