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
        target : target,
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

//Defend
var Defend = new Object();
Defend.getDependencies = function(){
    var dep = {
        num : 0
    }
    return dep;
}
//Effect 1 of bash
Defend.next1 = function(upgrade) {
    var effect = {
        name : "Block",
        target : "Self",
        block : 0
    }
    if(!upgrade){
        effect.block = 5;
    }
    else{
        effect.block = 8;
    }
    return effect;
}

//Strike

var Strike = new Object();
Strike.getDependencies = function(){
    var dep = {
        num : 1
        d1 : "target"
    }
    return dep;
}

Strike.next1 = function(target,upgrade) {
    var effect = {
        name : "Damage",
        target : target,
        damage : 0
    }
    if(!upgrade){
        effect.damage = 6;
    }
    else{
        effect.damage = 9;
    }
    return effect;
}

//Anger

var Anger = new Object();

Strike.getDependencies = function(){
    var dep = {
        num : 1
        d1 : "target"
    }
    return dep;
}

Anger.next1 = function(target,upgrade) {
    var effect = {
        name : "Damage",
        target : target,
        damage : 0
    }
    if(!upgrade){
        effect.damage = 6;
    }
    else{
        effect.damage = 8;
    }
    return effect;
}

Anger.next2 = function(target,upgrade){
    var effect = {
        name : "MoveCards",
        sourcePile : null;
        targetPile : "handPile",
        card : "Anger"
    }
    if(upgrade)
        effect.card = "Anger+";

    return effect;

}

//Armaments
/*
Gain 5 Block. Upgrade a(ALL) card(s) in your hand for the rest of combat.
*/
var Armaments = new Object();

Armaments.getDependencies = function(){
    var dep = {
        num : 1
        d1 : "handPile"
    }
    return dep;
}

Armaments.next1 = function(handPile,upgrade){
    var effect = {
        name : "Block",
        target : "Self",
        block : 5
    }
    return effect;
}

//Body Slam
/*
Deal damage equal to your current Block.
*/
var Body_Slam = new Object();

Body_Slam.getDependencies = function(){
    var dep = {
        num : 1
        d1 : "block",
        d2 : "target"
    }
    return dep;
}

Body_Slam.next1 = function(block,target,upgrade){
    var effect = {
        name : "Damage",
        target : target,
        damage : block
    }
    return effect;
}


/*
Clash
Can only be played if every card in your hand is an Attack. Deal 14(18) damage.
*/
var Clash = new Object();

Clash.getDependencies = function(){
    var dep = {
        num : 1
        d1 : "handPile",
        d2 : "target"
    }
    return dep;
}

Clash.next1 = function(handPile,target,upgrade){
    var effect = {
        name : "Damage",
        target : target,
        damage : 0
    }
    if(!upgrade){
        effect.damage = 14;
    }
    else{
        effect.damage = 18;
    }
    return effect;
}

/*
Cleave
Deal 8(11) damage to ALL enemies
*/
var Cleave = new Object();

Cleave.getDependencies = function(){
    var dep = {
        num : 0
    }
    return dep;
}

Cleave.next1 = function(upgrade){
    var effect = {
        name : "Damage",
        target : "All",
        damage : 0
    }
    if(!upgrade){
        effect.damage = 8;
    }
    else{
        effect.damage = 11;
    }
    return effect;
}

/*
Clothesline
Deal 12(14) damage. Apply 2(3) Weak.
*/
var Clothesline = new Object();

Clothesline.getDependencies = function(){
    var dep = {
        num : 1
        target : "target"
    }
    return dep;
}

Clothesline.next1 = function(target,upgrade){
    var effect = {
        name : "Damage",
        target : target,
        damage : 0
    }
    if(!upgrade){
        effect.damage = 12;
    }
    else{
        effect.damage = 14;
    }
    return effect;
}

Clothesline.next2 = function(target,upgrade){
        var effect = {
            name : "ApplyBuff",
            buff : "Weak",
            target : target,
            x : 2
        }
        
        if(upgrade)effect.x = 3;
        return effect;

}