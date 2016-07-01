
function validateNotEmptyField(fieldId, previosResult){
    var result = true;
    if($('#' + fieldId).val()==""){
        $('#' + fieldId).removeClass("invalid").removeClass("valid").addClass("invalid");
        result = false;
    }else{
        $('#' + fieldId).removeClass("invalid").removeClass("valid").addClass("valid");
    }
    if(previosResult == false){
        result = false;
    }
    return result;
}

function validateNumericField(fieldId, previosResult){
    var result = true;
    if(!$.isNumeric($('#' + fieldId).val())){
        $('#' + fieldId).removeClass("invalid").removeClass("valid").addClass("invalid");
        result = false;
    }else{
        $('#' + fieldId).removeClass("invalid").removeClass("valid").addClass("valid");
    }
    if(previosResult == false){
        result = false;
    }
    return result;
}

function validateSelectField(fieldId, previosResult){
    var result = true;
    if($('#' + fieldId).prop('selectedIndex') == 0){
        $('#' + fieldId).removeClass("invalid").removeClass("valid").addClass("invalid");
        result = false;
    }else{
        $('#' + fieldId).removeClass("invalid").removeClass("valid").addClass("valid");
    }
    if(previosResult == false){
        result = false;
    }
    return result;
}