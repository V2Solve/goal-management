import { ChangeDetectorRef, Component, OnInit, ViewChild } from '@angular/core';
import { CreateOrgDomainRequest, CreateOrgGoalDefinitionRequest, DeleteOrgDomainRequest, DeleteOrgGoalDefinitionRequest, OrgGoalDefinition, OrgGoalDomain, SearchOrgDomainRequest, SearchOrgGoalDefinitionRequest } from '../backend-services/backend-data';
import { RequestStatusInformation } from '../shared-services/rest-api-data';
import {TreeNodeData, BaseComponent, SelectRecord } from '../shared-services/base-component';
import {BackEndService} from './../backend-services/backend.service'
import {SelectItem, TreeNode} from './../../../../../node_modules/primeng/api';
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

  treeData: TreeNodeData [] = new Array<TreeNode>();

  title = new FormControl ('',Validators.required);
  description = new FormControl('',Validators.required);
  orgDomain = new FormControl('',Validators.required);
  goalWeight = new FormControl('',[Validators.required])

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

      this.orgDomain.setValue(b.owningGoalDomain.title);
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
     goalInfo.owningGoalDomain = orgGoalDomain;

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
          this.searchRecords (true);
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
          this.searchRecords (true);
      }
      else
      {
        this.addErrorMessage(response.status.statusMessage);
      }
      
     },pipe=>{
      this.setErrorMessage(JSON.stringify(pipe));
     });
  }

  
  setTreeNodes (treeNode: TreeNodeData, goalInfo: OrgGoalDefinition)
  {
    treeNode.data=goalInfo;
    
    if (goalInfo.childGoals != null && goalInfo.childGoals.length > 0)
    {
        treeNode.leaf=false;
        treeNode.expanded=true;
        treeNode.children = new Array();
        for (let goal of goalInfo.childGoals)
        {
            console.log("Pushed child: " + goal.title);
            let node = new TreeNodeData ();
            treeNode.children.push(node);
            this.setTreeNodes(node,goal);
        }
    }
    else
    {
      treeNode.leaf=true;
      treeNode.expanded=true;
      treeNode.children = null;
    }
  }

  setTreeData ()
  {
    let o = new TreeNodeData ();
    let ogd  = new OrgGoalDefinition ();
    o.data=ogd;
    ogd.title = "Sample";
    ogd.description = " Sample Description";
    ogd.goalWeight = 20;
    this.treeData.push(o);
    
  //   this.treeData = [
  //     {
  //         "data":{
  //             "name":"Documents",
  //             "size":"75kb",
  //             "type":"Folder"
  //         },
  //         "children":[
  //             {
  //                 "data":{
  //                     "name":"Work",
  //                     "size":"55kb",
  //                     "type":"Folder"
  //                 },
  //                 "children":[
  //                     {
  //                         "data":{
  //                             "name":"Expenses.doc",
  //                             "size":"30kb",
  //                             "type":"Document"
  //                         }
  //                     },
  //                     {
  //                         "data":{
  //                             "name":"Resume.doc",
  //                             "size":"25kb",
  //                             "type":"Resume"
  //                         }
  //                     }
  //                 ]
  //             },
  //             {
  //                 "data":{
  //                     "name":"Home",
  //                     "size":"20kb",
  //                     "type":"Folder"
  //                 },
  //                 "children":[
  //                     {
  //                         "data":{
  //                             "name":"Invoices",
  //                             "size":"20kb",
  //                             "type":"Text"
  //                         }
  //                     }
  //                 ]
  //             }
  //         ]
  //     },
  //     {
  //         "data":{
  //             "name":"Pictures",
  //             "size":"150kb",
  //             "type":"Folder"
  //         },
  //         "children":[
  //             {
  //                 "data":{
  //                     "name":"barcelona.jpg",
  //                     "size":"90kb",
  //                     "type":"Picture"
  //                 }
  //             },
  //             {
  //                 "data":{
  //                     "name":"primeui.png",
  //                     "size":"30kb",
  //                     "type":"Picture"
  //                 }
  //             },
  //             {
  //                 "data":{
  //                     "name":"optimus.jpg",
  //                     "size":"30kb",
  //                     "type":"Picture"
  //                 }
  //             }
  //         ]
  //     }
  // ];
  }

  searchRecords (clear?: boolean)
  {
     this.listOfDefinitions.length = 0;
     let request = new SearchOrgGoalDefinitionRequest ();
     let goalInfo = new OrgGoalDefinition ();
     request.goalInfo = goalInfo;
     
     if (!clear)
     {
      if (this.description.value != null && this.description.value.length > 0)
      goalInfo.description = this.description.value;
      
      if (this.title.value != null && this.title.value.length > 0)
      goalInfo.title = this.title.value;
     }
     
     let result = this.bs.searchOrgGoalDefinition(request);
     
     result.then(value => 
     {
       if (value != null && value.status.statusCode == RequestStatusInformation.standardSuccessCode)
       {
           this.treeData = new Array ();
           
           for (let goalInfo of value.goalInfos)
           {
               // this.listOfDefinitions.push(goalInfo);
               if (goalInfo.parentGoal == null)
               {
                  let node = new TreeNodeData ();
                  this.setTreeNodes(node,goalInfo);
                  this.treeData.push(node);
                  // console.log("Pushed main node: " + JSON.stringify(node));
               }
           }
           
           // console.log(JSON.stringify(this.treeData));
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
              this.listOfDomains.push(si);
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
    this.searchRecords (false);
  }

}