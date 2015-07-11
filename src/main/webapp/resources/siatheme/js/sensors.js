var intervalId = 0;
intervalId = setInterval(checkAvailability, 3000);

$(document).ready(function() {
    checkAvailability();
});

function checkAvailability() {
    $.ajax({
        url : "sensorsget",
        dataType : "json",
        data : "id=" + $('#sensorId').text(),
        success : function(data) {
            $('#result').html(data);
        },
        error : function() {
            $('#result').html("Fail");
        }
    });
}