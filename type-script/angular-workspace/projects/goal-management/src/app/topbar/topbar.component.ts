import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../shared-services/authentication.service';

@Component({
  selector: 'app-topbar',
  templateUrl: './topbar.component.html',
  styleUrls: ['./topbar.component.scss']
})
export class TopbarComponent implements OnInit 
{
  constructor(private authService: AuthenticationService) 
  { 

  }

  getUserName (): string
  {
      return this.authService.getDisplayName ();
  }

  login ()
  {
    this.authService.login ();
  }

  logout ()
  {
    this.authService.logout ();
  }

  isLoggedIn (): boolean
  {
      return this.authService.isLoggedIn ();
  }

  ngOnInit(): void {

  }
}