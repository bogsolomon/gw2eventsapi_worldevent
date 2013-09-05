 var pingSound;

soundManager.setup({
  url: 'resources/swf/',
  flashVersion: 9, 
  debugMode: false,// optional: shiny features (default = 8)
  // optional: ignore Flash where possible, use 100% HTML5 mode
  // preferFlash: false,
  onready: function() {
    // Ready to use; soundManager.createSound() etc. can now be called.
	  pingSound = soundManager.createSound({id: 'sound', url: 'resources/bell-ringing-04.mp3'});
	  
  }
});

function playSound() {
	var elements = document.getElementsByClassName("soundClass");
	
	var elementCB = document.getElementById("checkboxTab:checkboxes:playSoundCB:0");
	
	var playSound = false;
	
	if (elementCB.checked) {
		for(var i=0; i<elements.length; i++) {
			 var content = elements[i].getElementsByTagName("span")[0].innerHTML;
			
			if (content == "true") {
				playSound = true;
				elements[i].getElementsByTagName("span")[0].innerHTML = "false";
			}
		}
		
		if (pingSound != null && playSound)
			pingSound.play();
	}
}