import { Routes } from '@angular/router';
import { Disclaimer } from './components/disclaimer/disclaimer';
import { QuestionComponent } from './components/question/question';
import { ResultComponent } from './components/result/result';
import { SendTechComponent } from './components/send-tech/send-tech';
import { ElevatorTypeHelpComponent } from './components/elevator-type-help/elevator-type-help';
import { AllenBradleyElevatorComponent } from './components/allen-bradley-elevator/allen-bradley-elevator';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard';

export const routes: Routes = [
  { path: '', component: Disclaimer },
  { path: 'question/:questionNumber', component: QuestionComponent },
  { path: 'send-tech', component: SendTechComponent },
  { path: 'elevator-type-help', component: ElevatorTypeHelpComponent },
  { path: 'allen-bradley-elevator', component: AllenBradleyElevatorComponent },
  { path: 'admin', component: AdminDashboardComponent },
  { path: 'result', component: ResultComponent },
  { path: '**', redirectTo: '' }
];
