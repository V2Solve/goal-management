import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class GlobalVariablesService {

  constructor() {

  }

  saveValue (key: string, value: string)
  {
      window.sessionStorage.setItem(key,value);
  }

  getValue (key: string): string
  {
     return window.sessionStorage.getItem(key);
  }

  clearAll ()
  {
      window.sessionStorage.clear();
  }

  removeValue (key: string)
  {
      window.sessionStorage.removeItem(key);
  }

}