var xmlhttp
var data

function loadContent()
{

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

 var oTextbox = new AutoSuggestControl(document.getElementById("partenza"), new StateSuggestions(data));
 var oTextbox2 = new AutoSuggestControl(document.getElementById("arrivo"), new StateSuggestions(data));
  }
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