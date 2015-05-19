/**
 * Created by swift-seeker-89717 on 19.05.2015.
 */
$(document).ready(function () {
    var start = $("#startPrice");
    var end = $("#endPrice");
    var startWeight = $("#startWeigh");
    var endWeight = $("#endWeight");
    $('#redLevel').jqxSlider({
        theme: "black",
        min: 0,
        max: 20000,
        step: 200,
        ticksFrequency: 1000,
        mode: 'fixed',
        values: [0, 20000],
        rangeSlider: true,
        width: "90%"
    });
    $('#greenLevel').jqxSlider({
        theme: "black",
        min: 0,
        max: 10.0,
        step: 0.4,
        ticksFrequency: 0.6,
        mode: 'fixed',
        values: [0, 10.0],
        rangeSlider: true,
        width: "90%"
    });
    $('#redLevel').on('change', function (event) {
        start.val($('#redLevel').jqxSlider('value').rangeStart);
        end.val($('#redLevel').jqxSlider('value').rangeEnd);
    });
    $('#greenLevel').on('change', function (event) {
        startWeight.val($('#greenLevel').jqxSlider('value').rangeStart);
        endWeight.val($('#greenLevel').jqxSlider('value').rangeEnd);
    });
    $('#blueLevel').on('change', function (event) {
        setColor();
    });

})
;