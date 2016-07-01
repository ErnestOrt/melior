$(".front-loading").height($("body").height());
$(".front-loading").hide();

var ingredientsAdded = [];
var idUpdateSelected;


function addIngredientCreate(){

       $(".front-loading").show();
       var validation = validateNotEmptyField('create-ingredient-quantity', true);
       validation = validateSelectField('create-ingredient-select', validation);

       if(validation){
           var cost = parseFloat($("#create-ingredient-quantity").val()) * parseFloat($("#create-ingredient-select option:selected").data("price")) + parseFloat($('#create-consumible-cost').text());
           $('#create-consumible-cost').text(cost.toFixed(2));

           ingredientsAdded.push({ingredientId : $("#create-ingredient-select option:selected").val(), quantity: $("#create-ingredient-quantity").val()});

           $("#table-create-ingredients").append('<tr id="create-'+$("#create-ingredient-select option:selected").val()+'-row" data-price="'+$("#create-ingredient-select option:selected").data("price")+'" data-quantity="'+ $("#create-ingredient-quantity").val() +'" ><td>'+$("#create-ingredient-select option:selected").text()+'</td><td>'+$("#create-ingredient-quantity").val()+'</td><td><button type="button" class="btn-xs btn-danger" onclick="removeIngredientCreate(\''+$("#create-ingredient-select option:selected").val()+'\')">Delete</button></td></tr>');
           $("#create-ingredient-quantity").val("");
           $('#create-ingredient-select').prop('selectedIndex',0);

        }
        $(".front-loading").hide();
   }

   function addIngredientUpdate(){

        $(".front-loading").show();
        var validation = validateNotEmptyField('update-ingredient-quantity', true);
        validation = validateSelectField('update-ingredient-select', validation);

       if(validation){
           var cost = parseFloat($("#update-ingredient-quantity").val()) * parseFloat($("#update-ingredient-select option:selected").data("price")) + parseFloat($('#update-consumible-cost').text());
           $('#update-consumible-cost').text(cost.toFixed(2));

           ingredientsAdded.push({ingredientId : $("#update-ingredient-select option:selected").val(), quantity: $("#update-ingredient-quantity").val()});

           $("#table-update-ingredients").append('<tr id="update-'+$("#update-ingredient-select option:selected").val()+'-row" data-price="'+$("#update-ingredient-select option:selected").data("price")+'" data-quantity="'+ $("#update-ingredient-quantity").val() +'"><td>'+$("#update-ingredient-select option:selected").text()+'</td><td>'+$("#update-ingredient-quantity").val()+'</td><td><button type="button" class="btn-xs btn-danger" onclick="removeIngredientUpdate(\''+$("#update-ingredient-select option:selected").val()+'\')">Delete</button></td></tr>');
           $("#update-ingredient-quantity").val("");
           $('#update-ingredient-select').prop('selectedIndex',0);
        }
        $(".front-loading").hide();
   }

     function removeIngredientCreate(idToBeRemoved){

        $(".front-loading").show();
        var cost =  parseFloat($('#create-consumible-cost').text()) - parseFloat($('#create-'+idToBeRemoved+'-row').data("quantity")) * parseFloat($('#create-'+idToBeRemoved+'-row').data("price"));
        $('#create-consumible-cost').text(cost.toFixed(2));

          $('#create-'+idToBeRemoved+'-row').remove();

          var ingredientsAddedAux = [];

          for(i=0;i<ingredientsAdded.length;i++){
              if(ingredientsAdded[i].ingredientId != idToBeRemoved){
                  ingredientsAddedAux.push(ingredientsAdded[i]);
              }
          }

         ingredientsAdded = ingredientsAddedAux;
         $(".front-loading").hide();
     }

     function removeIngredientUpdate(idToBeRemoved){

        $(".front-loading").show();
        var cost =  parseFloat($('#update-consumible-cost').text()) - parseFloat($('#update-'+idToBeRemoved+'-row').data("quantity")) * parseFloat($('#update-'+idToBeRemoved+'-row').data("price"));
        $('#update-consumible-cost').text(cost.toFixed(2));

          $('#update-'+idToBeRemoved+'-row').remove();

          var ingredientsAddedAux = [];

          for(i=0;i<ingredientsAdded.length;i++){
              if(ingredientsAdded[i].ingredientId != idToBeRemoved){
                  ingredientsAddedAux.push(ingredientsAdded[i]);
              }
          }

         ingredientsAdded = ingredientsAddedAux;
         $(".front-loading").hide();
     }


   function createConsumable(){
        var validation = validateNotEmptyField('create-consumible-name', true);
        validation = validateNumericField('create-consumible-price', validation);

        if(validation){
            $('#modal-create-consumible').modal('hide');
            $.ajax({
                url : "/consumable/create",
                type: "POST",
                data : {name: $("#create-consumible-name").val(),
                        price: $("#create-consumible-price").val(),
                        ingredients: JSON.stringify(ingredientsAdded)},
                beforeSend: function(){$('.front-loading').show();},
                error: function(){$('.front-loading').hide()},
                success: function(data, textStatus, jqXHR) { setTimeout(function(){location.reload();}, 1500);}
            });
        }
    }

    function deleteConsumable(idConsumable){
        $.ajax({
            url : "/consumable/delete",
            type: "POST",
            data : {id: idConsumable},
            beforeSend: function(){$('.front-loading').show();},
            error: function(){$('.front-loading').hide()},
            success: function(data, textStatus, jqXHR) { setTimeout(function(){location.reload();}, 1500);}
        });
    }

    function updateModal(idConsumable){

        $(".front-loading").show();
        $('#table-update-ingredients tbody > tr').remove();
        idUpdateSelected = idConsumable;


        for(i=0;i<consumablesList.length;i++){
              if(consumablesList[i]._id == idConsumable){
                  $("#update-consumible-name").val(consumablesList[i].name);
                  $("#update-consumible-price").val(consumablesList[i].priceOnMenu);
                  $('#update-consumible-cost').text(consumablesList[i].cost);

                  for(j=0; j<consumablesList[i].ingredientUsedList.length; j++){

                        ingredientsAdded.push({ingredientId : consumablesList[i].ingredientUsedList[j].ingredientId, quantity: consumablesList[i].ingredientUsedList[j].quantity});

                        var ingredient;
                        for(m=0;m<ingredientsList.length;m++){
                              if(ingredientsList[m]._id == consumablesList[i].ingredientUsedList[j].ingredientId){
                                  ingredient = ingredientsList[m];
                              }
                          }
                        $("#table-update-ingredients").append('<tr id="update-'+consumablesList[i].ingredientUsedList[j].ingredientId+'-row" data-price="'+ingredient.price+'" data-quantity="'+consumablesList[i].ingredientUsedList[j].quantity+'"><td>'+ingredient.name+'</td><td>'+consumablesList[i].ingredientUsedList[j].quantity+'</td><td><button type="button" class="btn-xs btn-danger" onclick="removeIngredientUpdate(\''+consumablesList[i].ingredientUsedList[j].ingredientId+'\')">Delete</button></td></tr>');

                  }
              }
          }
         $(".front-loading").hide();
         $('#modal-update-consumible').modal('show');

    }

    function cleanUpIngredientsAdded(){
        ingredientsAdded = [];
    }


    function updateConsumable(){
        var validation = validateNotEmptyField('update-consumible-name', true);
        validation = validateNumericField('update-consumible-price', validation);

        if(validation){
            $('#modal-update-consumible').modal('hide');
          $.ajax({
            url : "/consumable/update",
            type: "POST",
            data : {id: idUpdateSelected,
                    name: $("#update-consumible-name").val(),
                    price: $("#update-consumible-price").val(),
                    ingredients: JSON.stringify(ingredientsAdded)},
            beforeSend: function(){$('.front-loading').show();},
            error: function(){$('.front-loading').hide()},
            success: function(data, textStatus, jqXHR) { setTimeout(function(){location.reload();}, 1500);}
          });
        }
    }