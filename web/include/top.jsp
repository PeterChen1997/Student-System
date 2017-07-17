<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<script>
$(function(){
	
	
	
	

    $("a").click(function(){
    	if($(this).attr("deleteLink")){
    		var deleteLink = $(this).attr("deleteLink");
            console.log(deleteLink);
            if("true"==deleteLink){
                var confirmDelete = confirm("确认要删除已选的这门课吗？可能由于人数已满，无法再次选上！");
                if(confirmDelete)
                    return true;
                return false;
                 
            }
    	}if($(this).attr("deleteCLink")){
    		var deleteCLink = $(this).attr("deleteCLink");
            console.log(deleteCLink);
            if("true"==deleteCLink){
                var confirmDelete = confirm("确认要将这门课打入冷宫吗？");
                if(confirmDelete)
                    return true;
                return false;
                 
            }
    	}
    	if($(this).attr("chooseLink")){
    		var chooseLink = $(this).attr("chooseLink");
            console.log(chooseLink);
            
            if("true"==chooseLink){
            	var confirmChoose = confirm("确认要选修这门课吗？");
            	if(confirmChoose)
            		return true;
            	return false;
            }
    	}
    	if($(this).attr("editLink")){
    		var chooseLink = $(this).attr("chooseLink");
            console.log(chooseLink);
            
            if("true"==chooseLink){
            	var confirmChoose = confirm("确认要修改这门课的描述吗？");
            	if(confirmChoose)
            		return true;
            	return false;
            }
    	}
    	
    });
    
    <c:if test="${!empty msg}">
    alert("${msg}");
    console.log("msg弹出");
    </c:if>
    
})
</script>


<div class="navbar navbar-default">
  <div class="container">
    <!--导航条头部-->
    <div class="navbar-header">
      <a href="http://www.jiangnan.edu.cn/" target="_blank" class="navbar-brand">
        <img src="img/xiaohui.png" height="44" width="152" style="margin-top: -13px;"/></a>
      <!--logo-->
      <!--折叠菜单的触发按钮-->
      <button data-toggle="collapse" data-target="#my-collapse" type="button" class="navbar-toggle collapsed">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
    </div>

    <!--导航条折叠部分-->
    <div id="my-collapse" class="collapse navbar-collapse">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#" ><span class="glyphicon glyphicon-user"></span> 欢迎你,${user.name}</a></li>
        <li><a href="#" data-toggle="modal" data-target="#myModal"><span class="glyphicon glyphicon-pencil"></span> 修改密码</a></li>
        <li><a href="forelogout"><span class="glyphicon glyphicon-log-out"></span> 退出</a></li>
      </ul>
    </div>
    
 

    
  </div>
  
<!-- 模态框（Modal）myModal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					登录密码修改
				</h4>
			</div>
			<div class="modal-body">
				<form action="forechange" class="form col-md-12 center-block" method="post">
                  <div class="form-group">
                  	<h4><label for="fpassword">原密码</label></h4>
                    <input type="password" id="fpassword" name="fpassword"  class="form-control input-md" placeholder="请输入原密码" title="默认学号">
                  </div>
                  <div class="form-group">
                  	<h4><label for="password1">请输入要修改的密码</label></h4>
                    <input  name="password1" id="password1" type="password" class="form-control input-md" placeholder="请输入你要修改的密码">
                  </div>
                  <div class="form-group">
                  	<h4><label for="password2">请再次输入</label></h4>
                    <input  name="password2" id="password2" type="password" class="form-control " placeholder="请再次输入密码">
                  </div>
                  <div class="form-group">
                    <button class="btn btn-primary btn-lg btn-block">修改</button>
                  </div>
                </form>
            </div>
            
            <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div>
            
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
	<!-- 模态框（Modal）myModalAdd -->
<div class="modal fade" id="myModalAdd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					新增课程
				</h4>
			</div>
			<div class="modal-body">
				<form action="admin_course_add" class="form col-md-12 center-block" method="post">
					<div class="form-group">
	                  	<h5>课程号</h5>
	                    <input type="text" name="cno"  class="form-control input-lg" placeholder="" title="默认学号">
	                  </div>
                  <div class="form-group">
                  	<h5>课程名</h5>
                    <input type="text" name="cname"  class="form-control input-lg" placeholder="" title="默认学号">
                  </div>
                  
                  <div class="form-group">
                  	<h5>课程描述</h5>
                    <input type="textarea" name="description"  class="form-control input-lg" placeholder="" title="默认学号">
                  </div>
                  
                  <div class="form-group">
                  	<h5>学分</h5>
                    <input type="text" name="credit"  class="form-control input-lg" placeholder="" title="默认学号">
                  </div>
                  
                  <div class="form-group">
                  	<h5>教授讲师号</h5>
                    <input type="text" name="tno"  class="form-control input-lg" placeholder="" title="默认学号">
                  </div>
                  <div class="form-group">
                  	<h5>教授讲师</h5>
                    <input type="text" name="tname"  class="form-control input-lg" placeholder="" title="默认学号">
                  </div>
                  <div class="form-group">
                  	<h5>课程人数</h5>
                    <input type="text" name="number"  class="form-control input-lg" placeholder="" title="默认学号">
                  </div>
                  <div class="form-group">
                    <button class="btn btn-primary btn-lg btn-block">修改</button>
                  </div>
                </form>
            </div>
            
            <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div>
            
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
	
</div>



