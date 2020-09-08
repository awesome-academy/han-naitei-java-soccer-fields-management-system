$(document).ready(() => {
   $('#fromDate').datetimepicker({
        step: 15,
        minTime: '9:00',
        maxTime: '21:15'
    });
    $('#toDate').datetimepicker({
        step: 15,
        minTime: '9:00',
        maxTime: '21:15'
    });
});