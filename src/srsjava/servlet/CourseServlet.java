package srsjava.servlet;
 
import java.awt.image.BufferedImage;    
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import srsjava.bean.*;
import srsjava.dao.*;
import srsjava.util.Page;

 
public class CourseServlet extends BaseForeServlet {
     
    public String choose(HttpServletRequest request, HttpServletResponse response, Page page) {
    	int id = Integer.parseInt(request.getParameter("cid"));
        System.out.println(id);
        User user = (User) request.getSession().getAttribute("user");
        System.out.println(id);
        System.out.println(user.getId());
        if(courseDAO.getTotalChoose(id)){
        	courseDAO.choose(id,user.getId());
        	return "@forehome";
        }else{
        	request.setAttribute("msg", "该课程人数已满，请选择其他课程");
        	return "forehome";
        }
        	
    }    
 
    public String delete(HttpServletRequest request, HttpServletResponse response,Page page) {
        int id = Integer.parseInt(request.getParameter("cid"));
        System.out.println(id);
        User user = (User) request.getSession().getAttribute("user");
        System.out.println(id);
        System.out.println(user.getId());
        courseDAO.delete(id,user.getId());
        return "@forehome";
    }
    
    public String drop(HttpServletRequest request, HttpServletResponse response,Page page) {
    	//退选一门课程后，三方都会收到通知，同学的已选课程会删除这节课（在sc中删除并加入selection）
        int cno = Integer.parseInt(request.getParameter("cid"));
        System.out.println("管理员删除的课程号为:" + cno);
        //将sc表中的所有sno加入到selection中,并删除同学选课记录
        courseDAO.remove(cno);
        //将course表中的课程转移到dcourse，删除course中的Cno课程
        
        
        User user = (User) request.getSession().getAttribute("user");
        System.out.println(user.getId());
        return "@foreahome";
    }
    
 
    public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
        int cno = Integer.parseInt(request.getParameter("cid"));
        Course c = courseDAO.get(cno);
        request.setAttribute("newc", c);
        return "editCourse.jsp"; 
    }
 
    
    public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
    	//参数传入失败导致无法转换interger,之前使用的是哈希表，存储二进制，所以不能使用getParameter
        String name=request.getParameter("name");
        System.out.println(name);
        int cno = Integer.parseInt(request.getParameter("id"));
        System.out.println(cno);
 
        courseDAO.updateDescription(name,cno);
         
        
        return "forethome";
 
    }
    public String student(HttpServletRequest request, HttpServletResponse response, Page page) {
    	
        int  cno = Integer.parseInt(request.getParameter("cid"));
        
        
 
        List<Course> c = courseDAO.getStudent(cno);
        request.setAttribute("student", c);
        
        return "student.jsp";
 
    }

    public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
        String cname=request.getParameter("cname");
        int cno=Integer.parseInt(request.getParameter("cno"));
        int tno=Integer.parseInt(request.getParameter("tno"));
        String tname=request.getParameter("tname");
        int credit=Integer.parseInt(request.getParameter("credit"));
        int number=Integer.parseInt(request.getParameter("number"));
        String description=request.getParameter("description");
        Course c = new Course();
        c.setChoosen(0);
        c.setCname(cname);
        c.setCno(cno);
        c.setCredit(credit);
        c.setDescription(description);
        c.setNumber(number);
        c.setTname(tname);
        c.setTno(tno);
        
 
        courseDAO.add(c);
         
        
        return "foreahome";
 
    }
    public String download(HttpServletRequest request, HttpServletResponse response, Page page) throws ServletException, IOException {
    	 List<Course> cs = courseDAO.alist();
    	 request.setAttribute("dcs", cs);
//    	 request.getRequestDispatcher("export.jsp").forward(request, response);
    	 return "export.jsp";
    }
    
    
}