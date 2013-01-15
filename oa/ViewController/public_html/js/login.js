function newCookie(name, value, days) {
    var days = 30;// the number at the left reflects the number of days for the cookie to last
    // modify it according to your needs
    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        var expires = "; expires=" + date.toGMTString();
    }
    else var expires = "";
    document.cookie = name + "=" + value + expires + "; path=/";
}

function readCookie(name) {
    var nameSG = name + "=";
    var nuller = '';
    if (document.cookie.indexOf(nameSG) ==  - 1)
        return nuller;

    var ca = document.cookie.split(';');
    for (var i = 0;i < ca.length;i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ')
            c = c.substring(1, c.length);
        if (c.indexOf(nameSG) == 0)
            return c.substring(nameSG.length, c.length);
    }
    return null;
}

function eraseCookie(name) {
    newCookie(name, "", 1);
}

function toMem(a) {
    newCookie('userName', document.getElementById('userid').value);// add a new cookie as shown at left for every
    newCookie('passwd', document.getElementById('password').value);
    newCookie('rem', document.getElementById('rememberMe').checked);// field you wish to have the script remember
}

function delMem(a) {
    eraseCookie('userName');// make sure to add the eraseCookie function for every field
    eraseCookie('passwd');
    eraseCookie('rem');

    //document.getElementById('userid').value = '';// add a line for every field
    //document.getElementById('password').value = '';
    //document.getElementById('rememberMe').checked = false;
}

function remCookie() {
    document.getElementById('userid').value = readCookie("userName");
    document.getElementById('password').value = readCookie("passwd");
    document.getElementById('rememberMe').checked = readCookie("rem");
}

// Multiple onload function created by: Simon Willison
// http://simon.incutio.com/archive/2004/05/26/addLoadEvent
function addLoadEvent(func) {
    var oldonload = window.onload;
    if (typeof window.onload != 'function') {
        window.onload = func;
    }
    else {
        window.onload = function () {
            if (oldonload) {
                oldonload();
            }
            func();
        }
    }
}

addLoadEvent(function () {
    remCookie();
});

function submit() {
    var form = document.getElementById('form1');
    if (validation()) {
        
        if (document.getElementById('rememberMe').checked) {
            toMem();
        }
        else {
            delMem();
        }
        
        form.submit();

    }
}

function validation() {
    var name = document.getElementById('userid').value;
    var pass = document.getElementById('password').value;

    if (name.length <= 0) {
        document.getElementById('usernamespan').innerHTML = "<span style='color:red;font-size:15px;'>* 请输入用户名</span>";
        return false;
    }
    else {
        document.getElementById('usernamespan').innerHTML = "";
    }

    if (pass.length <= 0) {
        document.getElementById('passwordspan').innerHTML = " <span style='color:red;font-size:15px;'> * 请输入密�?/span>";
        return false;
    }
    else {
        document.getElementById('passwordspan').innerHTML = "";
    }

    return true;
}