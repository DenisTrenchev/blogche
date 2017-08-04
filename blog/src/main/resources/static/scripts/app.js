$(document).ready(function(){
    function escapeHtml(str) {
        var div = document.createElement('div');
        div.appendChild(document.createTextNode(str));
        return div.innerHTML;
    }

    function loadComments(){
        var requestData = {};

        requestData[_csrf_param_name] = _csrf_token;

        $.ajax({url: window.location.href+'/getcomments',
            data: requestData,
            type: 'GET',
            success: function(response) {
                $("#comments").html("");
                $.each(response, function (index) {
                    if(this.posterPicture==""){
                        profilePicture = '<img class="commenter-pic" src="/pics/profile.png" height="40" style="display: inline; border-radius: 50%;"/>';
                    }else{
                        profilePicture = '<img class="commenter-pic" src="data:image/jpeg;base64,'+this.posterPicture+'" height="40" style="display: inline; border-radius: 50%;"/>';
                    }

                    $("#comments").append("<div class=\"comment-box\" id=\"" + this.id + "\">"
                        +profilePicture
                        +"<a class='commenter-name'> " + this.posterName + "</a>"
                        +"<p class='commenter-comment' style='margin-left: 40px;'>" + escapeHtml(this.content) + "</p>"
                        +"</div>"
                    );
                });
            },
            error: function () {
                alert("Something went wrong");
            }
        })
    }

    function postComment(content){

        var requestData = {
            content: content
        };

        requestData[_csrf_param_name] = _csrf_token;

        $.ajax({url: window.location.href+'/postcomment',
            data: requestData,
            type: 'POST',
            success: function(response) {
                $("#input-comment").val("");
                loadComments();
            },
            error: function () {
                alert("Please log in first");
            }
        });
    }

    loadComments();

    $("#submit-comment").click(function(){
        var content = $("#input-comment").val();
        if(content==""){
            alert("Can't post empty comment");
        }else {
            postComment(content);
        }
    });

    $("#load-comments").click(function(){
        loadComments();
    });

});