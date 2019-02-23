window.onload(function () {
   var newScript=document.createElement('script');
   newScript.setAttribute('type','text/javascript');
   newScript.setAttribute('src','./jquery-2.1.4.min.js');
   document.body.appendChild(newScript);
});

function readOnly(id,name,phone,email) {
    console.log(id,name,phone,email);
    document.seeUser.loginname.value=name;
    document.seeUser.phone.value=phone;
    document.seeUser.email.value=email;
}

function addUser() {
    return true;
};
function editUser() {
    var pwd1=document.seeUser.password.value;
    var pwd2=document.seeUser.new_password.value;
    return true;
};