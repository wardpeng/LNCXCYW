<%@ include file="/jsp/basepages/taglib.jsp" %>
<layout:override name="news_content">

<div>  
	<p style="text-align:center; font-weight:bold;font-size:16px; font-family:'微软雅黑';">标题：<s:property value="%{addressList.get(0).newsTile}"/></p>	
	<p style="text-align:center; color:grey; font-size:12px; margin-bottom:10px;">作者：<s:property value="%{addressList.get(0).author}"/></p>	
</div>


<div class="mycontent" id="paginationTableDiv">  
	<div style="margin-top:-15px; margin-left:-10px">
		<a href="<s:property value="#i.news_address" />">			
			<img alt="<s:property value="%{addressList.get(0).newsTile}"/>" src="<s:property value="%{addressList.get(0).news_address}"/>" style="width:600px; height:620px;  margin-top:17px; ">
		</a>
	</div>
</div>
</layout:override>


<%@ include file="base.jsp" %>