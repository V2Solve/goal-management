import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import {processLinkTokens,getProperty} from '../shared-services/common-functions'
import {Table} from 'primeng/table'

@Component({
  selector: 'app-common-table',
  templateUrl: './common-table.component.html',
  styleUrls: ['./common-table.component.scss']
})
export class CommonTableComponent implements OnInit 
{
  selectedColumns: string[] = new Array();

  @Input()
  tableColumns: Array<TableColumnInfo>;


  @Input ()
  tableData: Array<any>;

  @Input ()
  tableTitle: string = "Table Data";

  @ViewChild('dt') table: Table;

  @Input ()
  maxDisplayLength: number = -1;
  
  @Input ()
  globalFilterFields: Array<string>=new Array();

  @Input ()
  globalSearchEnabled: boolean = true;

  @Output() 
  rowClick = new EventEmitter<any>();

  constructor() { 
  }



  setGlobalFilterFields ()
  {
     if (this.globalFilterFields.length <= 0)
     {
        if (this.tableColumns != null && this.tableColumns.length > 0)
        {
          for (let col of this.tableColumns)
          {
            this.globalFilterFields.push(col.field);
          }
        }
    }
  }

  setAppropriateColumnWidths ()
  {
      let totalColums = this.tableColumns.length;

      let avgWidthPercent = 10;

      if (totalColums > 0)
      {
          avgWidthPercent = Math.round(100/totalColums);
          
          // Now lets provide the avg width to all that are not set, and to others that are set...
          for (let tblcolumn of this.tableColumns)
          {
              if (tblcolumn.wdt <= 0)
              tblcolumn.wdt = avgWidthPercent;
          }
      }
  }

  ngOnInit(): void 
  {
     this.setGlobalFilterFields ();
     this.selectedColumns.length = 0;
     
     for (let tableColumn of this.tableColumns)
     {
        this.selectedColumns.push(tableColumn.header)
     }
  }

  toggle(name:string){

    for(let col of this.selectedColumns){
      if(name==col)
      console.log("Selected Column: $s",name);
     
      return false;
    }
    console.log("Unselected Column: $s",this.selectedColumns );
    return true;
  }


  getFieldProperty(data: any,prop: string)
  {
      return getProperty(prop,data);
  }

  truncateToMaxDisplayLength (value: any,hdrInfo?: TableColumnInfo): string
  {
      if (value == null)
      {
        return "";
      }

      let disp = "";

      if (value instanceof Date)
      {
        let dt: Date = value;
        disp = dt.toDateString ();
      }
      else
      disp = ""+value;

      let maxLength = this.maxDisplayLength;

      if (hdrInfo != null && hdrInfo.maxTextLength > 0)
      maxLength = hdrInfo.maxTextLength;

      if (maxLength > 0)
      {
        if (disp.length > maxLength)
        {
            disp = disp.substr(0,maxLength) + "...";
        }
      }

      return disp;
  }
//check if column is selected
  isColumnSelected(name:string){
   
  
  for(let colName of this.selectedColumns){
      if(name==colName)
      return true;
    }
    
    return false; 
  }
}



/**
 * This class is to provide information about Columns...
 */
export class TableColumnInfo
{
  public static LINKTYPE_BUTTON: string = "button_link";
  public static LINKTYPE_NORMAL: string = "normal_link";
  public static LINKTYPE_ROUTER: string = "router_link";
  
  field!: string;
  header!: string;
  
  linkType: string = null;
  buttonText: string = "#"
  linkText: string = null;
  routerLinkQueryParams: string;
  routerLinkStateData: any;
  searchable: boolean = true;

  /**
   * Width in %
   */
  wdt: number = -1;

  maxTextLength: number = -1;

  wrp: boolean = false;

  /**
   * 
   * @param field 
   * @param header 
   * @param linkType 
   * @param buttonText 
   * @param routerLinkStateData - some object which when will be passed as state..
   */
  constructor(field: string,header: string,linkType?: string,buttonText?: string,routerLinkQueryParams?: string,routerLinkStateData?: any)
  {
    this.field = field;
    this.header = header;

    if (linkType != undefined && linkType != null)
    {
      this.linkType = linkType;
    }

    if (buttonText != null && buttonText != undefined)
    {
        this.buttonText = buttonText;
    }

    if (routerLinkQueryParams != null && routerLinkQueryParams != undefined)
    this.routerLinkQueryParams = routerLinkQueryParams;

    if (routerLinkStateData != null && routerLinkStateData != undefined)
    this.routerLinkStateData = routerLinkStateData;

  }

  width (wdt: number): TableColumnInfo
  {
      this.wdt = wdt;
      return this;
  }
 
  wrap (wrp: boolean)
  {
      this.wrp = wrp;
  }


  isRouterLink (): boolean
  {
    return (this.linkType == TableColumnInfo.LINKTYPE_ROUTER);
  }

  isButtonLink (): boolean
  {
    return (this.linkType == TableColumnInfo.LINKTYPE_BUTTON);
  }  

  isNormalLink (): boolean
  {
    return (this.linkType == TableColumnInfo.LINKTYPE_NORMAL);
  }
 
  getLinkText (asset: any): string
  {
      let textStr = null;
      
      if (this.isRouterLink() || this.isNormalLink())
      {
          textStr = processLinkTokens(asset,this.linkText);
      }

      return textStr;
  }

  getRouterLinkStateData (asset: any): string
  {
      let textToReturn = "{}";
      if (this.routerLinkStateData != null)
      textToReturn = JSON.stringify(this.routerLinkStateData);
      textToReturn = processLinkTokens(asset,textToReturn);
      // console.log("StateData being returned: " + textToReturn);
      return textToReturn;
  }

  getRouterLinkQueryParams (asset: any): any
  {
    let textToReturn = "{}";
    if (this.routerLinkQueryParams != null)
      textToReturn = processLinkTokens(asset,this.routerLinkQueryParams);
    console.log(" Text returned: " + textToReturn);
    return JSON.parse(textToReturn);
  }

  isNormalText ()
  {
     return (this.linkType == null || this.linkType == undefined);
  }

}