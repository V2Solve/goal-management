import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DomainManagementComponent } from './domain-management/domain-management.component'

const routes: Routes = [
  {path: 'domain-management',component: DomainManagementComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }