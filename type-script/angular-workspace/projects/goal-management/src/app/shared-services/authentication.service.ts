import { Injectable } from '@angular/core';
import { GlobalVariablesService } from './global-variables.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService 
{
  constructor(private gvs: GlobalVariablesService) { 
  }

  isLoggedIn () : boolean
  {
    if (this.gvs.getValue("loggedIn") == "true")
    {
      return true;
    }
    else
    return false;
  }

  getDisplayName (): string
  {
     if (this.isLoggedIn () == true)
          return "Saurin";
          else 
          return "Guest";
  }

  getAuthorizationHeader (): string
  {
     return "Bearer yetToBeObtained";
  }  


  login ()
  {
    this.gvs.saveValue("loggedIn","true");
  }
 
  logout ()
  {
    this.gvs.removeValue("loggedIn");
  }

}