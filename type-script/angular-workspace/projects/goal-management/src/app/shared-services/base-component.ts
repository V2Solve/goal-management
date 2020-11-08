import { Message, SelectItem } from 'primeng/api';
import {TreeNode} from 'primeng/api';

/**
 * Some base functions that are required by all components in angular
 */
export class BaseComponent
{
    infoMsgs:  PageMessage [] = new Array<PageMessage>();
    errorMsgs: PageMessage [] = new Array<PageMessage>();

    setErrorMessage (errMsg: string)
    {
        this.errorMsgs.length = 0;
        let msg = new PageMessage("error",errMsg,errMsg);
        this.errorMsgs.push(msg);
    }

    setInfoMessage (infoMsg: string)
    {
        this.infoMsgs.length = 0;
        let msg = new PageMessage("info",infoMsg,infoMsg);
        this.infoMsgs.push(msg);
    }

    addErrorMessage (errMsg: string)
    {
        let msg = new PageMessage("error",errMsg,errMsg);
        this.errorMsgs.push(msg);
    }

    addInfoMessage (infoMsg: string)
    {
        let msg = new PageMessage("info",infoMsg,infoMsg);
        this.infoMsgs.push(msg);
    }
}


export class PageMessage implements Message
{
    severity: string;
    summary: string;
    detail: string;
    id?: any;
    key?: string;
    life?: number;
    sticky?: boolean;
    closable?: boolean;
    data?: any;
    
    constructor (sev: string,summary: string, detail: string)
    {
        this.severity = sev;
        this.summary = summary;
        this.detail = detail;
    }
}


export class SelectRecord implements SelectItem
{
  label?: string;
  value: string;
  styleClass?: string;
  icon?: string;
  title?: string;
  disabled?: boolean;  
  
  constructor(label: string,value: string)
  {
    this.label = label;
    this.value = value;
  }
}


export class TreeNodeData implements TreeNode {
    data?: any;
    children?: TreeNodeData[];
    leaf?: boolean;
    expanded?: boolean;
}