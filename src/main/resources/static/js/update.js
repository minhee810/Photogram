// (1) 회원정보 수정
function update(userId, event) {

    event.preventDefault();  // form tag 액션을 막음

    let data = $("#profileUpdate").serialize();

    console.log(data);

    $.ajax({
        type:"put",
        url:`/api/user/${userId}`,
        data: data,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType: "json"
    }).done(res=>{
        console.log("update 성공");
        location.href=`/user/${userId}`;

    }).fail(errors=>{
        console.log("update 실패");
    });
}