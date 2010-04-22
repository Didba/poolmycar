<%-- 
    Document   : InserisciDateViaggio
    Created on : 12-apr-2010, 12.02.14
    Author     : berto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
  <title>Sample for the multiple dates feature</title>
  <!-- calendar stylesheet -->
  <link rel="stylesheet" type="text/css" media="all" href="calendar/calendar-blue.css" title="win2k-cold-1" />

  <!-- main calendar program -->
  <script type="text/javascript" src="calendar/calendar.js"></script>

  <!-- language for the calendar -->
  <script type="text/javascript" src="calendar/lang/calendar-en.js"></script>

  <!-- the following script defines the Calendar.setup helper function, which makes
       adding a calendar a matter of 1 or 2 lines of code. -->
  <script type="text/javascript" src="calendar/calendar-setup.js"></script>
</head>

<body>

  <h1>Inserisci le date</h1>
  <a id="trigger" href="#">[open calendar...]</a>

  <div id="output"></div>
  <form name="dati" id="formDate" action="ServletController" method="POST" onSubmit="return(scriviDate());">
  ora: <input name="ora" id="ora" type="text" size="2"/>:<input id="min" name="min" type="text" size="2"/>
  <input type="hidden" name="date" id="campoDate" value=""/>
  <input type="submit" name="operation" value="inserisciDate" />
  </form>

  <script type="text/javascript">//<![CDATA[
    // the default multiple dates selected, first time the calendar is instantiated
    var MA = [];
    var stringDate="";

    function scriviDate(){
        if(document.getElementById("ora").value<=23 && document.getElementById("ora").value>=0 && document.getElementById("min").value<=59 && document.getElementById("min").value>=0){
	    var el = document.getElementById("campoDate");
	    el.setAttribute("value",stringDate);
            return true;
        }
        else{
            alert("inserisci un'ora realistica");
            document.getElementById("ora").value="";
            document.getElementById("min").value="";
            return false;
        }

    }

    function closed(cal) {

      // here we'll write the output; this is only for example.  You
      // will normally fill an input field or something with the dates.
      var el = document.getElementById("output");

      // reset initial content.
      el.innerHTML = "";

      // Reset the "MA", in case one triggers the calendar again.
      // CAREFUL!  You don't want to do "MA = [];".  We need to modify
      // the value of the current array, instead of creating a new one.
      // Calendar.setup is called only once! :-)  So be careful.
      MA.length = 0;

      // walk the calendar's multiple dates selection hash

      stringDate="";
      for (var i in cal.multiple) {
        var d = cal.multiple[i];
        // sometimes the date is not actually selected, that's why we need to check.
        if (d) {
          // OK, selected.  Fill an input field.  Or something.  Just for example,
          // we will display all selected dates in the element having the id "output".
          var stringa=d.print("%A %d %B %Y");
          el.innerHTML += stringa + "<br />";
          stringDate=stringDate+stringa+";";
          // and push it in the "MA", in case one triggers the calendar again.
          MA[MA.length] = d;
        }
      }
      cal.hide();
      return true;
    };

    Calendar.setup({
      align      : "BR",
      showOthers : true,
      multiple   : MA, // pass the initial or computed array of multiple dates to be initially selected
      onClose    : closed,
      button     : "trigger"
    });
  //]]>
  </script>

<hr />


</body> </html>
