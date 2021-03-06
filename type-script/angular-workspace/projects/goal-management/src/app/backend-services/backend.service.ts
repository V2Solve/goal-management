import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { AuthenticationService } from '../shared-services/authentication.service';
import {BaseService} from './base-service'
import * as backenddata from './backend-data';
import { environment } from '../../environments/environment'

@Injectable({
  providedIn: 'root'
})
export class BackEndService extends BaseService 
{
  backendBaseUrl!: string;

  constructor(authService: AuthenticationService,httpClient: HttpClient) 
  {
     super(authService,httpClient);
     this.backendBaseUrl = environment.backendBaseUrl;
  }

  createOrgDomain (request: backenddata.CreateOrgDomainRequest): Promise<backenddata.CreateOrgDomainResponse>
  {
      let url = this.backendBaseUrl + "/createOrgDomain";
      return this.httpClient.post<backenddata.CreateOrgDomainResponse>(url,request,this.getHttpOptions()).toPromise();
  }


  deleteOrgDomain (request: backenddata.DeleteOrgDomainRequest): Promise<backenddata.DeleteOrgDomainResponse>
  {
    let url = this.backendBaseUrl + "/deleteOrgDomain";
    return this.httpClient.post<backenddata.DeleteOrgDomainResponse>(url,request,this.getHttpOptions()).toPromise();
  }


  searchOrgDomain (request: backenddata.SearchOrgDomainRequest): Promise<backenddata.SearchOrgDomainResponse>
  {
    let url = this.backendBaseUrl + "/searchOrgDomain";
    return this.httpClient.post<backenddata.SearchOrgDomainResponse>(url,request,this.getHttpOptions()).toPromise();
  }

  createOrgGoalDefinition (request: backenddata.CreateOrgGoalDefinitionRequest): Promise<backenddata.CreateOrgGoalDefinitionResponse>
  {
      let url = this.backendBaseUrl + "/createOrgGoalDefinition";
      return this.httpClient.post<backenddata.CreateOrgGoalDefinitionResponse>(url,request,this.getHttpOptions()).toPromise();
  }


  deleteOrgGoalDefinition (request: backenddata.DeleteOrgGoalDefinitionRequest): Promise<backenddata.DeleteOrgGoalDefinitionResponse>
  {
    let url = this.backendBaseUrl + "/deleteOrgGoalDefinition";
    return this.httpClient.post<backenddata.DeleteOrgGoalDefinitionResponse>(url,request,this.getHttpOptions()).toPromise();
  }


  searchOrgGoalDefinition (request: backenddata.SearchOrgGoalDefinitionRequest): Promise<backenddata.SearchOrgGoalDefintionResponse>
  {
    let url = this.backendBaseUrl + "/searchOrgGoalDefinition";
    return this.httpClient.post<backenddata.SearchOrgGoalDefintionResponse>(url,request,this.getHttpOptions()).toPromise();
  }

}