<%@ include file="/jsp/basepages/taglib.jsp" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html lang="en">
    <head>
    	
    	<layout:block name="head_begin">
    	</layout:block>
        <meta charset="utf-8"/>
       <!--  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> -->
       <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
      
       
        <meta name="renderer" content="webkit"/>
        <title>后台管理</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <meta name="keywords" content="" />
        <meta name="author" content="" />
        <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
		<link href="/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" />		
		 <link href="/datepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" />
		 <link rel="stylesheet" type="text/css" media="screen" href="/css/base/table.css" >		
		<link rel="stylesheet" type="text/css" media="screen" href="/css/base/base.css"/> 
        <script type='text/javascript' src="/js/base/jquery-2.1.4.min.js"></script>
        <script type='text/javascript' src="/datepicker/js/bootstrap-datetimepicker.min.js"></script>
		<script type="text/javascript" src="/datepicker/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
		<script type="text/javascript" charset="utf-8" src="/UEditor/ueditor.config.js"></script>
    	<script type="text/javascript" charset="utf-8" src="/UEditor/ueditor.all.min.js"> </script>		
		<script type='text/javascript' src="/js/base/myutil.js"></script>
		<!-- <link rel="stylesheet" type="text/css" media="screen" href="/css/admin/classroom_manage.css"/> -->

		<link rel="stylesheet" type="text/css" media="screen" href="/css/base/websocket.css">
		 <!-- <link rel="stylesheet" type="text/css" media="screen" href="/css/dashboard.css">  -->
		
		
		
		
		<layout:block name="head_end">
			
		</layout:block>
    </head>   

    <body >
		<div id="basecontent">	
			<layout:block name="base_content">
			
			</layout:block>
    	</div>
    	<!-- display:block;text-align:center;position:fixed;bottom:0px; -->
		<footer class="container" style=" display:block;width:100%;
	text-align:center;">
		  <p>Copyright © 1986 - 2016 All rights reserved | <a href="http://chuangxin.dlut.edu.cn">大连理工大学创新实验学院版权所有</a> | GPLv3 <a href="http://gplv3.fsf.org/">Terms</a></p>
		</footer>
	

        <script type='text/javascript' src="/bootstrap/js/bootstrap.min.js"></script>

		<!--TinyMCE Editor-->
		 <script type='text/javascript' src="/js/base/tiny_mce.js"></script> 
		<!-- JQuery Masonry-->
		<script type='text/javascript' src="/js/base/jquery.masonry.min.js"></script>
		<script type='text/javascript' src="/js/base/base.js"></script> 	       
        <!-- 百度推广JS -->
        <script type="text/javascript">
            var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
        </script>
         <!-- 百度推广JS END -->
        </body>
    </html>
