import { HttpClient } from '@angular/common/http'
import { AuthenticationService } from '../shared-services/authentication.service';

export class BaseService
{
    authService: AuthenticationService;
    httpClient: HttpClient;

    constructor(authService: AuthenticationService,httpClient: HttpClient)
    {
        this.authService = authService;
        this.httpClient = httpClient;
    }

    getHttpOptions ()
    {
        let authHeader = this.authService.getAuthorizationHeader();

        let httpOptions = {
            headers: {"Authorization": authHeader}
        };

        return httpOptions;
    }

}