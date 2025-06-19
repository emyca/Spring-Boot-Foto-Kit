$(function() {

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

    $('#updateFormUpdateBtn').click(function(e) {
        e.preventDefault();
		$("#updateFormCancelBtn").prop("disabled", true);
		$("#updateFormUpdateBtn").prop("disabled", true);
		$('#updateFormSpinner').show();

		let fotoUpdateId = $('span[id=updateId]').html();

        let formData = new FormData();
        formData.append('fotoName', $('input[id=updateFormName]').val());
        formData.append('fotoDescr', $('textarea[id=updateFormDescr]').val());

        $.ajax({
            type: 'PUT',
            url: './fotos/' + fotoUpdateId,
			dataType: 'json',
            contentType: "application/json",
			processData: false,
            data: formData
        })
        .done(function(response) {
            if (response.success == false) {
                output = "<span style='color: #f02d1f; font-size: 16px;'>" + response.message + "</span>";
            } else {
                output = "<span style='color: #22a131; font-size: 16px;'>" + response.message + "</span>";
            }
            $('#updateFormSpinner').hide();
            $('#updateFormResponse').html(output);
            $("#updateFormCancelBtn").prop("disabled", false);
            $("#updateFormUpdateBtn").prop("disabled", false);
        })
        .fail(function(e) {
            $('#updateFormSpinner').hide();
            $("#updateFormResponse").html(e.responseText);
            $("#updateFormCancelBtn").prop("disabled", false);
            $("#updateFormUpdateBtn").prop("disabled", false);
        });
    });

    $('#updateFormCancelBtn').click(function(e) {
            $('#updateForm')[0].reset();
            $('#updateFormResponse').html('');
    });

    $('#updateModalCloseBtn').click(function(e) {
        $('#updateForm')[0].reset();
        $('#updateFormResponse').html('');
        location.reload();
    });

});
