package com.z.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

class ThreadTest extends Thread {

    public ThreadTest() {
    }

    @Override
    public void run() {
        super.run();
        String html="  <!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "<head>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"application/xhtml+xml;charset=utf-8\" />\n" +
                "    <title>三国之从十常侍之乱崛起最新章节_禾穗谓之颖-顶点笔趣阁手机站</title>\n" +
                "    <meta name=\"generator\" content=\"顶点笔趣阁手机站\" />\n" +
                "    <meta id=\"ctl00_metaKeywords\" name=\"keywords\" content=\"三国之从十常侍之乱崛起\"/>\n" +
                "    <meta id=\"ctl00_metaDescription\" name=\"description\" content=\"三国之从十常侍之乱崛起无弹窗最新章节由网友提供，《三国之从十常侍之乱崛起》情节跌宕起伏、扣人心弦，是一本情节与文笔俱佳的玄幻小说，顶点笔趣阁免费提供三国之从十常侍之乱崛起最新清爽干净的文字章节在线阅读。\" />\n" +
                "    <meta http-equiv=\"Cache-Control\" content=\"no-siteapp\" />\n" +
                "    <meta http-equiv=\"Cache-Control\" content=\"no-transform\" />\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no\" />\n" +
                "    <meta name=\"format-detection\" content=\"telephone=no\" />\n" +
                "    <meta name=\"apple-mobile-web-app-capable\" content=\"yes\" />\n" +
                "    <meta name=\"apple-mobile-web-app-status-bar-style\" content=\"black-translucent\" />\n" +
                "    <meta name=\"author\" content=\"禾穗谓之颖\" />\n" +
                "    <meta property=\"og:type\" content=\"novel\" />\n" +
                "    <meta property=\"og:title\" content=\"三国之从十常侍之乱崛起\"/>\n" +
                "    <meta property=\"og:url\" content=\"https://qq.hehuamei.com/niyusnhdyua/277243/\" />\n" +
                "    <meta property=\"og:description\" content=\"苏辰意外穿越三国，获得签到系统。随机抽取破阵霸王枪、乌骓马、龙泉剑、越王八剑、留侯张良无双智谋传承……且看苏辰如何威震三国，成就霸业！\" />\n" +
                "    <meta property=\"og:image\" content=\"https://app.hehuamei.com/files/article/image/277/277243/277243s.jpg\"/>\n" +
                "    <meta property=\"og:novel:category\" content=\"穿越小说\"/>\n" +
                "    <meta property=\"og:novel:status\" content=\"连载中\"/>\n" +
                "    <meta property=\"og:novel:author\" content=\"禾穗谓之颖\"/>\n" +
                "    <meta property=\"og:novel:book_name\" content=\"三国之从十常侍之乱崛起\"/>\n" +
                "    <meta property=\"og:novel:read_url\" content=\"https://qq.hehuamei.com/niyusnhdyua/277243/\"/>\n" +
                "    <meta property=\"og:novel:update_time\" content=\"2021-09-22 22:40:53\" />\n" +
                "    <meta property=\"og:novel:latest_chapter_name\" content=\"第77章老虎\" />\n" +
                "    <meta property=\"og:novel:latest_chapter_url\" content=\"https://qq.hehuamei.com/niyusnhdyua/277243/83511272.html\" />\n" +
                "    <link rel=\"stylesheet\" href=\"/css/reset.css\" />\n" +
                "    <link rel=\"stylesheet\" href=\"/css/bookinfo.css\" />\n" +
                " <script type=\"application/ld+json\">\n" +
                "        {\n" +
                "            \"@context\": \"https://ziyuan.baidu.com/contexts/cambrian.jsonld\",\n" +
                "            \"@id\": \"https://qq.hehuamei.com/niyusnhdyua/277243/\",\n" +
                "            \"appid\": \"1601328506958234\",\n" +
                "            \"title\": \"三国之从十常侍之乱崛起最新章节_禾穗谓之颖-顶点笔趣阁手机站\",\n" +
                "            \"images\": [\n" +
                "                \"https://app.hehuamei.com/files/article/image/277/277243/277243s.jpg\"\n" +
                "            ], //请在此处添加希望在搜索结果中展示图片的url，可以添加0个、1个或3个url\n" +
                "            \"pubDate\": \"2021-09-22CST22:40:53\" // 需按照yyyy-mm-ddThh:mm:ss格式编写时间，字母T不能省去\n" +
                "        }\n" +
                "    </script>\n" +
                "    <script id='mob' type='text/javascript' charset='utf-8' src='/gg/app.js'></script>\n" +
                "</head>   \n" +
                "<body>\n" +
                "   <header class=\"channelHeader channelHeader2\">\n" +
                "       \n" +
                "</header>\n" +
                "    <div class=\"bg\">\n" +
                "        \n" +
                "    </div>\n" +
                "    \n" +
                "    <script id='mob' type='text/javascript' charset='utf-8' src='/gg/vx.js'></script> \n" +
                "    <p class=\"synopsisAd\" style=\"color: red;\"></p>\n" +
                "    <div class=\"synopsisArea\" style=\"height: 220px;\">\n" +
                "        \n" +
                "        <p class=\"btn\" style=\"height: 70px;\">\n" +
                "\t\t\t<a href=\"/all/277243/all.html\">全部章节</a><a href=\"javascript:addBookMarkByManual(0,277243);\" class=\"btn_toBookShelf\">加入书架</a><a href=\"/niyusnhdyua/277243/83511272.html\" class=\"btn_toMyBook\">开始阅读</a>\n" +
                "\t</p>\n" +
                "        <div class=\"synopsisArea_detail\">\n" +
                "            <img src=\"https://app.hehuamei.com/files/article/image/277/277243/277243s.jpg\" onerror=\"this.src='/images/defaultimg.png'\" />\n" +
                "                <p class=\"author\">作者：禾穗谓之颖</p>\n" +
                "            <p class=\"sort\">\n" +
                "                类别：穿越小说</p>\n" +
                "            <p class=\"\">\n" +
                "                状态：连载中</p>\n" +
                "            <p class=\"\">\n" +
                "                点击：<a href=\"/niyusnhdyua/277243/83511272.html\"><font color=\"red\">第77章老虎</font></a></p>\n" +
                "            <p class=\"\">\n" +
                "                更新：2021-09-22 22:40:53</p>\n" +
                "        </div>\n" +
                "        \n" +
                "        \n" +
                "    </div>\n" +
                "    <header class=\"channelHeader channelHeader2\">\n" +
                "<a href=\"javascript:history.go(-1);\" class=\"iconback\">返回</a>\n" +
                "<h1><span class=\"title\">小说名：三国之从十常侍之乱崛起</span></h1>\n" +
                "<a href=\"/\" class=\"iconhome\">首页</a>\n" +
                "</header>\n" +
                "<p class=\"review\">\n" +
                "        \t作品简介：苏辰意外穿越三国，获得签到系统。随机抽取破阵霸王枪、乌骓马、龙泉剑、越王八剑、留侯张良无双智谋传承……且看苏辰如何威震三国，成就霸业！\n" +
                "        </p>\n" +
                "        <script>read_app2(\"三国之从十常侍之乱崛起\");</script>\n" +
                "    <div  class=\"recommend\">\n" +
                "        <div class=\"top_l\"><p>三国之从十常侍之乱崛起最新章节</p></div>\n" +
                "\t<div class=\"directoryArea\">\n" +
                "\t\t            <p><a href=\"/niyusnhdyua/277243/83511272.html\">第77章老虎</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83511271.html\">第76章命令</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83511270.html\">第75章控制</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83511269.html\">第74章天眼</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83458482.html\">第73章疏忽</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83458481.html\">第72章英雄</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83383117.html\">第71章命案</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83383116.html\">第70章汉朝的官权</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345554.html\">第69章洛阳帝令</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345553.html\">第68章边防急报</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345552.html\">第67章西园军</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345551.html\">第66章张氏族长</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345550.html\">第65章抱怨</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345549.html\">第64章黄忠</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345548.html\">第63章三人畅谈</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345547.html\">第62章上当受骗</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345546.html\">第61章年长的女人</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345545.html\">第60章派出长子</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345544.html\">第59章中山景王</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345543.html\">弟58章无家可归</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345542.html\">第57章军纪严明</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345541.html\">第56章共筑辉煌的未来</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345540.html\">第55章半个月后</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345539.html\">第54章最后的胜利</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345538.html\">第53章精神失常</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345537.html\">第52章陛下说清楚</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345536.html\">第51章良好和恭敬</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345535.html\">第50章三个提议</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345534.html\">第49章张羌之战</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345533.html\">第48章父亲</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345532.html\">第47章送回洛阳</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345531.html\">第46章张角</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345530.html\">第45章斩首</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345529.html\">第44章攻打黄巾</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345528.html\">第43章张龙</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345527.html\">第42章首领</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345526.html\">第41章叛军</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345525.html\">第40章财产</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345524.html\">第39章婚事</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345523.html\">第38章简单的军训</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345522.html\">第37章还需观察</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345521.html\">第36章移居巴蜀</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345520.html\">第35章装神弄鬼</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345519.html\">第34章文武百官</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345518.html\">第33章大家族</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345517.html\">第32章娶妾</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345516.html\">第31章三大家族</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345515.html\">第30章英雄落幕</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345514.html\">第29章来了很多难民</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345513.html\">第28章傻傻的上钩</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345512.html\">第27章何家</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345511.html\">第26章绝妙恩情</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345510.html\">第25章前祖父</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345509.html\">第24章祸不单行</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345508.html\">第23章风流才子</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345507.html\">第22章获胜的机会</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345506.html\">第21章黄忠顾虑</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345505.html\">第20章江夏野蛮人</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345504.html\">第19章进入豫州</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345503.html\">第18章学生企业家</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345502.html\">第17章驱逐出境</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345501.html\">第16章贵族家庭</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345500.html\">第15章九鼎龙象之力</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345499.html\">第14章燕良弱如鸡</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345498.html\">第13章没来得及说什么</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345497.html\">第12章立马前来臣服</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345496.html\">第十一章不愿离开灵山岛</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345495.html\">第十章肥沃的土地之主</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345494.html\">第九章曹某人的目标</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345493.html\">第八章陷阵之志，有死无生</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345492.html\">第七章月夜杀戮，大破吕布！</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345491.html\">第六章吸收西凉军余部</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345490.html\">第五章洛阳城外的冲突</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345489.html\">第四章动荡很快将降临</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345488.html\">第三章大哥，你的志向是什么？</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345487.html\">第二章暗流涌动，风起洛阳</a></p>\n" +
                "                    <p><a href=\"/niyusnhdyua/277243/83345486.html\">第一章签到五年，开局诛杀十常侍</a></p>\n" +
                "        \t</div>\n" +
                "    </div>\n" +
                "    <script language=\"javascript\" type=\"text/javascript\" src=\"/js/zepto.min.js\"></script>\n" +
                "    <script language=\"javascript\" type=\"text/javascript\" src=\"/js/common.js\"></script>\n" +
                "    <script language=\"javascript\" type=\"text/javascript\" src=\"/js/index.js\"></script>\n" +
                "    <script language=\"javascript\" type=\"text/javascript\" src=\"/js/lazyload.js\"></script>\n" +
                "  <script language=\"javascript\" type=\"text/javascript\">\n" +
                "\n" +
                "$('.nav a').click(function(){\n" +
                "\t$(this).addClass('sel');\n" +
                "\t$(this).siblings().removeClass('sel');\n" +
                "\t\n" +
                "\t$obj = $('.'+ $(this).attr('id'));\n" +
                "\t\n" +
                "\t$obj.siblings().hide();\n" +
                "\t$obj.show();\n" +
                "\t$('.bomtype').text($obj.data('type'));\n" +
                "\t$('.bomtypeclass').text($(this).attr('id'));\n" +
                "});\t\n" +
                "\n" +
                "$(function(){\n" +
                "\t$('.btn_to2014').click(function(){\n" +
                "\t\t$('.bom_synopsis').show();\n" +
                "\t\t$('.bg').show();\n" +
                "\t});\n" +
                "\t\n" +
                "\t$('.bomhide').click(function(){\n" +
                "\t\t$('.bombox').hide();\n" +
                "\t\t$('.bg').hide();\t\n" +
                "\t});\n" +
                "})\n" +
                "    </script>\n" +
                "    \n" +
                "    <script>\n" +
                "(function(){\n" +
                "    var bp = document.createElement('script');\n" +
                "    var curProtocol = window.location.protocol.split(':')[0];\n" +
                "    if (curProtocol === 'https') {\n" +
                "        bp.src = 'https://zz.bdstatic.com/linksubmit/push.js';\n" +
                "    }\n" +
                "    else {\n" +
                "        bp.src = 'http://push.zhanzhang.baidu.com/push.js';\n" +
                "    }\n" +
                "    var s = document.getElementsByTagName(\"script\")[0];\n" +
                "    s.parentNode.insertBefore(bp, s);\n" +
                "})();\n" +
                "</script>\n" +
                "<p class=\"note\">\n" +
                "</p>\n" +
                "\n" +
                "<script>\n" +
                "var _hmt = _hmt || [];\n" +
                "(function() {\n" +
                "  var hm = document.createElement(\"script\");\n" +
                "  hm.src = \"https://hm.baidu.com/hm.js?e970c106d2b6f8585b429730d4536f61\";\n" +
                "  var s = document.getElementsByTagName(\"script\")[0]; \n" +
                "  s.parentNode.insertBefore(hm, s);\n" +
                "})();\n" +
                "</script>\n" +
                "<script>\n" +
                "var _hmt = _hmt || [];\n" +
                "(function() {\n" +
                "  var hm = document.createElement(\"script\");\n" +
                "  hm.src = \"https://hm.baidu.com/hm.js?362fbf56bf2371ce1d8b5d1e93827de3\";\n" +
                "  var s = document.getElementsByTagName(\"script\")[0]; \n" +
                "  s.parentNode.insertBefore(hm, s);\n" +
                "})();\n" +
                "</script>\n" +
                "  </body>\n" +
                "</html>";
        Document document= Jsoup.parse(html);
        Elements select = document.select(":containsOwn(全部章节)");
        System.out.println("select = " + select);
    }
}
