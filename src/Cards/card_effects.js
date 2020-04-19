/*
This file contains the card effects.
*/

/**
Bash Ironclad card.
*/
var Bash = new Object();
Bash.getDependencies = function(){
    var dep = {
        num : 1
        d1  : "target"
    }
    return dep;
}
//Effect 1 of bash
Bash.next1 = function(target,upgrade) {
    var effect = {
        name : "Damage",
        target : target,
        damage : 0
    }
    if(!upgrade){
        effect.damage = 6;
        effect.target = target;
    }
    else{
        effect.damage = 8;
    }
    return effect;
}


//Effect 2 of bash
Bash.next2 = function(target,upgrade) {
    var effect = {
        name : "ApplyBuff",
        buff : "Vulnerable",
        target : "Self",
        x : 0
    }
    if(!upgrade){
        effect.x = 2
    }
    else{
        effect.x = 3;
    }
    return effect;
}

