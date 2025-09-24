import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Question {
  id: number;
  text: string;
  options: string[];
  order: number;
  answerMappings: { [key: string]: string };
}

export interface User {
  id: number;
  name: string;
  uniqueLink: string;
  phoneNumber: string;
  isTicketOpen: boolean;
  createdAt: string;
  updatedAt: string;
}

export interface UserRegistrationRequest {
  name: string;
  phoneNumber: string;
}

export interface AnswerRequest {
  uniqueLink: string;
  questionId: number;
  selectedOption: string;
}

export interface Answer {
  id: number;
  user: User;
  question: Question;
  selectedOption: string;
  createdAt: string;
}

export interface NextPageResponse {
  nextPage: string;
  message?: string;
}

export interface AdminTicketView {
  userId: number;
  name: string;
  uniqueLink: string;
  isTicketOpen: boolean;
  createdAt: string;
  updatedAt: string;
}

export interface TroubleshootingResponse {
  uniqueLink: string;
  summary: string;
}

@Injectable({
  providedIn: 'root'
})
export class ElevatorAssistanceService {
  private baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) { }

  getAllQuestions(): Observable<Question[]> {
    return this.http.get<Question[]>(`${this.baseUrl}/questions`);
  }

  getQuestionById(id: number): Observable<Question> {
    return this.http.get<Question>(`${this.baseUrl}/questions/${id}`);
  }

  registerUser(request: UserRegistrationRequest): Observable<User> {
    return this.http.post<User>(`${this.baseUrl}/users/register`, request);
  }

  submitAnswer(answerRequest: AnswerRequest): Observable<NextPageResponse> {
    return this.http.post<NextPageResponse>(`${this.baseUrl}/answers`, answerRequest);
  }

  getUserByUniqueLink(uniqueLink: string): Observable<User> {
    return this.http.get<User>(`${this.baseUrl}/users/${uniqueLink}`);
  }

  getUserAnswers(uniqueLink: string): Observable<Answer[]> {
    return this.http.get<Answer[]>(`${this.baseUrl}/users/${uniqueLink}/answers`);
  }

  getAllTickets(): Observable<AdminTicketView[]> {
    return this.http.get<AdminTicketView[]>(`${this.baseUrl}/admin/tickets`);
  }

  generateUniqueLink(): string {
    return 'elevator-' + Math.random().toString(36).substring(2, 11) + '-' + Date.now();
  }
}
