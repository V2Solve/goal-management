import { Component, Input, OnInit } from '@angular/core';
import {PageMessage} from '../shared-services/base-component'

@Component({
  selector: 'app-page-message-box',
  templateUrl: './page-message-box.component.html',
  styleUrls: ['./page-message-box.component.scss']
})
export class PageMessageBoxComponent implements OnInit 
{
  @Input()
  errorMsgs: Array<PageMessage> = new Array();

  @Input ()
  infoMsgs: Array<PageMessage> = new Array();

  constructor() { 
  }

  hasErrorMessages(): boolean
  {
    return (this.errorMsgs != null && this.errorMsgs.length > 0)
  }

  hasInfoMessages(): boolean
  {
    return (this.infoMsgs != null && this.infoMsgs.length > 0)
  }


  hasMessages (): boolean
  {
    return (this.hasInfoMessages () || this.hasErrorMessages());
  }

  clearErrorMessages (): void
  {
    if (this.errorMsgs != null)
    this.errorMsgs.length = 0;

  }

  clearInfoMessages (): void
  {
    if (this.infoMsgs != null)
    this.infoMsgs.length =0;
  }

  clearAllMessages ()
  {
    this.clearErrorMessages ();
    this.clearInfoMessages ();
  }

  ngOnInit(): void {
  }

}
