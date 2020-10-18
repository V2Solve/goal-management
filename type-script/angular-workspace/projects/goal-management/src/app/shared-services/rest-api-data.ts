/**
 * A Base Request which represents the base from all requests will originate for the API.
 * @author Saurin Magiawala
 *
 */
export class BaseRequest
{
	/**
	 * 
	 */
	
	/**
	 * Unique client id which represents the client on behalf of which the call is being made.
	 */
	clientId!: string;
	
	/**
	 * Additional groups that the client must be assumed to belong to 
	 *
	 */
	groups!: Array<string>;

	/**
	 * data about how information returned should be paginated. - helpful in search and responses
	 */
	pagingInfo!: PagingInformation;
	
	
	/**
	 * data about how information returned should be sorted - helpful in searches.
	 */
	sortingInfo!: SortingInformation;
}





/**
 * A base response class which all the response will extend.
 * @author Saurin Magiawala
 *
 */
export class BaseResponse
{	/**
	 * 
	 */

	
	// will specific what the status is. (error, or success depending on the code)
	status!: RequestStatusInformation;
	
	// In case of paging enabled the number of records returned could be held in here..
	pageInfo!: PagingInformation;
}



/**
 * This class represents a request/response data structure for paging information to be passed in API calls
 * and returned as appropriate.
 * @author Saurin Magiawala
 *
 */
export class PagingInformation
{
	/**
	 * 
	 */
	// may be set in request or response
	currentPage!: number;
	
	// usually set in requests..
	pageSize!: number;
	
	// may be set in request or response..
	noOfPages!: number;
	
	// may be set in the response
	totalNoOfRecords!: number;
	
	// may be set in the response.
	recordsReturned!: number;
}



/**
 * This class represents a Request Status information.  It can be extended by other classes, but has common
 * structure such as a success message/success code, and error message/error code if operations failed.
 * @author Saurin Magiawala
 *
 */
export class RequestStatusInformation
{
	/**
	 * 
	 */
	statusCode!: string;
	statusMessage!: string;
	
	public static standardSuccessCode: string = "Succeeded";
	public static standardFailureCode: string = "Failed";
	public static standardSuccessMessage: string = "Operation Succeeded";
	public static standardFailureMessage: string = "Operation Failed";
	
	public static SUCCESS = new RequestStatusInformation(RequestStatusInformation.standardSuccessCode,RequestStatusInformation.standardSuccessMessage);
	public static FAILURE = new RequestStatusInformation(RequestStatusInformation.standardFailureCode,RequestStatusInformation.standardFailureMessage);
	

	public static success (successMessage: string): RequestStatusInformation
	{
		return new RequestStatusInformation(RequestStatusInformation.standardSuccessCode, RequestStatusInformation.standardSuccessMessage);
	}
	
	public static failure (failureMessage: string): RequestStatusInformation
	{
		return new RequestStatusInformation(RequestStatusInformation.standardFailureCode, RequestStatusInformation.standardFailureMessage);
	}
	
	constructor (statusCode: string,statusMessage: string)
	{
		this.statusCode =statusCode;
		this.statusMessage = statusMessage;
	}

}





/**
 * 
 * This class represents the sort information for a field.
 * 
 * @author Saurin Magiawala
 *
 */



export class SortInfo 
{
	sortField!: string;
	sortDirection!: string;
}


/**
 * This class represents a request/response data structure for paging information to be passed in API calls
 * and returned as appropriate.
 * @author Saurin Magiawala
 *
 */


export class SortingInformation
{
	/**
	 * 
	 */
	
	/**
	 * Array of Sorts that need to be applied to a query..
	 */
	sorts!: Array<SortInfo>;
}
