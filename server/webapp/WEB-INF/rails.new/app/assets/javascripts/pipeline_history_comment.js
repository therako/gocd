function initPipelineHistoryComment(jQuery, Modalbox, dashboardPeriodicalExecuter) {
    var my = {};

    my.showModal = function (pipelineName, pipelineLabel, pipelineCounter) {
        var div = jQuery('#comment-form-' + pipelineCounter)[0];
        var title = 'Comment on pipeline: ' + pipelineName + ' label: ' + pipelineLabel;

        Modalbox.show(div, { title: title });
    };

    my.submit = function (pipelineName, pipelineCounter) {
        var commentText = jQuery('#comment-input').val();
        var url = '/go/pipelines/' + pipelineName + '/' + pipelineCounter + '/comment';

        jQuery.post(
            url,
            { comment: commentText },
            my.onCommentSuccessCloseModalAndRefreshPipelineHistory,
            'json'
        );
    };

    my.onCommentSuccessCloseModalAndRefreshPipelineHistory = function () {
        Modalbox.hide();
        dashboardPeriodicalExecuter.fireNow();
    };

    return my;
}
