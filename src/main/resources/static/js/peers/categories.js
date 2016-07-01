$(".front-loading").height($("body").height());
$(".front-loading").hide();

var consumbalesAdded = [];
var idUpdateSelected;

function addConsumableCreate(){
   consumbalesAdded.push($("#create-category-select option:selected").val());
   $("#table-create-category").append('<tr id="create-'+$("#create-category-select option:selected").val()+'-row"><td>'+$("#create-category-select option:selected").text()+'</td><td><button type="button" class="btn-xs btn-danger" onclick="removeConsumableCreate(\''+$("#create-category-select option:selected").val()+'\')">Delete</button></td></tr>');
}

function removeConsumableCreate(idToBeRemoved){
      $('#create-'+idToBeRemoved+'-row').remove();

      var consumbalesAddedAux = [];

      for(i=0;i<consumbalesAdded.length;i++){
          if(consumbalesAdded[i] != idToBeRemoved){
              consumbalesAddedAux.push(consumbalesAdded[i]);
          }
      }

     consumbalesAdded = consumbalesAddedAux;
 }


 function addConsumableUpdate(){
   consumbalesAdded.push($("#update-category-select option:selected").val());
   $("#table-update-category").append('<tr id="update-'+$("#update-category-select option:selected").val()+'-row"><td>'+$("#update-category-select option:selected").text()+'</td><td><button type="button" class="btn-xs btn-danger" onclick="removeConsumableUpdate(\''+$("#update-category-select option:selected").val()+'\')">Delete</button></td></tr>');
}

function removeConsumableUpdate(idToBeRemoved){
      $('#update-'+idToBeRemoved+'-row').remove();

      var consumbalesAddedAux = [];

      for(i=0;i<consumbalesAdded.length;i++){
          if(consumbalesAdded[i] != idToBeRemoved){
              consumbalesAddedAux.push(consumbalesAdded[i]);
          }
      }

     consumbalesAdded = consumbalesAddedAux;
 }

function createCategory(){
    if(validateNotEmptyField('create-category-name', true)){

        $('#modal-create-category').modal('hide');
        $.ajax({
            url : "/category/create",
            type: "POST",
            data : {name: $("#create-category-name").val(),
                    consumbalesAdded: JSON.stringify(consumbalesAdded)},
            beforeSend: function(){$('.front-loading').show();},
            error: function(){$('.front-loading').hide()},
            success: function(data, textStatus, jqXHR) { setTimeout(function(){location.reload();}, 1500);}
        });
    }
}

function deleteCategory(idToBeDeleted){
    $.ajax({
        url : "/category/delete",
        type: "POST",
        data : {id: idToBeDeleted},
        beforeSend: function(){$('.front-loading').show();},
        error: function(){$('.front-loading').hide()},
        success: function(data, textStatus, jqXHR) { setTimeout(function(){location.reload();}, 1500);}
    });
}

function updateModal(idCategory){
    $('.front-loading').show();
    consumbalesAdded = [];
    $('#table-update-category tbody > tr').remove();
    idUpdateSelected = idCategory;


    for(i=0;i<categoriesList.length;i++){
          if(categoriesList[i]._id == idCategory){
              $("#update-category-name").val(categoriesList[i].name);

              for(j=0; j<categoriesList[i].consumablesIds.length; j++){

                    consumbalesAdded.push(categoriesList[i].consumablesIds[j]);

                    for(m=0;m<consumablesList.length;m++){
                          if(consumablesList[m]._id == categoriesList[i].consumablesIds[j]){
                            $("#table-update-category").append('<tr id="update-'+ categoriesList[i].consumablesIds[j] +'-row"><td>'+consumablesList[m].name+'</td><td><button type="button" class="btn-xs btn-danger" onclick="removeConsumableUpdate(\''+categoriesList[i].consumablesIds[j]+'\')">Delete</button></td></tr>');
                          }
                      }
              }
          }
      }
     $('.front-loading').hide();
     $('#modal-update-category').modal('show');
}

function cleanUpCategoriesAdded(){
    consumbalesAdded = [];
}

function updateCategory(){
    if(validateNotEmptyField('update-category-name', true)){
      $('#modal-update-category').modal('hide');
      $.ajax({
        url : "/category/update",
        type: "POST",
        data : {id: idUpdateSelected,
                name: $("#update-category-name").val(),
                consumbalesAdded: JSON.stringify(consumbalesAdded)},
        beforeSend: function(){$('.front-loading').show();},
        error: function(){$('.front-loading').hide()},
        success: function(data, textStatus, jqXHR) { setTimeout(function(){location.reload();}, 1500);}
      });
    }
}