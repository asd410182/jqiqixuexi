var endResult;
window.onload = function(){
    let EditInfo = document.querySelector("#left");
    EditInfo.onclick = function(){
        window.location.href="/company/jumpToCompanyData?cid="+cid;
    };

    let radios = document.querySelectorAll("input[type='radio']");
    for (const val of radios) {
        if(val.checked == true){
            val.parentElement.style.borderColor = "#53cac3";
        }
    }
    $.ajax({
        type: "post",
        url: "/company/showTheData",
        data:{
            "cid":cid
        },
        success: function (result) {
            endResult = result[5];
            console.log(result);
            console.log(result[0]);//公司信息
            console.log(result[1]);//发布职位数量
            console.log(result[2]);//收到的简历数量
            console.log(result[3]);//还未通过的简历数量
            console.log(result[4]);//已经通过简历的数量
            console.log(result[5]);//职位列表
            console.log(result[6]);//职位对应未处理简历的数量
        },
        error: function(){
            alert("服务器跑到火星去了，请稍后再试！");
        }
    });
};
function select(o){
    let id = o.getAttribute("for");
    //这里要拿到选中的单选框的id
    let bro = o.previousElementSibling;
    let radios = document.querySelectorAll(`input[name=${bro.name}]`);
    for (const val of radios) {
        if(val.id != id){
            val.parentElement.style.borderColor = "#9fa3b0";
            continue;
        }
        val.parentElement.style.borderColor = "#53cac3";
    }
}