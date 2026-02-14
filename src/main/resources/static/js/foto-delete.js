$(function() {

    const deleteModalCloseBtn = $('#deleteModalCloseBtn');
    const deleteDeleteBtn = $('#deleteDeleteBtn');
    const deleteCancelBtn = $("#deleteCancelBtn");
    const deleteSpinner = $('#deleteSpinner');
    const deleteResponse =  $('#deleteResponse');

    $('button[name="btnDelete"]').click(function(e) {
        e.preventDefault();
        let parentEl = $(this).closest('.card');
        let fotoId = $(parentEl).find('span[name="id"]').html();
        let fotoName = $(parentEl).find('span[name="name"]').html();
        $('#deleteId').html(fotoId);
        $('#deleteName').html(fotoName);
    });

    deleteDeleteBtn.click(function(e) {
        e.preventDefault();
        deleteCancelBtn.prop("disabled", true);
        deleteDeleteBtn.prop("disabled", true);
        deleteSpinner.show();

        let fotoDeleteId = $('span[id=deleteId]').html();

        $.ajax({
            type: 'DELETE',
            url: './fotos/' + fotoDeleteId,
            dataType: 'json',
            contentType: false,
            processData: false
        })
        .done(function(response) {
            if(response.success == false) {
                deleteResponse
                    .css({"color": "#f02d1f", "font-size": "16px"})
                    .html(response.message);
            } else {
                deleteResponse
                    .css({"color": "#22a131", "font-size": "16px"})
                    .html(response.message);
            }
            deleteSpinner.hide();
            deleteResponse.html(output);
            deleteDeleteBtn.prop("disabled", false);
        })
        .fail(function(e) {
            deleteSpinner.hide();
            deleteResponse.html(e.responseText);
            deleteDeleteBtn.prop("disabled", false);
        });
    });

    deleteModalCloseBtn.click(function(e) {
        $('#deleteId').html('');
        $('#deleteName').html('');
        deleteResponse.html('');
        location.reload();
    });
});
