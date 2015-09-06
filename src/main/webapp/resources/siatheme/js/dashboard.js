/**
 * Created by jose on 04/01/15.
 */

function checkAvailability(boardId, sensorId) {
    $.ajax({
        url : "sensorsget",
        dataType : "json",
        data : "boardId=" + boardId + "&sensorId=" + sensorId,
        success : function(data) {
           $("#" + sensorId + "-value").html(data);
        },
        error : function() {
            alert("error");
            $("#" + sensorId + "-value").html("XX");
        }
    });
}