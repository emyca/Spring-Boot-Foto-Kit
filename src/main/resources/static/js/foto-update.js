$(function() {

    const updateForm = $('#updateForm');
    const updateModalCloseBtn = $('#updateModalCloseBtn');
    const updateFormUpdateBtn = $('#updateFormUpdateBtn');
    const updateFormSpinner = $('#updateFormSpinner');
    const updateFormResponse = $('#updateFormResponse');

    $('button[name="btnUpdate"]').click(function(e) {
        e.preventDefault();
        let parentEl = $(this).closest('.card');
        let fotoId = $(parentEl).find('span[name="id"]').html();
        let fotoName = $(parentEl).find('span[name="name"]').html();
        let fotoDescription = $(parentEl).find('span[name="description"]').html();
        $('#updateId').html(fotoId);
        $('#updateFormName').val(fotoName);
        $('#updateFormDescr').val(fotoDescription);
    });

    updateFormUpdateBtn.click(function(e) {
        e.preventDefault();
        updateFormUpdateBtn.prop("disabled", true);
        updateFormSpinner.show();

		let fotoUpdateId = $('span[id=updateId]').html();

        // Create a regular JS object
        let updateData = {
            name: $("#updateFormName").val(),
            description: $("#updateFormDescr").val()
        };
        // Just for debug
        console.log(JSON.stringify(updateData));

        $.ajax({
            type: 'PUT',
            url: './fotos/' + fotoUpdateId,
			dataType: 'json',
            contentType: "application/json",
			processData: false,
            data: JSON.stringify(updateData)
        })
        .done(function(response) {
            if(response.success == false) {
                updateFormResponse
                    .css({"color": "#f02d1f", "font-size": "16px"})
                    .html(response.message);
            } else {
                updateFormResponse
                    .css({"color": "#22a131", "font-size": "16px"})
                    .html(response.message);
            }
            updateFormSpinner.hide();
            updateFormResponse.html(output);
            updateFormUpdateBtn.prop("disabled", false);
        })
        .fail(function(e) {
            updateFormSpinner.hide();
            updateFormResponse.html(e.responseText);
            updateFormUpdateBtn.prop("disabled", false);
        });
    });

    updateModalCloseBtn.click(function(e) {
        updateForm[0].reset();
        updateFormResponse.html('');
        location.reload();
    });
});
