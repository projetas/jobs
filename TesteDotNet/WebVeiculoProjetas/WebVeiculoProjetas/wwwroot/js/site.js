jQuery.support.cors = true;

function formatDate(date, format) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    if (format == 'yyyy-mm-dd') {
        return [year, month, day].join('-');
    }
    else {
        return [day, month, year].join('/');
    }
}

$(document).ready(function () {
    LoadTable(null);

    $("#formCreate").submit(function (e) {
        e.preventDefault();

        CreateVehicle();
    });

    $('#formUpdate').submit(function (e) {
        e.preventDefault();

        UpdateVehicle();
    });

    $('#txtPrice').maskMoney({ prefix: 'R$ ', allowNegative: true, thousands: '.', decimal: ',', affixesStay: false });
    $('#txtPriceEdit').maskMoney({ prefix: 'R$ ', allowNegative: true, thousands: '.', decimal: ',', affixesStay: false });
});

function OnlyNumber(num) {
    var er = /[^0-9.]/;
    er.lastIndex = 0;
    var campo = num;
    if (er.test(campo.value)) {
        campo.value = "";
    }
}

function ClearForm() {
    //NEW
    $('#txtMark').val('');
    $('#txtModel').val('');
    $('#txtColor').val('');
    $('#txtYear').val('');
    $('#txtPrice').val('');
    $('#txtDescription').val('');
    $('#chkIsNew').prop('checked', false);

    //EDIT
    $('#txtMarkEdit').val('');
    $('#txtModelEdit').val('');
    $('#txtColorEdit').val('');
    $('#txtYearEdit').val('');
    $('#txtPriceEdit').val('');
    $('#txtDescriptionEdit').val('');
    $('#chkIsNewEdit').prop('checked', false);
}

function LoadTable(search) {

    var isNew = 'Sim';
    var getUrl = 'http://localhost:8000/vehicles';

    if (search != null) {
        getUrl += '?search=' + search;
    }

    $('#dataVehicles').empty();
    $.ajax({
        type: 'GET',
        crossDomain: true,
        dataType: 'json',
        url: getUrl,
        success: function (dados) {
            for (var i = 0; dados.dataLista.length > i; i++) {
                if (dados.dataLista[i].isNew) {
                    isNew = 'Sim';
                }
                else {
                    isNew = 'Não';
                }
                $('#dataVehicles').append('<tr><td>' + dados.dataLista[i].id + '</td>'
                    + '<td>' + dados.dataLista[i].mark + '</td>'
                    + '<td>' + dados.dataLista[i].model + '</td>'
                    + '<td>' + dados.dataLista[i].color + '</td>'
                    + '<td>' + dados.dataLista[i].year + '</td>'
                    + '<td>' + dados.dataLista[i].price + '</td>'
                    + '<td>' + dados.dataLista[i].description + '</td>'
                    + '<td>' + isNew + '</td>'
                    + '<td>' + formatDate(dados.dataLista[i].createDate, 'dd/mm/yyyy') + '</td>'
                    + '<td class="center"><a onclick="ViewVehicle(' + dados.dataLista[i].id + ');" href="#modalEdit" role="button" class="btn btn-success btn-default" data-toggle="modal"><i class="fas fa-edit"></i></a></td>'
                    + '<td class="center"><button onclick="DeleteVehicle(' + dados.dataLista[i].id + ');" type="button" class="btn btn-danger btn-default"><i class="fas fa-trash-alt"></i></button></td></tr>');
            }
        }
    });
}

$('#btnSearch').click(function () {
    LoadTable($('#txtSearch').val());
});

function UpdateVehicle() {
    var json = {
        Mark: $('#txtMarkEdit').val(),
        Model: $('#txtModelEdit').val(),
        Color: $('#txtColorEdit').val(),
        Year: $('#txtYearEdit').val(),
        Price: $('#txtPriceEdit').val().replace('.', '').replace(',', '.'),
        Description: $('#txtDescriptionEdit').val(),
        IsNew: $("#chkIsNewEdit").is(':checked')
    };

    $.ajax({
        url: 'http://localhost:8000/vehicles/' + $('#hdVehicle').val(),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: 'PUT',
        crossDomain: true,
        dataType: 'json',
        data: JSON.stringify(json),
        success: function (data) {
            $('#btnCancelEdit').click();
            LoadTable(null);
            ClearForm();
        },
        error: function (data) {
            FailureCallBack(data);
        }
    });
}

function CreateVehicle() {
    var json = {
        Mark: $('#txtMark').val(),
        Model: $('#txtModel').val(),
        Color: $('#txtColor').val(),
        Year: $('#txtYear').val(),
        Price: $('#txtPrice').val().replace('.', '').replace(',', '.'),
        Description: $('#txtDescription').val(),
        IsNew: $("#chkIsNew").is(':checked')
    };

    $.ajax({
        url: 'http://localhost:8000/vehicles',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: 'POST',
        crossDomain: true,
        dataType: 'json',
        data: JSON.stringify(json),
        success: function (data) {
            $('#btnCancelCreate').click();
            LoadTable(null);
            ClearForm();
        },
        error: function (data) {
            FailureCallBack(data);
        }
    });
}

function DeleteVehicle(id) {
    $.ajax({
        url: 'http://localhost:8000/vehicles/' + id,
        type: 'DELETE',
        crossDomain: true,
        dataType: 'json',
        success: function (data) {
            LoadTable(null);
        },
        error: function (data) {
            FailureCallBack(data);
        }
    });
}

function ViewVehicle(id) {
    $('#hdVehicle').val(id);
    $.ajax({
        url: 'http://localhost:8000/vehicles/' + id,
        type: 'GET',
        crossDomain: true,
        dataType: 'json',
        success: function (data) {
            $('#txtMarkEdit').val(data.data.mark);
            $('#txtModelEdit').val(data.data.model);
            $('#txtColorEdit').val(data.data.color);
            $('#txtYearEdit').val(data.data.year);
            $('#txtPriceEdit').val(data.data.price);
            $('#txtDescriptionEdit').val(data.data.description);
            
            if (data.data.isNew) {
                $('#chkIsNewEdit').prop('checked', true);
            }
            else {
                $('#chkIsNewEdit').prop('checked', false);
            }            
        },
        error: function (data) {
            FailureCallBack(data);
        }
    });
}

function FailureCallBack(result) {
    alert("ERRO: " + result.status + ' ' + result.statusText);
}