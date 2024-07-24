var baseUrl = window.location.protocol + "//" + window.location.host + "/";


$(function () {
    // var width = $(".fTnfND").width();
    // var left = $(".fTnfND").offset().left - 48;
    // $(".ktYdPx").css({"width": width + "px", "left": left + "px", "display": "block"})
})

$(document).ready(function () {
    // 是否登录
    var loginFlag = true;
    // 未登录，显示遮罩层
    if (!loginFlag) {
        $(".col-section-main-mask").addClass("col-section-main-mask-show");
    }

    // 第一行
    // 加载表头
    var body = '<div class="col-section-main-table-header col-section-main-table-row sc-jdhwqr sc-iLOkMM bMMZGs iYxCeC">';
    var tableHeader = ["Name", "APY (24H)", "APY (7D)", "IL(7D)", "Volume", "TVL", "Pool Token Holders", "Holders", "Ownership&", "Txs(7D)"]
    var index = 0;
    for (const title of tableHeader) {
        var template = template_table_header;
        template = template.replace("{{title}}", title);
        template = template.replace("{{index}}", index++);
        var fixedValue = "";
        if (index === 0) {
            fixedValue = "data-fixed=\"true\"";
        }
        template = template.replace("{{fixed}}", fixedValue);
        body += template;
//        $("#one-one-section .col-section-main-table-header").append(template);
    }
    body += '</div>';

    var grid_template_columns = "";
    for (let i = 0; i < tableHeader.length; i++) {
        grid_template_columns += "auto ";
    }
    $("#one-one-section").css({"grid-template-columns": grid_template_columns});
    // 加载数据
    function oneOneSectionLoadData() {
            var bodyNew = body;
            var template_1_1_column = '';

            for (var i = 0; i < tableHeader.length; i++) {
                var template = template_1_1_column_temp;

                template = template.replace("{{value}}", tableHeader[i]);
                template = template.replace("{{columnIndex}}", i);
                template_1_1_column += template
            }

            queryData(baseUrl+"showApyDataList", {}, function (resp) {
                resp = $.parseJSON(resp);
                if (!resp || !resp.length){
                    console.log("获取列表为空")
                    return
                }
                $("#one-one-section").find(".col-section-main-table-item").remove()

                for (const r of resp) {
                    var template = template_1_1_column;

                    template = template.replace(tableHeader[0], r.name.toUpperCase());
                    template = template.replace(tableHeader[1], r.apy.net_apy.toFixed(6));
                    template = template.replace(tableHeader[2], (r.apy.net_apy*7).toFixed(6));
                    template = template.replace(tableHeader[3], generateRandomPercentage());
                    template = template.replace(tableHeader[4], r.apy.net_apy.toFixed(6));
                    template = template.replace(tableHeader[5], r.tvl.tvl.toFixed(6));
                    template = template.replace(tableHeader[6], generateRandomNumber());
                    template = template.replace(tableHeader[7], generateRandomNumber());
                    template = template.replace(tableHeader[8], generateRandomPercentage());
                    template = template.replace(tableHeader[9], generateRandomNumber());



                    var template_row = template_1_1_row;
                    template_row = template_row.replace("{{body}}", template);
    //                $("#one-one-section").append(template_row)
    //                $("#1_1_row").append(template)
                    bodyNew += template_row;
                }
                $("#one-one-section").html(bodyNew)
            });
        }
    oneOneSectionLoadData();
    setInterval(oneOneSectionLoadData, 2000);
    // 第二行
    // 加载表头
//    var towOneSectionTableHeader = ["Network", "Name", "Balance", "Pool Token Holders", "Holders", "Ownership", "Txs (7D)",  "Txs (7D)", "First Seen"]
//    var index = 0;
//    for (const title of towOneSectionTableHeader) {
//        var template = template_table_header;
//        template = template.replace("{{title}}", title);
//        template = template.replace("{{index}}", index++);
//        var fixedValue = "";
//        if (index === 0) {
//            fixedValue = "data-fixed=\"true\"";
//        }
//        template = template.replace("{{fixed}}", fixedValue);
//        $("#two-one-section .col-section-main-table-header").append(template);
//    }
//    var grid_template_columns2 = "";
//    for (let i = 0; i < towOneSectionTableHeader.length; i++) {
//        grid_template_columns2 += "auto ";
//    }
//    $("#two-one-section").css({"grid-template-columns": grid_template_columns2});
//    // 加载数据
//    function twoOneSectionLoadData() {
//        queryData("data-3-1.json", {}, function (resp) {
//            if (!resp || !resp.data || !resp.data.length){
//                console.log("获取列表为空")
//                return
//            }
//            // $("#two-one-section").find(".col-section-main-table-item").remove()
//            // for (const r of resp.data) {
//            //     var template = template_3_1;
//            //     template = template.replace("{{headline}}", r.headline);
//            //     template = template.replace("{{date}}", r.date);
//            //     $("#tow-one-section").append(template)
//            // }
//        });
//    }
//    twoOneSectionLoadData();
});

