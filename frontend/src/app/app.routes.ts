import { Routes } from '@angular/router';
import { Disclaimer } from './components/disclaimer/disclaimer';
import { QuestionComponent } from './components/question/question';
import { ResultComponent } from './components/result/result';

export const routes: Routes = [
  { path: '', component: Disclaimer },
  { path: 'question/:questionNumber', component: QuestionComponent },
  { path: 'result', component: ResultComponent },
  { path: '**', redirectTo: '' }
];
