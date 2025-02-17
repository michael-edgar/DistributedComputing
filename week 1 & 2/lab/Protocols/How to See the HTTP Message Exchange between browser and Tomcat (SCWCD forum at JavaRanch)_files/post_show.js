function sendEmail(s1, s2)
{
	document.location = 'mailto:' + showEmail(s1, s2);
}

function handleBbCode(evt)
{
	var e = evt || window.event;
	var thisKey = e.which || e.keyCode;

	var ch = String.fromCharCode(thisKey).toLowerCase();
	
	if (e.altKey && ch == "b") {
		bbstyle(0);
		return false;
	}
	else if (e.altKey && ch == "i") {
		bbstyle(2);
		return false;
	}
	else if (e.altKey && ch == "u") {
		bbstyle(4);
		return false;
	}
	else if (e.altKey && ch == "q") {
		bbstyle(6);
		return false;
	}
	else if (e.altKey && ch == "c") {
		bbstyle(8);
		return false;
	}
	else if (e.altKey && ch == "l") {
		bbstyle(10);
		return false;
	}
	else if (e.altKey && ch == "p") {
		bbstyle(12);
		return false;
	}
	else if (e.altKey && ch == "w") {
		bbstyle(14);
		return false;
	}
	else if (e.altKey && ch == "y") {
		bbstyle(18);
		return false;
	}
}

function enterText(field)
{
	storeCaret(field);
	document.onkeydown = handleBbCode;
}

function leaveText()
{
	document.onkeydown = null;
}

function selectCode(a)
{
	var e = a.parentNode.parentNode.getElementsByTagName('code')[0];

	if (document.selection) {
		var r = document.body.createTextRange();
		r.moveToElementText(e);
		r.select();
	}
	else {
		var s = window.getSelection();
		var r = document.createRange();
		r.setStartBefore(e);
		r.setEndAfter(e);
		s.addRange(r);
	}
}
