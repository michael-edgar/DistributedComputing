/* madboa.js -- common javascript functions for madboa.com */

function setStylesheet(title) {
  var i, a;
  for (i = 0; (a = document.getElementsByTagName("link")[i]); i++) {
    if (a.getAttribute("rel").indexOf("stylesheet") != -1)  {
       a.disabled = true;
       if (a.getAttribute("title") == title) { a.disabled = false; }
     }
   }
}

/* eof */