function showConditionDialog() {
    $("#dialog-windows").removeClass("hide");
    // $(".layui-nav-bar").css({"display": "none"})
}

function hideConditionDialog() {
    $("#dialog-windows").addClass("hide");
}

var template_table_header =
"<div class=\"col-section-main-table-header-col sc-ezHhwS bpEKFQ\" data-target=\"cell\" {{fixed}} data-column=\"{{index}}\">\n" +
"    <div class=\"sc-dYtuZ iqUusz\">\n" +
"        <div data-target=\"inner\" data-type=\"wallet\" class=\"sc-kBzgEd iMgJry\" style=\"max-width: 250px;\">\n" +
"            <span data-target=\"formatted\" class=\"sc-gJbFto gFORgR\" style=\"max-width: 250px;\">\n" +
"                <span>\n" +
"                    <span class=\"\">{{title}}</span>\n" +
"                </span>\n" +
"            </span>\n" +
"        </div>\n" +
"    </div>\n" +
"</div>";

var template_1_1_row =
    "<div data-row=\"row-0\" data-idx=\"0\" class=\"sc-jdhwqr sc-fkJVfC sc-iuqRDJ kCuHoQ cpknaP fEywaC\">\n" +
    "    {{body}}\n" +
    "</div>";
var template_1_1_link_column =
    "<a data-target=\"cell\" data-column=\"{{columnIndex}}\" data-fixed=\"true\" role=\"button\" data-cy=\"tbl-defi-paradise-liquidity-pool-body-row-0-col-0\" href=\"{{link}}\" rel=\"noopener noreferrer\" target=\"_blank\" class=\"sc-ezHhwS gKlIHZ\">\n" +
    "    <div class=\"sc-dYtuZ iqUusz\">\n" +
    "        <div data-target=\"inner\" data-type=\"wallet\" class=\"sc-kBzgEd eUNGNJ\" style=\"max-width: 250px;\">\n" +
    "\t\t\t\t<span data-target=\"formatted\" class=\"sc-gJbFto iNyjQm\" style=\"max-width: 250px;\">\n" +
    "\t\t\t\t\t<span>\n" +
    "\t\t\t\t\t\t<span class=\"\">{{value}}</span>\n" +
    "\t\t\t\t\t</span>\n" +
    "\t\t\t\t</span>\n" +
    "        </div>\n" +
    "    </div>\n" +
    "</a>";
var template_1_1_column_temp =
    "<div data-target=\"cell\" data-column=\"{{columnIndex}}\" data-cy=\"tbl-defi-paradise-liquidity-pool-body-row-0-col-1\"\n" +
    "     class=\"sc-ezHhwS cyzOVl\">\n" +
    "    <div class=\"sc-dYtuZ iqUusz\">\n" +
    "        <div data-target=\"inner\" data-type=\"default\" class=\"sc-kBzgEd eUNGNJ\" style=\"max-width: 250px;\">\n" +
    "\t\t\t\t<span data-target=\"formatted\" class=\"sc-gJbFto iNyjQm\" style=\"max-width: 250px;\">\n" +
    "\t\t\t\t\t<span>\n" +
    "\t\t\t\t\t\t<span class=\"\">{{value}}</span>\n" +
    "\t\t\t\t\t</span>\n" +
    "\t\t\t\t</span>\n" +
    "        </div>\n" +
    "    </div>\n" +
    "</div>";


// var template_3_1 = "<div class=\"col-section-main-table-item col-section-main-table-row\">\n" +
//     "                          <div class=\"col-section-main-table-header-col\">\n" +
//     "                              <div class=\"col-content\">\n" +
//     "                                  <span class=\"formatted\">\n" +
//     "                                      <span>\n" +
//     "                                          <span class=\"\">{{headline}}</span>\n" +
//     "                                      </span>\n" +
//     "                                  </span>\n" +
//     "                              </div>\n" +
//     "                          </div>\n" +
//     "                          <div class=\"col-section-main-table-header-col-2\">\n" +
//     "                              <div class=\"col-content\">\n" +
//     "                                  <span class=\"formatted\">\n" +
//     "                                      <span class=\"\">{{date}}</span>\n" +
//     "                                  </span>\n" +
//     "                              </div>\n" +
//     "                          </div>\n" +
//     "                      </div>";

//查询列表数据
function queryData(url, data, success) {
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        dateType: "json",
        contentType: "application/json",
        success: function(resp){
            success(resp)
        },
        error: function () {
            console.log("send request error...")
        }
    })
}


function generateRandomPercentage() {
  var min = 1.00;
  var max = 5.00;
  var randomNum = (Math.random() * (max - min) + min).toFixed(2);
  return randomNum + "%";
}

function generateRandomNumber() {
  var min = 10000;
  var max = 50000;
  var randomNum = Math.floor(Math.random() * (max - min + 1)) + min;
  return randomNum;
}


