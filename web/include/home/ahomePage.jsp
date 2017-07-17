<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<script>
	$(function(){
		
		$("div[status]").hide();
		$("div[status=choose]").show();
		console.log("运行隐藏和显示表格");
		
		$("a[status]").click(function(){
			var status = $(this).attr("status");
			console.log(status);
			$("div[status]").hide();
			$("div[status="+status+"]").show();
			//由于双引号没加，造成编译错误
			$("div.dataType li").removeClass("active");
			$(this).parent("li").addClass("active");
		});
		
	});
	
	
	
	
</script>



<div class="container">
<div class="jumbotron " status="choose">
  <h1>所有课程选修情况</h1>
  <p>本学期的所有课程，你说不上就不上。</p>
</div>
<div class="jumbotron " status="notice">
  <h1>已发布通知</h1>
  <p>你说不上了就是不上了。</p>
</div>
</div>


<div class="container">
  <div class="row">
    <div class="col-md-2  dataType">
      <ul class="nav nav-pills nav-stacked">
        <li><a href="#" status="notice">已发布通知</a></li>
        <li class="active"><a href="#" status="choose">所有课程选修情况</a></li>
      </ul>
    </div>



    <div class="col-md-9 col-md-offset-1 ">
    
    <!-- 通知栏 -->
      <div class="table-responsive" status="notice">
	     <h2>已发布通知栏：</h2>
	     <c:forEach items="${anotice}" var = "c">
			<p>你已退选课程名为${c.cname }，授课老师为${c.tname }的课程</p>
          </c:forEach>

      </div>
      
      <!-- 所有课程栏 -->
      <div class="table-responsive" status="choose">
      
	      <!-- 搜索框 -->
	      
	      <div class="row">
			  <div class="addNew col-lg-4 ">
	    	<button type="button" class="btn btn-primary btn-md" data-toggle="modal" data-target="#myModalAdd">
	    	 新增课程
</button>
<a href="admin_course_download"><button type="button" class="btn btn-primary btn-md bg-success" >
	    	 导出为所有课程为Excel
</button></a>

	    </div>
			  <div class="col-lg-4 col-lg-offset-4">
			  <form action="foreasearch" method="post">
			    <div class="input-group">
			      <input type="text"  class="form-control" placeholder="请输入要查询的内容 " name="keyword" value="${param.keyword}">
			      <span class="input-group-btn">
			        <button class="btn btn-default" type="submit">搜索</button>
			      </span>
			    </div><!-- /input-group -->
			    </form>
			  </div><!-- /.col-lg-6 -->
			  
			</div><!-- /.row -->
	      	
	      
	      
        <br>
	     
	    
        
        
        <table class="table table-striped table-bordered table-hover table-condensed " id="table">
          <thead>
          <tr>
            <th>课程号</th>
            <th>课程名</th>
            <th>课程描述</th>
            <th>学分</th>
            <th>授课老师号</th>
            <th>授课老师</th>
            <th>已选人数</th>
            <th>退选</th>
          </tr>
       </thead>
       <tbody>
       
       	<c:forEach items="${cs}" var = "c">
       	
          <tr>
          	<td>${c.cno }</td>
          	<td>${c.cname }</td>
          	<td>${c.description }</td>
          	<td>${c.credit }</td>
          	<td>${c.tno }</td>
          	<td>${c.tname}</td>
          	<td>${c.choosen }/${c.number }</td>
          	<td><a deleteCLink="true" href="admin_course_drop?cid=${c.cno }"><span class="glyphicon glyphicon-trash"></</span>删除</a></td>
          </tr>
          </c:forEach>
          </tbody>
        </table>

      </div>
      
      </div>
    </div>
</div>






