import { Component, OnInit } from '@angular/core';
import { CreateOrgDomainRequest, CreateOrgGoalDefinitionRequest, DeleteOrgDomainRequest, DeleteOrgGoalDefinitionRequest, OrgGoalDefinition, OrgGoalDomain, SearchOrgDomainRequest, SearchOrgGoalDefinitionRequest } from '../backend-services/backend-data';
import { RequestStatusInformation } from '../shared-services/rest-api-data';
import { BaseComponent, SelectRecord } from '../shared-services/base-component';
import {BackEndService} from './../backend-services/backend.service'
import {SelectItem} from './../../../../../node_modules/primeng/api';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { TableColumnInfo } from '../common-table/common-table.component';

@Component({
  selector: 'app-goal-definition-management',
  templateUrl: './goal-definition-management.component.html',
  styleUrls: ['./goal-definition-management.component.scss']
})
export class GoalDefinitionManagementComponent extends BaseComponent implements OnInit 
{
  listOfDomains: Array<SelectItem> = new Array();
  listOfDefinitions: Array<OrgGoalDefinition> = new Array();
  listOfSelects: Array<SelectItem> = new Array();

  title = new FormControl ('',Validators.required);
  description = new FormControl('',Validators.required);
  orgDomain = new FormControl('',Validators.required);
  goalWeight = new FormControl('',[Validators.required,Validators.pattern("0-9")])

  formGroup = new FormGroup ({'title':this.title,'description':this.description,'goalWeight':this.goalWeight,'orgDomain':this.orgDomain});

  selectedParent: string;
  

  listOfColumns: Array<any> = new Array();

  constructor(private bs: BackEndService) {
    super ();
  }
  
  rowClicked (eventData: any)
  {
    if (eventData != null)
    {
      let b: OrgGoalDefinition = eventData;
      this.title.setValue(b.title);
      this.description.setValue(b.description);
      this.goalWeight.setValue(b.goalWeight);
      
      if (b.parentGoal  != null)
      {
          this.selectedParent = b.parentGoal.title;
      }
      else
      {
          this.selectedParent = null;
      }

      this.orgDomain.setValue(b.orgGoalDomain.title);
    }
  }

  readOrgGoalDefinitions ()
  {
    this.listOfSelects.length=0;
    
    let rootSI = new SelectRecord("Select Parent","");
    this.listOfSelects.push(rootSI);

    let req = new SearchOrgGoalDefinitionRequest ();
    let result = this.bs.searchOrgGoalDefinition(req);

    result.then(value => 
      {

      if (value != null && value.status.statusCode == RequestStatusInformation.standardSuccessCode)
      {
          for (let record of value.goalInfos)
          {
              let si = new SelectRecord(record.title,record.title);
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

  saveRecord ()
  {
     let request = new CreateOrgGoalDefinitionRequest ();
     let goalInfo = new OrgGoalDefinition();
     let orgGoalDomain = new OrgGoalDomain ();
     orgGoalDomain.title = this.orgDomain.value;
     request.goalInfo = goalInfo;
     goalInfo.description = this.description.value;
     goalInfo.title = this.title.value;
     goalInfo.goalWeight = this.goalWeight.value;
     goalInfo.goalDefinitionState = "ACTIVE";
     goalInfo.orgGoalDomain = orgGoalDomain;

     if (this.selectedParent != null)
     {
       let parentGoal = new OrgGoalDefinition ();
       parentGoal.title = this.selectedParent;
       goalInfo.parentGoal = parentGoal;
     }
     
    
     this.bs.createOrgGoalDefinition(request).then(response=>{

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
     let request = new DeleteOrgGoalDefinitionRequest ();
     request.objectId = this.title.value;
     this.bs.deleteOrgGoalDefinition(request).then(response=>{

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
     this.listOfDefinitions.length = 0;

     let request = new SearchOrgGoalDefinitionRequest ();
     let goalInfo = new OrgGoalDefinition ();
     request.goalInfo = goalInfo;
     
     if (this.description.value != null && this.description.value.length > 0)
     goalInfo.description = this.description.value;
     
     if (this.title.value != null && this.title.value.length > 0)
     goalInfo.title = this.title.value;
     
     let result = this.bs.searchOrgGoalDefinition(request);
     
     result.then(value => 
      {
 
       if (value != null && value.status.statusCode == RequestStatusInformation.standardSuccessCode)
       {
           for (let goalInfo of value.goalInfos)
           {
               this.listOfDefinitions.push(goalInfo);
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
    this.listOfDomains.length=0;
    
    let rootSI = new SelectRecord("Select Domain","");
    this.listOfDomains.push(rootSI);

    let req = new SearchOrgDomainRequest ();
    let result = this.bs.searchOrgDomain(req);

    result.then(value => 
      {

      if (value != null && value.status.statusCode == RequestStatusInformation.standardSuccessCode)
      {
          for (let orgDomainInfo of value.domainInfos)
          {
              let si = new SelectRecord(orgDomainInfo.title,orgDomainInfo.title);
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



  ngOnInit(): void
  {
    this.readOrgDomains ();
    this.readOrgGoalDefinitions ();
  }

}