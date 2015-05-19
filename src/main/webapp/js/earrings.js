/**
 * Created by swift-seeker-89717 on 19.05.2015.
 */
var currentPage = 1;
var pages;
var next = $(".next");
var previous = $(".previous");
var container = $(".container-right");
var incorrectFilterMessage = $("#incorrectFilter");
$.ajax({
    url: "earrings",
    method: "post",
    dataType: "json",
    success: function (data) {
        parseData(data, container)
    }
});

$("#search").click(function () {
    rewriteProduct(currentPage);
});

function parseData(data, container) {
    currentPage = data.currentPage;
    pages = data.noOfPages;
    $("#pageNumber").text(currentPage);
    for (var i = 0; i < data.products.length; i++) {
        $("#incorrectFilter").hide("slow");
        var d = data.products[i];
        var product = new ProductObject(d.id, d.image.url, d.title, d.price).build();
        container.append(product);
        container.show("slow");
    }
    if (pages >= currentPage) {
        next.attr("disabled", true);
    }
    if (currentPage <= 1) {
        previous.attr("disabled", true)
    }
    else {
        previous.removeAttr("disabled")
    }
    if (currentPage == pages) {
        next.attr("disabled", true)
    } else {
        next.removeAttr("disabled")
    }
}
function rewriteProduct(currentPage) {
    var container = $(".container-right");
    container.empty();
    container.hide();
    var startPrice = $("#startPrice").val();
    var endPrice = $("#endPrice").val();
    var startWeight = $("#startWeigh").val();
    var endWeight = $("#endWeight").val();
    var material = $("#material").val();
    var insert = $("#insert").val();
    var sortType = $("#sortType").val();
    $.ajax({
        url: "earrings",
        method: "post",
        dataType: "json",
        data: {
            "minPrice": startPrice,
            "maxPrice": endPrice,
            "minWeight": startWeight,
            "maxWeight": endWeight,
            "insert": insert,
            "material": material,
            "sortType": sortType,
            "page": currentPage
        },
        success: function (data) {
            if (data.products.length < 1) {
                next.attr("disabled", true);
                previous.attr("disabled", true);
                container.append(incorrectFilterMessage);
                container.show("slow");
                incorrectFilterMessage.show("slow");
                return;
            }
            parseData(data, container)
        }
    })
}
next.click(function () {
    rewriteProduct(currentPage + 1)
});
previous.click(function () {
    rewriteProduct(currentPage - 1)
});
function chooseInsert(insertId) {
    var container = $(".container-right");
    $("#insert").val(insertId);
    container.empty();
    container.hide();
    var sortType = $("#sortType").val();
    $.ajax({
        url: "earrings",
        method: "post",
        dataType: "json",
        data: {
            "sortType": sortType,
            "insert": insertId
        },
        success: function (data) {
            next.attr("disabled", true);
            previous.attr("disabled", true);
            if (data.noOfPages < 1) {
                container.append(incorrectFilterMessage);
                container.show("slow");
                incorrectFilterMessage.show("slow");
                return;
            }
            parseData(data, container)
        }
    })
}
function removeActiveClass(object) {
    var active = "active";
    $(".diamonds").removeClass(active);
    $(".emerald").removeClass(active);
    $(".fianit").removeClass(active);
    $(".ruby").removeClass(active);
    $(".none").removeClass(active);
    object.addClass("active");
}
$(".diamonds").click(function () {
    removeActiveClass($(this));
    chooseInsert(1);
});
$(".emerald").click(function () {
    removeActiveClass($(this));
    chooseInsert(2);
});
$(".fianit").click(function () {
    removeActiveClass($(this));
    chooseInsert(4);
});
$(".ruby").click(function () {
    removeActiveClass($(this));
    chooseInsert(3);
});
$(".none").click(function () {
    removeActiveClass($(this));
    chooseInsert(7);
});