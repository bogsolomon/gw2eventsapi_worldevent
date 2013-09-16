var clips = new Array();

function createClipboards() {
	var elements = document.getElementsByClassName("clipboard");
	
	for(var i=0; i<elements.length; i++) {
		var clip = new ZeroClipboard(elements[i].getElementsByTagName("button")[0], {
			  moviePath: "script/ZeroClipboard.swf"
			});
		
		clips[elements[i].id] = clip;
	}
}

function updatePositions() {
	var elements = document.getElementsByClassName("clipboard");
	
	for(var i=0; i<elements.length; i++) {
		var clip = new ZeroClipboard(elements[i].getElementsByTagName("button")[0], {
			  moviePath: "script/ZeroClipboard.swf"
			});
	}
}

function updateColors() {
	var elements = document.getElementsByClassName("colorClass");
	
	for(var i=0; i<elements.length; i++) {
		var content = elements[i].getElementsByTagName("span")[0].innerHTML;
		
		elements[i].parentNode.className = "ui-widget-content ui-datatable-odd "+content;
	}
}