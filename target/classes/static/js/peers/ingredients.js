$(".front-loading").height($("body").height());
$(".front-loading").hide();


var idUpdateSelected;

function createIngredient(){
    var result = true;

    result = validateNotEmptyField('create-ingredient-name', result);
    result = validateNotEmptyField('create-ingredient-price', result);
    result = validateNumericField('create-ingredient-price', result);

    if(result ){
        $.ajax({
            url : "/ingredients/create",
            type: "POST",
            data : {name: $("#create-ingredient-name").val(),
                    price: $("#create-ingredient-price").val()},
            beforeSend: function(){$('.front-loading').show();},
            error: function(){$('.front-loading').hide()},
            success: function(data, textStatus, jqXHR) { setTimeout(function(){location.reload();}, 1500);}
        });
    }
}

function deleteIngredient(idIngredient){
    $.ajax({
        url : "/ingredients/delete",
        type: "POST",
        data : {id: idIngredient},
        beforeSend: function(){$('.front-loading').show();},
        error: function(){$('.front-loading').hide()},
        success: function(data, textStatus, jqXHR) { setTimeout(function(){location.reload();}, 1500);}
    });
}

function updateModal(idIngredient){
     idUpdateSelected = idIngredient;

     $("#update-ingredient-name").val($("#"+idIngredient+"-name").text());
     $("#update-ingredient-price").val($("#"+idIngredient+"-price").text());
     $('#modal-update-ingredient').modal('show');
}

function updateIngredient(){
    var result = true;

    result = validateNotEmptyField('update-ingredient-name', result);
    result = validateNotEmptyField('update-ingredient-price', result);
    result = validateNumericField('update-ingredient-price', result);

    if(result){
        $.ajax({
            url : "/ingredients/update",
            type: "POST",
            data : {id: idUpdateSelected,
                    name: $("#update-ingredient-name").val(),
                    price: $("#update-ingredient-price").val()},
            beforeSend: function(){$('#modal-update-ingredient').modal('hide'); $('.front-loading').show();},
            error: function(){$('.front-loading').hide()},
            success: function(data, textStatus, jqXHR) { setTimeout(function(){location.reload();}, 1500);}
        });
    }
}