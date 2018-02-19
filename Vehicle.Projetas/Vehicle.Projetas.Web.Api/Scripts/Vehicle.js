$(document).ready(function () {
    $("#rbNew").change(function () {
        if ($('#rbNew input:checked').val() == "True") {
            $("#ddlYear").prop("disabled", true);            
            $("#ddlYear option[value='" + (new Date).getFullYear() + "']").attr('selected', 'selected');            
        } else {
            $("#ddlYear").prop("disabled", false);                     
        }

    });
    return false;
});