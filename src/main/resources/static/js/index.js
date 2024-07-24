var baseUrl = window.location.protocol + "//" + window.location.host + "/";
//var baseUrl = "https://analysis.thefizz.io/";

function table1_1() {
    // 第一行第一列
    queryData(baseUrl+"showNFTData", {}, function ( resp) {
        resp = $.parseJSON(resp);
        if (!resp || !resp || !resp.length){
            console.log("获取列表为空");
            return;
        }
        var max_num = resp[0].volume;
        var templateAdd = template_1_1_header;
        for (const r of resp) {
            var template = template_1_1;
            var volumePercent = (r.volume / max_num)* 100;
            if ((r.volume / max_num)* 100 != 2 && (r.volume / max_num)* 100 != 0){
                volumePercent += 8;
            }
            template = template.replace("{{name}}", r.name.toUpperCase());
            template = template.replace("{{title}}", r.name.toUpperCase());
            template = template.replace("{{volume}}", addCommas((r.volume), 6) );
            template = template.replace("{{percentage}}", volumePercent + "%" );
            templateAdd += template;
        }
        $("#one-one-section").html(templateAdd);
    })
}

function table1_2() {
    // 第一行第二列，图表
            var okxJson;
            var binanceJson;
            var byBitJson;
            var timeArray = "";
            queryData(baseUrl+"showDerivativesDataForFlow", {}, function (resp) {
                resp = $.parseJSON(resp);
                if (!resp || !resp.length){
                    console.log("获取列表为空");
                    return;
                }
                try {
                    okxJson = $.parseJSON(resp[0]).okx.split(",");
                    binanceJson = $.parseJSON(resp[1]).binance.split(",");
                    byBitJson = $.parseJSON(resp[2]).byBit.split(",");
                    timeArray = $.parseJSON(resp[3]).dateList.split(",");
                } catch (error) {
                  console.error(error);
                }


            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('echart-main'));

            // 指定图表的配置项和数据
            var option = {
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },
                legend: {
                    data: ['OKX', 'BINANCE', 'BYBIT'],
                    // 设置文本为红色
                    textStyle: {
                        color: '#ffffff'
                    }
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: [
                    {
                        type: 'category',
                        axisTick: {
                            show: false
                        },
                        data: timeArray
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
//                        ,
//                        min:function(value){
//                            return value.min;
//                        }
                    }
                ],
                series: [
                    {
                        name: 'OKX',
                        type: 'bar',
                        stack: 'Total',
                        label: {
                            show: false,
                            position: 'inside'
                        },
                        emphasis: {
                            focus: 'series'
                        },
                        data: okxJson
                    },
                    {
                        name: 'BINANCE',
                        type: 'bar',
                        stack: 'Total',
                        label: {
                            show: false
                        },
                        emphasis: {
                            focus: 'series'
                        },
                        data: binanceJson
                    },
                    {
                        name: 'BYBIT',
                        type: 'line',
                        symbol: 'none',
                        label: {
                            show: false
                        },
                        emphasis: {
                            focus: 'series'
                        },
                        data: byBitJson
                    },
                ]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        })


}

