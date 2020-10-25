import { Component, OnInit } from '@angular/core';
import { CreateOrgDomainRequest, DeleteOrgDomainRequest, OrgGoalDomain, SearchOrgDomainRequest } from '../backend-services/backend-data';
import { RequestStatusInformation } from '../shared-services/rest-api-data';
import { BaseComponent } from '../shared-services/base-component';
import {BackEndService} from './../backend-services/backend.service'
import {SelectItem} from './../../../../../node_modules/primeng/api';
import { FormControl, FormGroup, Validators } from '@angular/forms';


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

  selectedParent: SelectedItem;

  constructor(private bs: BackEndService) {
    super ();
  }


  saveRecord ()
  {
     let request = new CreateOrgDomainRequest ();
     let domainInfo = new OrgGoalDomain ();
     request.domainInfo = domainInfo;
     domainInfo.description = this.description.value;
     domainInfo.title = this.title.value;

     if (this.selectedParent != null && this.selectedParent.code != null)
     {
       let parentDomain = new OrgGoalDomain ();
       parentDomain.title = this.selectedParent.code;
       domainInfo.parentDomain = parentDomain;
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
     let request = new SearchOrgDomainRequest ();
     let domainInfo = new OrgGoalDomain ();
     request.domainInfo = domainInfo;
     domainInfo.description = this.description.value;
     domainInfo.title = this.title.value;
     let result = this.bs.searchOrgDomain(request);
     result.then(value => {
 
       if (value != null && value.status.statusCode == RequestStatusInformation.standardSuccessCode)
       {
           this.listOfDomains.length = 0;
           this.listOfSelects.length=0;
           
           let rootSI = {label:'Select Parent', value:null};
           this.listOfSelects.push(rootSI);
           
           for (let orgDomainInfo of value.domainInfos)
           {
               this.listOfDomains.push(orgDomainInfo);
               let si = {label: orgDomainInfo.title, value: orgDomainInfo.title};
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


  readOrgDomains ()
  {
    let req = new SearchOrgDomainRequest ();
    
    let result = this.bs.searchOrgDomain(req);
    
    result.then(value => {

      if (value != null && value.status.statusCode == RequestStatusInformation.standardSuccessCode)
      {
          this.listOfDomains.length = 0;
          this.listOfSelects.length=0;
          
          let rootSI = {label:'Select Parent', value:null};
          this.listOfSelects.push(rootSI);
          
          for (let orgDomainInfo of value.domainInfos)
          {
              this.listOfDomains.push(orgDomainInfo);
              let si = {label: orgDomainInfo.title, value: orgDomainInfo.title};
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
    this.readOrgDomains();
    this.searchRecords ();
  }

}

interface SelectedItem {
  name: string;
  code: string;
}
