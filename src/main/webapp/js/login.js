$(function(){
    $('#username').focus(function(){
        $('.warning1').html('');
        $('.warning2').html('');
    });
    $('#password').focus(function(){
        $('.warning1').html('');
        $('.warning2').html('');
    });

    $('#submit').click(function(){
        var username = $('#username').val();
        var password = $('#password').val();

        $.post("../user/login.action", { username: username, password: password },
            function(data){
                if(data.username!=null){
                    $('.warning1').html(data.username);
                }
                if(data.password!=null){
                    $('.warning2').html(data.password);
                }
                if(data.redirect){
                    sessionStorage.setItem("user",username);
                    $(location).attr('href', '../index.html');
                }

            });
    });
});