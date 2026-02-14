$(function() {

    const addForm = $('#addForm');
    const addModalCloseBtn = $('#addModalCloseBtn');
    const addFormSaveBtn = $('#addFormSaveBtn');
    const addFormSpinner = $('#addFormSpinner');
    const addFormResponse = $('#addFormResponse');

    addFormSaveBtn.click(function(e) {
		e.preventDefault();
        addFormSaveBtn.prop("disabled", true);
        addFormSpinner.show();

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
                addFormResponse
                    .css({"color": "#f02d1f", "font-size": "16px"})
                    .html(response.message);
            } else {
                addFormResponse
                    .css({"color": "#22a131", "font-size": "16px"})
                    .html(response.message);
            }
            addFormSpinner.hide();
            addFormResponse.html(output);
            addFormSaveBtn.prop("disabled", false);
        })
        .fail (function(e) {
            addFormSpinner.hide();
            addFormResponse.html(e.responseText);
            addFormSaveBtn.prop("disabled", false);
        });
    });

    addModalCloseBtn.click(function(e) {
        addForm[0].reset();
        addFormResponse.html('');
        location.reload();
    });
});
