$(function() {

    $('#addFormSaveBtn').click(function(e) {
		e.preventDefault();
		$("#addFormClearBtn").prop("disabled", true);
		$("#addFormSaveBtn").prop("disabled", true);
		$('#addFormSpinner').show();

        let formData = new FormData();
		formData.append('name', $('input[id=addFormName]').val());
		formData.append('description', $('textarea[id=addFormDescr]').val());
		formData.append('file', $('input[id=addFormFile]')[0].files[0]);

        $.ajax({
            type: 'POST',
            enctype: 'multipart/form-data',
			url: './fotos',
			dataType: 'json',
            contentType: false,
            processData: false,
			data: formData
        })
        .done(function(response) {
            if(response.success == false) {
                output = "<span style='color: #f02d1f; font-size: 16px;'>" + response.message + "</span>";
            } else {
                output = "<span style='color: #22a131; font-size: 16px;'>" + response.message + "</span>";
            }
            $('#addFormSpinner').hide();
            $('#addFormResponse').html(output);
            $("#addFormClearBtn").prop("disabled", false);
            $("#addFormSaveBtn").prop("disabled", false);
        })
        .fail (function(e) {
            $('#addFormSpinner').hide();
            $("#addFormResponse").html(e.responseText);
            $("#addFormClearBtn").prop("disabled", false);
            $("#addFormSaveBtn").prop("disabled", false);
        });
    });

    $('#addFormClearBtn').click(function(e) {
        $('#addForm')[0].reset();
        $('#addFormResponse').html('');
        location.reload();
    });

});
