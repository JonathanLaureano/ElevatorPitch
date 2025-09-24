import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Question {
  id: number;
  text: string;
  options: string[];
  order: number;
}

export interface AnswerRequest {
  sessionId: string;
  questionId: number;
  selectedOption: string;
}

export interface UserResponse {
  id: number;
  sessionId: string;
  question: Question;
  selectedOption: string;
  createdAt: string;
}

export interface OutlineResponse {
  sessionId: string;
  outline: string;
}

@Injectable({
  providedIn: 'root'
})
export class ElevatorPitch {
  private baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) { }

  getAllQuestions(): Observable<Question[]> {
    return this.http.get<Question[]>(`${this.baseUrl}/questions`);
  }

  getQuestionById(id: number): Observable<Question> {
    return this.http.get<Question>(`${this.baseUrl}/questions/${id}`);
  }

  submitAnswer(answerRequest: AnswerRequest): Observable<UserResponse> {
    return this.http.post<UserResponse>(`${this.baseUrl}/answers`, answerRequest);
  }

  generateOutline(sessionId: string): Observable<OutlineResponse> {
    return this.http.get<OutlineResponse>(`${this.baseUrl}/outline/${sessionId}`);
  }

  getSessionResponses(sessionId: string): Observable<UserResponse[]> {
    return this.http.get<UserResponse[]>(`${this.baseUrl}/responses/${sessionId}`);
  }

  generateSessionId(): string {
    return 'session-' + Math.random().toString(36).substr(2, 9) + '-' + Date.now();
  }
}