function table2_2(){
      // 第二行第二列，图表
            var VNDJson;
            var IDRJson;
            var SATSJson;
            var MMKJson;
            var KRWJson;
            var timeArray = "";
            queryData(baseUrl+"showGlobalDataForFlow", {}, function (resp) {
                resp = $.parseJSON(resp);
                if (!resp || !resp.length){
                    console.log("获取列表为空");
                    return;
                }
                try {
                    VNDJson = $.parseJSON(resp[0]).VND.split(",");
                    IDRJson = $.parseJSON(resp[1]).IDR.split(",");
                    SATSJson = $.parseJSON(resp[2]).SATS.split(",");
                    MMKJson = $.parseJSON(resp[3]).MMK.split(",");
                    KRWJson = $.parseJSON(resp[4]).KRW.split(",");
                    timeArray = $.parseJSON(resp[5]).dateList.split(",");

                } catch (error) {
                  console.error(error);
                }


            // 基于准备好的dom，初始化echarts实例
            var myChart2 = echarts.init(document.getElementById('echart-main2'));

            // 指定图表的配置项和数据
            var option = {

                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },
                legend: {
                    data: ['VND', 'IDR', 'SATS', 'MMK', 'KRW'],
                    // 设置文本为红色
                    textStyle: {
                        color: '#ffffff'
                    }
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: [
                    {
                        type: 'category',
                        axisTick: {
                            show: false
                        },
                        data: timeArray
                    }

                ],
                yAxis: [
                    {
                        type: 'value'
//                        ,
//                        min:function(value){
//                            return value.min;
//                        }
                    }
                ],
                series: [
                    {
                        name: 'VND',
                        type: 'line',
                        stack: 'Total',
                        label: {
                            show: false,
                            position: 'inside'
                        },
                        emphasis: {
                            focus: 'series'
                        },
                        data: VNDJson
                    },
                    {
                        name: 'IDR',
                        type: 'line',
                        stack: 'Total',
                        label: {
                            show: false
                        },
                        emphasis: {
                            focus: 'series'
                        },
                        data: IDRJson
                    },
                    {
                        name: 'SATS',
                        type: 'line',
                        symbol: 'none',
                        label: {
                            show: false
                        },
                        emphasis: {
                            focus: 'series'
                        },
                        data: SATSJson
                    }
                    ,{
                          name: 'MMK',
                          type: 'line',
                          symbol: 'none',
                          label: {
                              show: false
                          },
                          emphasis: {
                              focus: 'series'
                          },
                          data: MMKJson
                      },{
                        name: 'KRW',
                        type: 'line',
                        symbol: 'none',
                        label: {
                            show: false
                        },
                        emphasis: {
                            focus: 'series'
                        },
                        data: KRWJson
                    },
                ]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart2.setOption(option);
        })
}

function login(){
    // 是否登录
    var loginFlag = false;
    // 未登录，显示遮罩层
//    if (!loginFlag) {
//        $(".col-section-main-mask").addClass("col-section-main-mask-show");
//    }



    // 第一行第三列
    queryData(baseUrl+"showDerivativesData", {}, function (resp) {
        resp = $.parseJSON(resp);

        var max_num = resp[0].price;
        if (!resp || !resp.length){
            console.log("获取列表为空");
            return;
        }
        var templateAdd = template_1_3_header;
        for (const r of resp) {
            var template = template_1_3;
            var volumePercent = (r.price / max_num)* 100;

            template = template.replace("{{name}}", r.market.toUpperCase()+"  ("+r.symbol+")");
            template = template.replace("{{title}}", r.market.toUpperCase()+"  ("+r.symbol+")");
            template = template.replace("{{volume}}", addCommas(parseFloat(r.price), 2));
            template = template.replace("{{percentage}}", volumePercent + "%");
            templateAdd += template;
        }
        $("#one-three-section").html(templateAdd);
    })



    // 第二行第一列
    queryData(baseUrl+"showApyData", {}, function (resp) {
        resp = $.parseJSON(resp);
        var max_num = resp[0].apy.net_apy;
        if (!resp || !resp.length){
            console.log("获取列表为空");
            return;
        }
        var templateAdd = template_2_1_header;
        for (const r of resp) {
            var template = template_2_1;
            var volumePercent = (r.apy.net_apy / max_num)* 100;
            if (volumePercent < 2 && volumePercent != 0){
                volumePercent += 5;
            }
            template = template.replace("{{name}}", r.name.toUpperCase());
            template = template.replace("{{title}}", r.name.toUpperCase());
            template = template.replace("{{volume}}", r.apy.net_apy.toFixed(6));
            template = template.replace("{{percentage}}", volumePercent + "%");
            templateAdd += template;
        }
        $("#two-one-section").html(templateAdd);

    })
    // 第二行第三列
    queryData(baseUrl+"showTvlData", {}, function (resp) {
            resp = $.parseJSON(resp);
            var max_num = resp[0].tvl.tvl;
            if (!resp || !resp.length){
                console.log("获取列表为空");
                return;
            }
            var templateAdd = template_2_3_header;
            for (const r of resp) {
                var template = template_2_3;
                var volumePercent = (r.tvl.tvl / max_num)* 100;
    //            if ((r.price / max_num)* 100 < 50 && (r.price / max_num)* 100 != 0){
    //                volumePercent += 8;
    //            }
                template = template.replace("{{name}}", r.name.toUpperCase());
                template = template.replace("{{title}}", r.name.toUpperCase());
                template = template.replace("{{volume}}", r.tvl.tvl.toFixed(6));
                template = template.replace("{{percentage}}", volumePercent + "%");
                templateAdd += template;
            }
            $("#two-three-section").html(templateAdd)

    })

}

function getTmp_3_1(){
     // 第三行
        // 当前页码
        var pageNo = 1;
        // 每页数量
        var pageSize = 10;
        // 总条数
        var total = 0;
        // 总页数
        var pages = 0
        function loadData() {
        queryData(baseUrl+"showNewsData", {"page":pageNo}, function (resp) {
            resp = $.parseJSON(resp);
            if (!resp){
                console.log("获取列表为空");
                return;
            }
            $("#three-one-section").find(".col-section-main-table-item").remove()
            for (const r of resp.articles) {
                var template = template_3_1;
                var str = r.title;
                if (str.length > 180) {
                  str = str.substring(0, 180) + " ……";
                }
                template = template.replace("{{headline}}", str);
                template = template.replace("{{date}}", r.publishedAt);
                template = template.replace("{{link}}", r.url);
                $("#three-one-section").append(template)
            }
            // 总条数
            total = resp.totalResults;
            // 总页数
            pages = parseInt(total / pageSize) ;
            if (total % pageSize > 0) {
                pages++;
            }
            if (pages > 1) {
                var optionHtml = "";
                for (let i = 0; i < pages; i++) {
                    optionHtml += "<option id=\"page-option-"+ (i+1) +"\" value=\"1\" class=\"page-select-option\">"+(i+1)+"</option>";
                }
                $("#pages-select").html(optionHtml);
                // $(".page-select-option").remove()
                $("#page-option-" + pageNo).attr("selected", "selected")
            }
            $("#pages").html(pages);
            $("#total").html(total);
            var lastIndex = pageNo * pageSize
            if (lastIndex > total) {
                lastIndex = total
            }
            $("#start-index").html(pageNo * pageSize - pageSize + 1);
            $("#end-index").html(lastIndex);
        });
        }
            // 初始化
            loadData();
            $("#prev-page").click(function () {            console.log("上一页。")
                pageNo--;
                if (pageNo < 1) {
                    console.log("已经是第一页")
                    pageNo = 1;
                    return;
                }
                loadData();
            })
            $("#next-page").click(function () {
                console.log("下一页。pageNo", pageNo, "pages", pages)
                pageNo++;
                if (pageNo > pages) {
                    console.log("已经是最后一页")
                    pageNo = pages;
                    return;
                }
                loadData();
            })
}

$(document).ready(function () {
    table1_1();
    table1_2();
    table2_2();
    login();
    getTmp_3_1();
});

var template_1_1_header = '<div class="col-section-main-table-header col-section-main-table-row">' +
                '<div class="col-section-main-table-header-col">' +
                    '<div class="col-content">' +
                        '<span class="formatted">' +
                            '<span>' +
                                '<span class="">NFT Collection</span>' +
                            '</span>' +
                        '</span>' +
                    '</div>' +
                '</div>' +
                '<div class="col-section-main-table-header-col-2">' +
                    '<div class="col-content">' +
                        '<span class="formatted">' +
                            '<span class="">Volume (24H)</span>' +
                        '</span>' +
                    '</div>' +
                '</div>' +
            '</div>';

var template_1_1 =  "<div class=\"col-section-main-table-item col-section-main-table-row\">\n" +
    "                          <div class=\"col-section-main-table-header-col\">\n" +
    "                              <div class=\"col-content\">\n" +
    "                                  <span class=\"formatted formatted2\">\n" +
    "                                      <span>\n" +
    "                                          <span style='max-width: 140px;' class=\"over-a\" title=\"{{title}}\">{{name}}</span>\n" +
    "                                      </span>\n" +
    "                                  </span>\n" +
    "                              </div>\n" +
    "                          </div>\n" +
    "                          <div class=\"col-section-main-table-header-col-2\">\n" +
    "                              <div class=\"col-content\">\n" +
    "                                  <span class=\"formatted\">\n" +
    "                                      <span class=\"\">{{volume}}</span>\n" +
    "                                  </span>\n" +
    "                              </div>\n" +
    "                              <div class=\"sparkline\">\n" +
    "                                  <div class=\"sparkline-bar\" style=\"width: {{percentage}};\"></div>\n" +
    "                              </div>\n" +
    "                          </div>\n" +
    "                      </div>";

var template_1_3_header = '<div class="col-section-main-table-header col-section-main-table-row">' +
                    '<div class="col-section-main-table-header-col">' +
                        '<div class="col-content">' +
                                '<span class="formatted">' +
                                    '<span>' +
                                        '<span class="">Name</span>' +
                                    '</span>' +
                                '</span>' +
                        '</div>' +
                    '</div>' +
                    '<div class="col-section-main-table-header-col-2">' +
                        '<div class="col-content">' +
                                '<span class="formatted">' +
                                    '<span class="">Price(24H)</span>' +
                                '</span>' +
                        '</div>' +
                    '</div>' +
                '</div>';

var template_1_3 = "<div  name=\"template_add\" class=\"col-section-main-table-item col-section-main-table-row\">\n" +
    "                          <div class=\"col-section-main-table-header-col\">\n" +
    "                              <div class=\"col-content\">\n" +
    "                                  <span class=\"formatted formatted2\">\n" +
    "                                      <span>\n" +
    "                                          <span style='max-width: 140px;' class=\"over-a\" title=\"{{title}}\">{{name}}</span>\n" +
    "                                      </span>\n" +
    "                                  </span>\n" +
    "                              </div>\n" +
    "                          </div>\n" +
    "                          <div class=\"col-section-main-table-header-col-2\">\n" +
    "                              <div class=\"col-content\">\n" +
    "                                  <span class=\"formatted\">\n" +
    "                                      <span class=\"\">{{volume}}</span>\n" +
    "                                  </span>\n" +
    "                              </div>\n" +
    "                              <div class=\"sparkline\">\n" +
    "                                  <div class=\"sparkline-bar\" style=\"width: {{percentage}};\"></div>\n" +
    "                              </div>\n" +
    "                          </div>\n" +
    "                      </div>";

var template_2_1_header = '<div class="col-section-main-table-header col-section-main-table-row">' +
                    '<div class="col-section-main-table-header-col">' +
                        '<div class="col-content">' +
                                '<span class="formatted">' +
                                    '<span>' +
                                        '<span class="">Name</span>' +
                                    '</span>' +
                                '</span>' +
                        '</div>' +
                    '</div>' +
                    '<div class="col-section-main-table-header-col-2">' +
                        '<div class="col-content">' +
                                '<span class="formatted">' +
                                    '<span class="">NET_APY(24H)</span>' +
                                '</span>' +
                        '</div>' +
                    '</div>' +
                '</div>';

var template_2_1 = "<div  name=\"template_add\" class=\"col-section-main-table-item col-section-main-table-row\">\n" +
    "                          <div class=\"col-section-main-table-header-col\">\n" +
    "                              <div class=\"col-content\">\n" +
    "                                  <span class=\"formatted\">\n" +
    "                                      <span>\n" +
    "                                          <span class=\"over-a\" title=\"{{title}}\">{{name}}</span>\n" +
    "                                      </span>\n" +
    "                                  </span>\n" +
    "                              </div>\n" +
    "                          </div>\n" +
    "                          <div class=\"col-section-main-table-header-col-2\">\n" +
    "                              <div class=\"col-content\">\n" +
    "                                  <span class=\"formatted\">\n" +
    "                                      <span class=\"\">{{volume}}</span>\n" +
    "                                  </span>\n" +
    "                              </div>\n" +
    "                              <div class=\"sparkline\">\n" +
    "                                  <div class=\"sparkline-bar\" style=\"width: {{percentage}};\"></div>\n" +
    "                              </div>\n" +
    "                          </div>\n" +
    "                      </div>";

var template_2_2 = "<div  name=\"template_add\" class=\"col-section-main-table-item col-section-main-table-row\">\n" +
    "                          <div class=\"col-section-main-table-header-col\">\n" +
    "                              <div class=\"col-content\">\n" +
    "                                  <span class=\"formatted\">\n" +
    "                                      <span>\n" +
    "                                          <span class=\"\">{{name}}</span>\n" +
    "                                      </span>\n" +
    "                                  </span>\n" +
    "                              </div>\n" +
    "                          </div>\n" +
    "                          <div class=\"col-section-main-table-header-col-2\">\n" +
   "                              <div class=\"col-content\">\n" +
   "                                  <span class=\"formatted\">\n" +
   "                                      <span class=\"\">{{volume}}</span>\n" +
   "                                  </span>\n" +
   "                              </div>\n" +
   "                              <div class=\"sparkline\">\n" +
   "                                  <div class=\"sparkline-bar\" style=\"width: {{percentage}};\"></div>\n" +
   "                              </div>\n" +
   "                          </div>\n" +
    "                      </div>";

var template_2_3_header = '<div class="col-section-main-table-header col-section-main-table-row">' +
                    '<div class="col-section-main-table-header-col">' +
                        '<div class="col-content">' +
                                '<span class="formatted">' +
                                    '<span>' +
                                        '<span class="">Name</span>' +
                                    '</span>' +
                                '</span>' +
                        '</div>' +
                    '</div>' +
                    '<div class="col-section-main-table-header-col-2">' +
                        '<div class="col-content">' +
                                '<span class="formatted">' +
                                    '<span class="">TVL(24H)</span>' +
                                '</span>' +
                        '</div>' +
                    '</div>' +
                '</div>';

var template_2_3 = "<div  name=\"template_add\" class=\"col-section-main-table-item col-section-main-table-row\">\n" +
    "                          <div class=\"col-section-main-table-header-col\">\n" +
    "                              <div class=\"col-content\">\n" +
    "                                  <span class=\"formatted\">\n" +
    "                                      <span>\n" +
    "                                          <span class=\"over-a\" title=\"{{title}}\">{{name}}</span>\n" +
    "                                      </span>\n" +
    "                                  </span>\n" +
    "                              </div>\n" +
    "                          </div>\n" +
    "                          <div class=\"col-section-main-table-header-col-2\">\n" +
   "                              <div class=\"col-content\">\n" +
   "                                  <span class=\"formatted\">\n" +
   "                                      <span class=\"\">{{volume}}</span>\n" +
   "                                  </span>\n" +
   "                              </div>\n" +
   "                              <div class=\"sparkline\">\n" +
   "                                  <div class=\"sparkline-bar\" style=\"width: {{percentage}};\"></div>\n" +
   "                              </div>\n" +
   "                          </div>\n" +
    "                      </div>";

var template_3_1 = "<div class=\"col-section-main-table-item col-section-main-table-row\">\n" +
    "                          <div class=\"col-section-main-table-header-col\">\n" +
    "                              <div class=\"col-content\">\n" +
    "                                  <span class=\"formatted\">\n" +
    "                                      <span>\n" +
    "                                          <span class=\"\"><a class=\"over-a\" href=\"{{link}}\" >{{headline}} </a></span>\n" +
    "                                      </span>\n" +
    "                                  </span>\n" +
    "                              </div>\n" +
    "                          </div>\n" +
    "                          <div class=\"col-section-main-table-header-col-2\">\n" +
    "                              <div class=\"col-content\">\n" +
    "                                  <span class=\"formatted\">\n" +
    "                                      <span class=\"\">{{date}}</span>\n" +
    "                                  </span>\n" +
    "                              </div>\n" +
    "                          </div>\n" +
    "                      </div>";

//查询列表数据
function queryData(url, data, success) {
    $.ajax({
        url: url,
        type: "GET",
        data: data,
        dateType: "json",
        headers: {
            "Access-Control-Allow-Origin": "*"
          },
        success: function(resp){
            success(resp)
        },
        error: function () {
            console.log("send request error...")
        }
    })
}

// 计算给定 UTC 时间字符串距离当前时间过了多少小时或分钟
function getHoursDiffFromUTC(utcTimeString) {
  // 获取当前 UTC 时间和给定 UTC 时间字符串表示的时间之间的差值（单位为毫秒）
  var currentUTCTime = new Date().toUTCString();
  var currentUTCTimestamp = new Date(currentUTCTime).getTime();
  var utcTime = new Date(utcTimeString).toUTCString();
  var utcTimestamp = new Date(utcTime).getTime();
  var timeDiff = currentUTCTimestamp - utcTimestamp;

  // 根据时间差值计算过了多少小时或分钟
  var hoursDiff = timeDiff / (1000 * 60 * 60);
  if (hoursDiff < 1) {
    var minutesDiff = timeDiff / (1000 * 60);
    return Math.round(minutesDiff) + " min ago";
  } else {
    return Math.round(hoursDiff) + " h ago";
  }
}

function addCommas(num, num2) {
  var numStr = num.toFixed(num2).toString(); // 将数字保留六位小数，并转换成字符串
  var pattern = /\B(?=(\d{3})+(?!\d))/g; // 正则表达式，匹配小数点左边的数字
  numStr = numStr.split(".")[0].replace(pattern, ",") + "." + numStr.split(".")[1]; // 将小数点左边的数字添加逗号，再将整数部分和小数部分合并
  return numStr;
}

function addLongCommas(num) {
  var numStr = num.toString();
  var pattern = /\B(?=(\d{3})+(?!\d))/g; // 正则表达式，匹配小数点左边的数字
  numStr = numStr.replace(pattern, ","); // 将小数点左边的数字添加逗号，再将整数部分和小数部分合并
  return numStr;
}


window.onload = function() {
    var countdowns = [
      { id: "Time_1", time: 10 * 60 * 1000 },
      { id: "Time_2", time: 10 * 60 * 1000 },
      { id: "Time_3", time: 1 * 1000 },
      { id: "Time_4", time: 1 * 1000 },
      { id: "Time_5", time: 10 * 60 * 1000 },
      { id: "Time_6", time: 1 * 1000 },
    ];

    function startCountdown(id, time) {
      var countdownDate = new Date().getTime() + time;
      var countdownElement = document.getElementById(id);
      var countdownInterval;

      function stopCountdown() {
        clearInterval(countdownInterval);
      }

      function resetCountdown() {
        stopCountdown();
        startCountdown(id, time);
      }

      function updateCountdown() {
        var now = new Date().getTime();
        var distance = countdownDate - now;

        if (distance <= 0) {
          if(id == "Time_1"){
            table1_1();
          }
          if(id == "Time_2"){
            table1_2();
            table2_2();
          }
          if(id == "Time_3"){
            login();
          }
          resetCountdown();
          return;
        }

        var minutes = Math.floor(distance / (1000 * 60));
        var seconds = Math.floor((distance % (1000 * 60)) / 1000);

        countdownElement.innerHTML = "Next update in " + minutes + "m " + seconds + "s";
      }

      updateCountdown();
      countdownInterval = setInterval(updateCountdown, 1000 - (new Date().getTime() % 1000));
    }

    // 初始化所有倒计时
    countdowns.forEach(function(countdown) {
      startCountdown(countdown.id, countdown.time);
    });
}

function date7(){
    var today = new Date();  // 获取今天日期
    var dates = [];  // 创建一个空数组来存储日期

    // 获取当前时间
    var currentTime = new Date();

    // 循环生成7个时间间隔，每次减去10分钟
    for (var i = 0; i < 7; i++) {
      // 计算当前时间之前的时间
      var previousTime = new Date(currentTime.getTime() - (i * 10 * 60 * 1000));
      // 打印时间
      dates.push(previousTime);
    }

    return dates;

}

