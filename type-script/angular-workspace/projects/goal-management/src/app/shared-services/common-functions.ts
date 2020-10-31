
/**
 * This function will assume that tokens are on the text field in terms of curly braces and it will try to substitute 
 * the data if any found on the data object for that field..
 * E.g. if the text is of type  http://localhost:8080?key={id}&color={colortype}    the function will try to find
 * the two fields (id and idtype on the data object if a value is obtained, will inject those in the place of {id} 
 * and {colortype} respectively. Thus if the id is found to be 10 and colortype is found to be blue the returned 
 * url will be http://localhost:8080?key=10&color=blue )
 * @param data
 * @param text 
 */
export function processLinkTokens (data: any,textStr: string): string
{
    // console.log("In process tokens with text: " + textStr);
    // console.log("In process tokens with data: " + JSON.stringify(data));

    if (textStr == undefined || textStr == null || data == undefined || data == null)
        return textStr;
        
    let firstCurlyIndex = textStr.indexOf("[");
    // console.log("Firstest curly: " + firstCurlyIndex);

    while (firstCurlyIndex >= 0)
    {
        // console.log("First curly: " + firstCurlyIndex);

        let secondCurlyIndex = textStr.indexOf("]",firstCurlyIndex);
        
        if (secondCurlyIndex >= 0)
        {
            // console.log("Second curly: " + secondCurlyIndex);

            // Good we found one.. lets find out what the Id looks like..
            let id = textStr.substr(firstCurlyIndex+1,(secondCurlyIndex-firstCurlyIndex)-1);

            // console.log("id found: " + id);

            if (id != null && id.length > 0)
            {
                // Okay lets find out if there is any data in the data object with that id..
                let idValue = data[id];

                // console.log("id value: " + idValue);

                if (idValue != null && idValue != undefined)
                {
                    // Cool we got that, so lets replace that in the string..
                    textStr = textStr.replace("["+id+"]",idValue);

                    // console.log("Replaced Text: " + textStr);
                }
            }

            // Okay now that we have that replaced.. lets move further
            firstCurlyIndex = textStr.indexOf("[",secondCurlyIndex+1);
            // console.log("First2nd curly: " + firstCurlyIndex);
        }
        else
        {
            firstCurlyIndex = -1;
        }
    }

    // console.log("Returning replaced data: " + textStr);
    return textStr;
}


export function getProperty (property: string, data: any): string
{
    let firstDot = property.indexOf(".");

    if (firstDot <= 0)
    {
        return data[property];
    }

    let currentData: any = data;
    let currentProp: string = property;

    while (firstDot > 0)
    {
        let nextPropName = currentProp.substring(0,firstDot);

        if (nextPropName != null && nextPropName.length > 0)
        {
            currentData = currentData[nextPropName];
        }

        currentProp = currentProp.substring(firstDot+1);

        if (currentProp != null && currentProp.length > 0)
        {
            firstDot = currentProp.indexOf(".");
        }
    }

    if (currentData != null)
        return currentData[currentProp];
    
    return "";
}