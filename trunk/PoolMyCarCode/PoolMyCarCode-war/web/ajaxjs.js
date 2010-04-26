var xmlhttp
var data
var ids
var cont=0
var oTextbox = new Array();

function loadContent(id)
{
    ids = id;
    xmlhttp=GetXmlHttpObject();

    if (xmlhttp==null)
    {
        alert ("Your browser does not support Ajax HTTP");
        return;
    }

    var url="ServletController?operation=autoCompletamento";
    xmlhttp.onreadystatechange=getOutput;
    xmlhttp.open("GET",url,true);
    xmlhttp.send(null);
}

function getOutput()
{
    if (xmlhttp.readyState==4)
    {
        //document.getElementById("prtCnt").innerHTML=xmlhttp.responseText;
        data = ((String) (xmlhttp.responseText)).split("<br>");
        data= data.sort();
                cont = ids.length;
        var j=0;
        while( j <ids.length){
            
            oTextbox[j]=new AutoSuggestControl(document.getElementById(ids[j]), new StateSuggestions(data));
            j=j+1;
            
        }
  
    }
}

function completamento(id){
    oTextbox[cont] =new AutoSuggestControl(document.getElementById(id), new StateSuggestions(data));
    cont=cont+1;
}

function GetXmlHttpObject()
{
    if (window.XMLHttpRequest)
    {
        return new XMLHttpRequest();
    }
    if (window.ActiveXObject)
    {
        return new ActiveXObject("Microsoft.XMLHTTP");
    }
    return null;
}