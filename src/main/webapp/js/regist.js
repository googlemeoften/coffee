$(function () {

    $('#username').focus(function(){
        $('.warning1').html('');
    });
    $('#password').focus(function(){
        $('.warning2').html('');
        $('.warning3').html('');
    });
    $('#repassword').focus(function(){
        $('.warning3').html('');
    });

    $('#phone').focus(function(){
        $('.warning4').html('');
    });

    $('#repassword').blur(function () {
        var password = $('#password').val();
        var repassword = $('#repassword').val();

        if (password != repassword) {
            $('.warning3').html("两次输入密码不一致");
        }
    });

    $('#submit').click(function () {
        var username = $('#username').val();
        var password = $('#password').val();
        var phone = $('#phone').val();


        $.post("../user/register.action", {username: username, password: password, phone: phone},
            function (data) {
                if (data.username != null) {
                    $('.warning1').html(data.username);
                }
                if (data.password != null) {
                    $('.warning2').html(data.password);
                }

                if (data.phone != null) {
                    $('.warning4').html(data.phone);
                }
                if (data.redirect) {
                    sessionStorage.setItem("user", username);
                    $(location).attr('href', '../index.html');
                }

            });
    });
});