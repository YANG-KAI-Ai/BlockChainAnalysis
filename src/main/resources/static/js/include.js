// 创建header
var headerElement = '' +
    '<ul class="layui-nav layui-layout-left">'+
        '<li id="HomeTab" class="layui-nav-item"><a href="./index.html">Smart Defi</a></li>'+
        '<li id="poolsTab" class="layui-nav-item"><a href="./top-liquidity-pools.html">Defi Paradise</a></li>'+
    '</ul>' +
    '<ul class="layui-nav layui-layout-right">\n' +
    '    <li class="layui-nav-item layui-show-xs-inline-block layui-hide-sm" lay-header-event="menuRight" lay-unselect style="height: 59px;">\n' +
    '        <a href="javascript:;">\n' +
    '            <i class="layui-icon layui-icon-spread-left"></i>\n' +
    '        </a>\n' +
    '    </li>\n' +
    '</ul>';



var nameHeader = $("title").text()+"Tab";
$(".layui-header").html(headerElement);
if(nameHeader=="HomeTab"){
    $("#"+nameHeader).attr("class", "layui-nav-item layui-this");
} else {
    $("#poolsTab").attr("class", "layui-nav-item layui-this");
}

var htmlToInsert = `
    <ul class="layui-nav layui-nav-tree" lay-filter="test">
                        <li id="liquidity" class="layui-nav-item"><a href="./top-liquidity-pools.html">Top Liquidity Pools</a></li>
                        <li id="pool" class="layui-nav-item"><a href="./Hot-Blockchain-pools.html">Hot Pool</a></li>
                        <li id="token" class="layui-nav-item"><a href="./Hot-Blockchain-token.html">Hot Token</a></li>
                        <li id="mint" class="layui-nav-item"><a href="./mint-master.html">Mint Master</a></li>
                    </ul>
`;

 $(".layui-side-scroll").html(htmlToInsert);
var name = $("title").text();
$("#"+name).attr("class", "layui-nav-item layui-this");



