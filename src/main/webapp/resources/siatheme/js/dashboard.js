/**
 * Created by jose on 04/01/15.
 */

function checkAvailability(id) {
    $.ajax({
        url : "sensorsget",
        dataType : "json",
        data : "id=" + id,
        success : function(data) {
           $("#" + id + "-value").html(data);
        },
        error : function() {
            alert("error");
            //$('#result').html("Fail");
        }
    });
}