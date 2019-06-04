<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>SUI Mobile Demo</title>
    <meta name="description" content="MSUI: Build mobile apps with simple HTML, CSS, and JS components.">
    <meta name="author" content="阿里巴巴国际UED前端">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">

    <!-- Google Web Fonts -->

    <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/sm.min.css">
    <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/sm-extend.min.css">

    <link rel="apple-touch-icon-precomposed" href="/assets/img/apple-touch-icon-114x114.png">

    <script>
        //ga
    </script>
    <script>
        var _hmt = _hmt || [];
        (function () {
            var hm = document.createElement("script");
            hm.src = "//hm.baidu.com/hm.js?ba76f8230db5f616edc89ce066670710";
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(hm, s);
        })();
    </script>

</head>
<body>
<div class="page-group">
    <div id="page-label-input" class="page">
        <header class="bar bar-nav">
            <a class="button button-link button-nav pull-left back" href="/demos/form">
                <span class="icon icon-left"></span>
                back
            </a>
            <h1 class="title">表单</h1>
        </header>
        <div class="content">
            <div class="list-block">
                <ul>
                    <!-- Text inputs -->
                    <li>
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title label">Shop Name</div>
                                <div class="item-input">
                                    <input type="text" id="shop-name" placeholder="Shop Name">
                                </div>
                            </div>
                        </div>
                    </li>
                    <!--商铺分类，下拉列表-->
                    <li>
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title label">Shop Category</div>
                                <div class="item-input">
                                    <select id="shop-category">
                                        <!--从后台读出来-->
                                    </select>
                                </div>
                            </div>
                        </div>
                    </li>
                    <!--区域分类，下拉列表-->
                    <li>
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title label">Area</div>
                                <div class="item-input">
                                    <select id="area">
                                        <!--所属区域-->
                                    </select>
                                </div>
                            </div>
                        </div>
                    </li>
                    <!--详细地址，text-->
                    <li>
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title label">Shop Address</div>
                                <div class="item-input">
                                    <input type="text" id="shop-addr" placeholder="Shop Addr">
                                </div>
                            </div>
                        </div>
                    </li>
                    <!--联系电话 text-->
                    <li>
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title label">Phone</div>
                                <div class="item-input">
                                    <input type="text" id="shop-phone" placeholder="Shop Phone">
                                </div>
                            </div>
                        </div>
                    </li>
                    <!--缩略图 上传控件-->
                    <li>
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title label">Shop Name</div>
                                <div class="item-input">
                                    <input type="text" id="shop-name" placeholder="Shop Name">
                                </div>
                            </div>
                        </div>
                    </li>
                    <!--店铺简介text area-->
                    <!--验证码 -->
                    <li>
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title label">E-mail</div>
                                <div class="item-input">
                                    <input type="email" placeholder="E-mail">
                                </div>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title label">Password</div>
                                <div class="item-input">
                                    <input type="password" placeholder="Password" class="">
                                </div>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title label">Gender</div>
                                <div class="item-input">
                                    <select>
                                        <option>Male</option>
                                        <option>Female</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </li>
                    <!-- Date -->
                    <li>
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title label">Birth date</div>
                                <div class="item-input">
                                    <input type="date" placeholder="Birth day" value="2014-04-30">
                                </div>
                            </div>
                        </div>
                    </li>
                    <!-- Switch (Checkbox) -->
                    <li>
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title label">Switch</div>
                                <div class="item-input">
                                    <label class="label-switch">
                                        <input type="checkbox">
                                        <div class="checkbox"></div>
                                    </label>
                                </div>
                            </div>
                        </div>
                    </li>
                    <li class="align-top">
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title label">Textarea</div>
                                <div class="item-input">
                                    <textarea></textarea>
                                </div>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="content-block">
                <div class="row">
                    <div class="col-50"><a href="#" class="button button-big button-fill button-danger">取消</a></div>
                    <div class="col-50"><a href="#" class="button button-big button-fill button-success">提交</a></div>
                </div>
            </div>
        </div>
    </div>

</div>
<script type='text/javascript' src='//g.alicdn.com/sj/lib/zepto/zepto.min.js' charset='utf-8'></script>
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm.min.js' charset='utf-8'></script>
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm-extend.min.js' charset='utf-8'></script>
</body>
</html>
