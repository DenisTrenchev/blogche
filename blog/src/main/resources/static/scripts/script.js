$(".text").each(function() {
    $(this).html($(this).html().replace('\n', '<br />'));
});