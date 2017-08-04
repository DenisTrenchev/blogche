$(document).ready(function(){
    function postComment(content){

        var requestData = {
            content: content
        };

        requestData[_csrf_param_name] = _csrf_token;

        console.log(requestData);

        $.ajax({url: window.location.href+'/postcomment',
            data: requestData,
            type: 'POST',
            dataType: "json",
            success: function(output) {
                console.log(output);
            }
        });
    }

    $("#submit-comment").click(function(){
        var content = $("#input-comment").val();

        postComment(content);
    });

    $("#load-comments").click(function(){
        var requestData = {};

        requestData[_csrf_param_name] = _csrf_token;

        $.ajax({url: window.location.href+'/getcomments',
            data: requestData,
            type: 'GET',
            success: function(response) {
                console.log(response);

                $("#comments").html("");
                $.each(response, function (index) {
                    if(this.posterPicture==""){
                        profilePicture = '<img class="commenter-pic" src="/pics/profile.png" height="40" style="display: inline; border-radius: 50%;"/>';
                    }else{
                        profilePicture = '<img class="commenter-pic" src="data:image/jpeg;base64,'+this.posterPicture+'" height="40" style="display: inline; border-radius: 50%;"/>';
                    }

                    $("#comments").append("<div class=\"comment-box\" id=\"" + this.id + "\">"
                        +profilePicture
                        +"<a class='commenter-name'>" + this.posterName + "</a>"
                        +"<p class='commenter-comment' style='margin-left: 40px;'>" + this.content + "</p>"
                        +"</div>"
                    );
                });
            },
            error: function () {
                alert("Please log in");
            }
        })
    });



});