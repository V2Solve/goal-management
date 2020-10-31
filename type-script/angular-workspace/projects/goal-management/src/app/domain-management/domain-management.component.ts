import { Component, OnInit } from '@angular/core';
import { CreateOrgDomainRequest, DeleteOrgDomainRequest, OrgGoalDomain, SearchOrgDomainRequest } from '../backend-services/backend-data';
import { RequestStatusInformation } from '../shared-services/rest-api-data';
import { BaseComponent } from '../shared-services/base-component';
import {BackEndService} from './../backend-services/backend.service'
import {SelectItem} from './../../../../../node_modules/primeng/api';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { TableColumnInfo } from '../common-table/common-table.component';


@Component({
  selector: 'app-domain-management',
  templateUrl: './domain-management.component.html',
  styleUrls: ['./domain-management.component.scss']
})
export class DomainManagementComponent extends BaseComponent implements OnInit 
{
  listOfDomains: Array<OrgGoalDomain> = new Array();
  listOfSelects: Array<SelectItem> = new Array();

  title = new FormControl ('',Validators.required);
  description = new FormControl('',Validators.required);

  formGroup = new FormGroup ({'title':this.title,'description':this.description});

  selectedParent: string;

  listOfColumns: Array<any> = new Array();

  constructor(private bs: BackEndService) {
    super ();
  }



  rowClicked (eventData: any)
  {
    if (eventData != null)
    {
      let b: OrgGoalDomain = eventData;
      this.title.setValue(b.title);
      this.description.setValue(b.description);
      if (b.parentDomain  != null)
      {
          this.selectedParent = b.parentDomain.title;
      }
      else
      {
          this.selectedParent = null;
      }
    }
  }

  configureTableColumns ()
  {
      this.listOfColumns.length = 0;

      {
        let tblColumnInfo = new TableColumnInfo('id','ID',TableColumnInfo.LINKTYPE_BUTTON,"^");
        tblColumnInfo.width(10);
        tblColumnInfo.searchable = false;
        this.listOfColumns.push(tblColumnInfo);
      }

      {
        let tblColumnInfo = new TableColumnInfo('title','Title');
        tblColumnInfo.width(20);
        this.listOfColumns.push(tblColumnInfo);
      }

      {
        let tblColumnInfo = new TableColumnInfo('description','Description');
        tblColumnInfo.width(50);
        this.listOfColumns.push(tblColumnInfo);
      }

      {
        let tblColumnInfo = new TableColumnInfo('parentDomain.title','Parent');
        tblColumnInfo.width(20);
        this.listOfColumns.push(tblColumnInfo);
      }
  }

  saveRecord ()
  {
     let request = new CreateOrgDomainRequest ();
     let domainInfo = new OrgGoalDomain ();
     request.domainInfo = domainInfo;
     domainInfo.description = this.description.value;
     domainInfo.title = this.title.value;

    console.log("Parent Information: " + JSON.stringify(this.selectedParent));

     if (this.selectedParent != null)
     {
       let parentDomain = new OrgGoalDomain ();
       parentDomain.title = this.selectedParent;
       domainInfo.parentDomain = parentDomain;
       console.log("Put Parent Domain: " + JSON.stringify(parentDomain));
     }
     
    
     this.bs.createOrgDomain(request).then(response=>{

      if (response.status.statusCode == RequestStatusInformation.standardSuccessCode)
      {
          this.setInfoMessage(response.status.statusMessage);
          this.searchRecords ();
      }
      else
      {
        this.addErrorMessage(response.status.statusMessage);
      }

     },pipe=>{
       this.setErrorMessage(JSON.stringify(pipe));
     })
  }

  deleteRecord ()
  {
     let request = new DeleteOrgDomainRequest ();
     request.objectId = this.title.value;
     this.bs.deleteOrgDomain(request).then(response=>{

      if (response.status.statusCode == RequestStatusInformation.standardSuccessCode)
      {
          this.setInfoMessage(response.status.statusMessage);
          this.searchRecords ();
      }
      else
      {
        this.addErrorMessage(response.status.statusMessage);
      }
      
     },pipe=>{
      this.setErrorMessage(JSON.stringify(pipe));
     });
  }

  searchRecords ()
  {
     this.listOfDomains.length = 0;

     let request = new SearchOrgDomainRequest ();
     let domainInfo = new OrgGoalDomain ();
     request.domainInfo = domainInfo;
     
     if (this.description.value != null && this.description.value.length > 0)
     domainInfo.description = this.description.value;
     
     if (this.title.value != null && this.title.value.length > 0)
     domainInfo.title = this.title.value;
     
     let result = this.bs.searchOrgDomain(request);
     
     result.then(value => 
      {
 
       if (value != null && value.status.statusCode == RequestStatusInformation.standardSuccessCode)
       {
           for (let orgDomainInfo of value.domainInfos)
           {
               this.listOfDomains.push(orgDomainInfo);
           }
       }
       else
       {
         this.setErrorMessage(value.status.statusMessage);
       }
 
     },pipe=>{
       this.setErrorMessage(JSON.stringify(pipe));
       console.log(JSON.stringify(pipe))
     });
  }


  readOrgDomains ()
  {
    this.listOfSelects.length=0;
    
    let rootSI = new SelectRecord("Select Parent","");
    this.listOfSelects.push(rootSI);

    let req = new SearchOrgDomainRequest ();
    let result = this.bs.searchOrgDomain(req);

    result.then(value => 
      {

      if (value != null && value.status.statusCode == RequestStatusInformation.standardSuccessCode)
      {
          for (let orgDomainInfo of value.domainInfos)
          {
              let si = new SelectRecord(orgDomainInfo.title,orgDomainInfo.title);
              console.log("Pushed: " + JSON.stringify(orgDomainInfo));
              this.listOfSelects.push(si);
          }
      }
      else
      {
        this.setErrorMessage(value.status.statusMessage);
      }

    },pipe=>{
      this.setErrorMessage(JSON.stringify(pipe));
      console.log(JSON.stringify(pipe))
    });
  }

  ngOnInit(): void {
    this.configureTableColumns ();
    this.readOrgDomains();
    this.searchRecords ();
  }

}

interface SelectedItem {
  name: string;
  code: string;
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