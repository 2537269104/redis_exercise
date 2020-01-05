<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="setList" >
 <input type="hidden" name="pageNum">
</form>
<table>
   <tr>
    <td>id</td>
    <td>名称</td>
    <td>价格</td>
    <td>出售百分比</td>
   </tr>
   <c:forEach items="${pageInfo.list}" var="g">
    <tr>
     <td>${g.id }</td>
     <td>${g.name }</td>
     <td>${g.price }</td>
     <td>${g.sell }%</td>
     <td></td>
    </tr>
   </c:forEach>
     <tr>
          <td colspan="10">
           <button onclick="fenye(1)">首页</button>
           <button onclick="fenye(${pageInfo.prePage})">上一页</button>
           <button onclick="fenye(${pageInfo.nextPage})">下一页</button>
           <button onclick="fenye(${pageInfo.pages})">尾页</button> &nbsp;&nbsp;&nbsp;&nbsp;
			当前${pageInfo.pageNum}/${pageInfo.pages}页，共${pageInfo.total}条
     </td>       
     </tr>
    
    </table> 
<script type="text/javascript" src="/js/jquery-1.8.3.js"></script>  
<script type="text/javascript">
       function fenye(currentPage) {
		   $("[name=pageNum]").val(currentPage);
		   $("form").submit();
	}
</script>  
</body>
</html>