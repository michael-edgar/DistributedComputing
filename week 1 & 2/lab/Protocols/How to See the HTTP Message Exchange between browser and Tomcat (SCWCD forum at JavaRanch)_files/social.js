function insertSocialBookmarkLinks(pageUrl,title,imagePath,whichOnes,fbLikePath,postId) {
	if( !pageUrl )
		pageUrl = location.href;
	if( !title )
		title = document.title;
	if(!whichOnes)
		whichOnes = ["delicious","mrwong","twitter","digg","reddit","stumbleupon", "dzone","facebookshare"];

	var info;
	var output = "";

	for( var i=0; i < whichOnes.length; i++ ) {
		info = null;
		switch( whichOnes[i] ) {
			case "delicious":
				info = getDeliciousInfo(pageUrl,title);
				break;
			case "digg":
				info = getDiggInfo(pageUrl,title);
				break;
			case "technorati":
				info = getTechnoratiInfo(pageUrl,title);
				break;
			case "reddit":
				info = getRedditInfo(pageUrl,title);
				break;
			case "stumbleupon":
				info = getStumbleUponInfo(pageUrl,title);
				break;
			case "slashdot":
				info = getSlashdotInfo(pageUrl,title);
				break;
			case "facebook":
				info = getFacebookInfo(pageUrl,title);
				break;
			case "newstrust":
				info = getNewsTrustInfo(pageUrl,title);
				break;
			case "mrwong":
				info = getMrWongInfo(pageUrl,title);
				break;
			case "twitter":
				info = getTwitterInfo(pageUrl,title);
				break;
			case "yahoobuzz":
				info = getYahooBuzzInfo(pageUrl,title);
				break;
			case "dzone":
				info = getDzoneInfo(pageUrl,title);
				break;
			case "facebookshare":
				info = getFaceBookShareInfo(pageUrl);
				break;			
		}
		if( info ) {
			output += getListItem(info,imagePath);
		}
	}
	document.write(output);
}


function getListItem (info, imagePath) {
	var output = "<td><div style=\"padding: 6px; position: relative; background-image: url(" + imagePath + "iconTile.gif);\">"
		+ " <a style=\"text-decoration:none;\" href=\"" + info.href + "\" onclick=\"" + info.onclick + "\" title=\""
		+ info.alt + "\"><img src=\"" + imagePath + info.image + "\" border=\"0\"/>";
		if(info.likesCount && info.likesCount>0){
			output = output + " <span class=\"likesCount\">&nbsp;" + info.likesCount + "&nbsp;</span> ";
		}
		output = output + "</a></div></td>";
	return output;
}

function getDeliciousInfo(pageUrl,title) {
	var info = new Object();
	info.image = "delicious-16x16.gif";
	info.handle = "delicious";
	info.text = "del.icio.us";
	info.href = "http://www.delicious.com/post?v=4&noui&jump=close&url="+encodeURIComponent(pageUrl)+"&title="+encodeURIComponent(title);
	info.onclick = "window.open(this.href,'"+info.handle+"','toolbar=no,width=700,height=400');return false;";
	info.alt = "post to del.icio.us";
	return info;
}

function getDiggInfo(pageUrl,title) {
	var info = new Object();
	info.image = "digg-16x16.gif";
	info.handle = "digg";
	info.text = "Digg";
	info.href = "http://digg.com/submit?phase=2&url="+encodeURIComponent(pageUrl)+"&title="+encodeURIComponent(title)+"&topic=java";
	info.onclick = "";
	info.alt = "Digg!";
	return info;
}

function getTechnoratiInfo(pageUrl,title) {
	var info = new Object();
	info.image = "technorati-16x16.png";
	info.handle = "technorati";
	info.text = "Technorati";
	info.href = "http://technorati.com/faves/?add="+encodeURIComponent(pageUrl);
	info.onclick = "";
	info.alt = "Technorati";
	return info;
}

function getRedditInfo(pageUrl,title) {
	var info = new Object();
	info.image = "reddit-16x16.gif";
	info.handle = "reddit";
	info.text = "reddit";
	info.href = "http://reddit.com/submit?url="+encodeURIComponent(pageUrl);
	info.onclick = "";
	info.alt = "Reddit";
	return info;
}

function getStumbleUponInfo(pageUrl,title) {
	var info = new Object();
	info.image = "stumbleupon-16x16.gif";
	info.handle = "stumbleupon";
	info.text = "StumbleUpon";
	info.href = "http://www.stumbleupon.com/submit?url="+encodeURIComponent(pageUrl)+"&title="+encodeURIComponent(title);
	info.onclick = "";
	info.alt = "StumbleUpon";
	return info;
}

function getSlashdotInfo(pageUrl,title) {
	var info = new Object();
	info.image = "slashdot-16x16.gif";
	info.handle = "slashdot";
	info.text = "Slashdot";
	info.href = "http://slashdot.org/bookmark.pl?url="+encodeURIComponent(pageUrl)+"&title="+encodeURIComponent(title);
	info.onclick = "";
	info.alt = "Slashdot It!";
	return info;
}

function getFaceBookShareInfo(pageUrl,title) {
	var info = new Object();
	info.image = "facebook-16x16.gif";
	info.handle = "facebook";
	info.text = "Facebook";
	info.href = "http://www.facebook.com/share.php?u="+encodeURIComponent(pageUrl)+"&t="+encodeURIComponent(title);
	info.onclick = "";
	info.alt = "Share on Facebook";
	return info;
}

function getNewsTrustinfo(pageurl,title) {
	var info = new Object();
	info.image = "newstrust-16x16.gif";
	info.handle = "newstrust";
	info.text = "NewsTrust";
	info.href = "http://www.newstrust.net/submit?ref=pbs.org&url="+encodeURIComponent(pageurl)+"&title="+encodeURIComponent(title);
	info.onclick = "";
	info.alt = "review on NewsTrust";
	return info;
}

function getMrWongInfo(pageurl,title) {
	var info = new Object();
	info.image = "mrwong-16x16.gif";
	info.handle = "mrwong";
	info.text = "Mr Wong";
	info.href = "http://www.mister-wong.com/index.php?action=addurl&bm_url="+encodeURIComponent(pageurl)+"&bm_description="+encodeURIComponent(title);
	info.onclick = "";
	info.alt = "post to Mr Wong";
	return info;
}

function getTwitterInfo(pageurl,title) {
	var info = new Object();
	info.image = "twitter-16x16.png";
	info.handle = "twitter";
	info.text = "Twitter";
	info.href = "http://twitter.com/home?status="+encodeURIComponent(pageurl);
	info.onclick = "";
	info.alt = "Tweet this!";
	return info;
}

function getYahooBuzzInfo(pageurl,title) {
	var info = new Object();
	info.image = "yahoobuzz-16x16.png";
	info.handle = "yahoobuzz";
	info.text = "Yahoo! Buzz";
	info.href = "http://buzz.yahoo.com/buzz?targetUrl="+encodeURIComponent(pageurl)+"&headline="+encodeURIComponent(title);
	info.onclick = "";
	info.alt = "Buzz up!";
	return info;
}

function getDzoneInfo(pageurl,title) {
	var info = new Object();
	info.image = "dzone-16x16.gif";
	info.handle = "dzone";
	info.text = "DZone";
	info.href = "http://www.dzone.com/links/add.html?url="+encodeURIComponent(pageurl)+"&title="+encodeURIComponent(title)+"&description="+encodeURIComponent(title);
	info.onclick = "";
	info.alt = "submit to DZone";
	return info;
}



