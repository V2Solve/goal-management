import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DomainManagementComponent } from './domain-management/domain-management.component'
import { GoalDefinitionManagementComponent } from './goal-definition-management/goal-definition-management.component';

const routes: Routes = [
  {path: 'domain-management',component: DomainManagementComponent},
  {path: 'goal-definitions',component: GoalDefinitionManagementComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }