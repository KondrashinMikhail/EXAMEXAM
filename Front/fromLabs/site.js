var onFormSubmit = function(event) {
    event.preventDefault();
    var data = new FormData(event.target);
    console.log(data);
    const values = [...data.values()];
    console.log(values);
    let error = values.includes("") ? true : false;
    if (isNaN(+$("#phoneNumber").val()) || $("#phoneNumber").val().length !== 11) {
        Swal.fire({
            icon: 'error',
            text: 'Номер введен некорректно!'
        });
    } else if (error) {
        Swal.fire({
            icon: 'error',
            text: 'Вы заполнили не все поля!'
        });
    } else {
        Swal.fire({
            icon: 'success',
            text: 'Регистрация прошла успешно!'
        });
    }
}
document.getElementById('userData').addEventListener('submit', onFormSubmit);

var flag = false;

function isPasswordCheck() {
    var inputPassword = $("#name").val();
    var repeatPassword = $("#surname").val();

    if (inputPassword !== repeatPassword) {
        $("#divCheckPassword").html("Пароли не совпадают!").css('color', 'red');
        flag = true;
    } else {
        $("#divCheckPassword").html("Пароли совпадают").css('color', 'green');
        flag = false;
    }
}

$(document).ready(function() {
    $("#repeatPassword").keyup(isPasswordCheck);
});