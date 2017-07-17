package srsjava.dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 
import srsjava.bean.Course;
import srsjava.util.DBUtil;
  
public class CourseDAO {
  
    public int getTotalClass(int cno) {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
  
            String sql = "select count(*) from sc where cno =" + cno;
  
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return total;
    }
    
    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
  
            String sql = "select count(*) from course ";
  
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return total;
    }
  
    
    public boolean getTotalChoose(int cno) {
        int totalC = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
  
            String sql = "select count(*) from sc where cno = " + cno;
  
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                totalC = rs.getInt(1);
            }
            if(totalC < new CourseDAO().getSelect(cno)){
            	return true;
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return false;
    }
    
    
    
    private int getSelect(int cno) {
		// TODO Auto-generated method stub
    	int totalC = 0;
    	try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
    		  
            String sql = "select number from course where cno = " + cno;
  
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                totalC = rs.getInt(1);
            }
            
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return totalC;
    
	}

	public void choose(int cno,String sno) {
    	
        String sql = "insert into sc values(?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
        	
            ps.setInt(2, cno);
            ps.setString(1,sno);
            
            
            ps.execute();
            
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
    }
    
    
    
    public void add(Course bean) {
  
        String sql = "insert into course values(?,?,?,?,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ps.setInt(1, bean.getCno());
            ps.setString(2,bean.getCname());
            ps.setString(3,bean.getDescription());
            ps.setInt(4,bean.getCredit());
            ps.setInt(5, bean.getTno());
            ps.setString(6,bean.getTname());
            ps.setInt(7, bean.getNumber());
            
            ps.execute();
  
//            ResultSet rs = ps.getGeneratedKeys();
//            if (rs.next()) {
//                int id = rs.getInt(1);
//                bean.setId(id);
//            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
    }
  
    public void update(Course bean) {
  
        String sql = "update category set cname= ?,description=?,credit=?,tno=? where cno = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ps.setString(1, bean.getCname());
            ps.setString(2, bean.getDescription());
            ps.setInt(3, bean.getCredit());
            ps.setInt(4, bean.getTno());
            ps.setInt(5, bean.getCno());
  
            ps.execute();
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
  
    }
  
    public void delete(int cno,String id) {
    	//使用预编译不通过
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
  
        	String sql = "delete from sc where cno = " + cno + " and sno = "+ id +"";
            
           
            
            s.execute(sql);
            System.out.println("删除功能执行成功");
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
    }
  
    public Course get(int cno) {
        Course bean = null;
  
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
  
            String sql = "select * from course where cno = " + cno;
  
            ResultSet rs = s.executeQuery(sql);
  
            if (rs.next()) {
                bean = new Course();
                int cno2 = rs.getInt(1);
                String cname = rs.getString(2);
                String description = rs.getString(3);
                int credit = rs.getInt(4);
                int tno = rs.getInt(5);
                String tname = rs.getString(6);
                int number = rs.getInt(7);
                
                bean.setCno(cno2);
                bean.setCname(cname);
                bean.setDescription(description);
                bean.setCredit(credit);
                bean.setTno(tno);
                bean.setTname(tname);
                bean.setNumber(number);
            }
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return bean;
    }
  
    public List<Course> list() {
        return list(0, Short.MAX_VALUE);
    }
  
    public List<Course> list(int start, int count) {
        List<Course> beans = new ArrayList<Course>();
  
        String sql = "select * from course where cno not in ( select cno from selection ) order by cno asc limit ?,? ";
  
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ps.setInt(1, start);
            ps.setInt(2, count);
  
            ResultSet rs = ps.executeQuery();
  
            while (rs.next()) {
                Course bean = new Course();
                int cno = rs.getInt(1);
                String cname = rs.getString(2);
                String description = rs.getString(3);
                int credit = rs.getInt(4);
                int tno = rs.getInt(5);
                String tname = rs.getString(6);
                int number = rs.getInt(7);
                int choosen = new CourseDAO().getTotalClass(cno);
                
//                if(choosen == number)
//                	continue;
                
                bean.setCno(cno);
                bean.setCname(cname);
                bean.setDescription(description);
                bean.setCredit(credit);
                bean.setTno(tno);
                bean.setTname(tname);
                bean.setNumber(number);
                bean.setChoosen(choosen);
                beans.add(bean);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return beans;
    }

	public List<Course> listChoosen(String userId) {
		// TODO Auto-generated method stub
		List<Course> beans = new ArrayList<Course>();
		  
        String sql = "select * from sc where sno = " + userId;
  
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
           
            ResultSet rs = ps.executeQuery();
  
            
            while (rs.next()) {
            	
                int cno = rs.getInt(2);
                Course bean = new CourseDAO().get(cno);
                System.out.println("学生选修了课程号为" + cno + "的课程");
                
                beans.add(bean);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return beans;
	}

	public List<Course> listNotice(String userId) {
		// TODO Auto-generated method stub
		List<Course> beans = new ArrayList<Course>();
		  
        String sql = "select  * from selection,course where selection.cno=course.cno and sno = ?";
  
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
           ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();
  
            while (rs.next()) {
                Course bean = new Course();
                String sno = rs.getString(1);	
                int cno = rs.getInt(2);
                String cname = rs.getString(4);
                String description = rs.getString(5);
                int credit = rs.getInt(6);
                String tname = rs.getString(8);
                	
                
                
                bean.setCno(cno);
                bean.setCname(cname);
                bean.setDescription(description);
                bean.setCredit(credit);
                bean.setTname(tname);
                beans.add(bean);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return beans;
	}
	
	
	
	public List<Course> search(String keyword, int start, int count) {
         List<Course> beans = new ArrayList<Course>();
         
         if(null==keyword||0==keyword.trim().length())
             return new CourseDAO().list();
            String sql = "select * from course where cname like ? or credit = ? or description like ? or tname like ?";
      
            try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
                ps.setString(1, "%"+keyword.trim()+"%");
                ps.setString(4, "%"+keyword.trim()+"%");
                
                int i = 0;
                for (i = 0; i < keyword.length(); i++) {
                    if (!Character.isDigit(keyword.charAt(i))) {
                        break;
                    }
                }
                if(i == keyword.length())
                	ps.setInt(2, Integer.parseInt(keyword.trim()));
                else 
                	ps.setInt(2, 0);
                
                
                ps.setString(3, "%"+keyword.trim()+"%");
                ResultSet rs = ps.executeQuery();
      
                while (rs.next()) {
                    Course bean = new Course();
                    int cno = rs.getInt("cno");
                    String cname = rs.getString("cname");
                    String description = rs.getString("description");
                    int credit = rs.getInt("credit");
                    int tno = rs.getInt("tno");
                    String tname = rs.getString("tname");
                    int number = rs.getInt(7);
                    int choosen = new CourseDAO().getTotalClass(cno);
 
                    bean.setCname(cname);
                    bean.setCno(cno);
                    bean.setTno(tno);
                    bean.setDescription(description);
                    bean.setCredit(credit);
                    bean.setTname(tname);
                    bean.setNumber(number);
                    bean.setChoosen(choosen);
                         
                    beans.add(bean);
                }
            } catch (SQLException e) {
      
                e.printStackTrace();
            }
            return beans;
    }

	public List<Course> teacherList(String tno) {
		// TODO Auto-generated method stub
		List<Course> beans = new ArrayList<Course>();
		  
        String sql = "select * from course where tno = " + tno;
  
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
        	ResultSet rs = ps.executeQuery();
  
            while (rs.next()) {
                Course bean = new Course();
                int cno = rs.getInt(1);
                String cname = rs.getString(2);
                String description = rs.getString(3);
                int credit = rs.getInt(4);
                int number = rs.getInt(7);
                int choosen = new CourseDAO().getTotalClass(cno);
                
                bean.setCno(cno);
                bean.setCname(cname);
                bean.setDescription(description);
                bean.setCredit(credit);
                bean.setNumber(number);
                bean.setChoosen(choosen);
                beans.add(bean);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return beans;
		
	}

	public List<Course> listTNotice(String tno) {
		// TODO Auto-generated method stub
		List<Course> beans = new ArrayList<Course>();
		  
        String sql = "select  * from selection,course where selection.cno=course.cno and tno = ? group by description";
  
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ps.setString(1, tno);
            ResultSet rs = ps.executeQuery();
  
            while (rs.next()) {
                Course bean = new Course();
                String tname = rs.getString(8);
                String cname = rs.getString(4);
                System.out.println(cname);
                
                bean.setTname(tname);
                bean.setCname(cname);
                beans.add(bean);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return beans;
	}

	public void updateDescription(String description,int cno) {
		// TODO Auto-generated method stub
		String sql = "update course set description=? where cno=?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ps.setString(1, description);
            ps.setInt(2, cno);
            System.out.println("执行更新描述操作");
            ps.execute();
            
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
		
		
		
		
	}

	public void remove(int cno) {
		// TODO Auto-generated method stub
		
        try (Connection c = DBUtil.getConnection(); Statement ps = c.createStatement();) {
        	String sql = "insert into selection (sno,cno) select sno,cno from sc where cno =  " + cno; 
        	String sql2 = "delete from sc where cno =  "+ cno;
            System.out.println(sql);
            System.out.println(sql2);
            System.out.println("执行Remove描述操作");
            ps.execute(sql);
            ps.execute(sql2);
            
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
		
	}

	public List<Course> listANotice() {
		// TODO Auto-generated method stub
		List<Course> beans = new ArrayList<Course>();
		  
        String sql = "select * from selection,course where selection.cno=course.cno group by description";
  
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ResultSet rs = ps.executeQuery();
  
            while (rs.next()) {
                Course bean = new Course();
                String sno = rs.getString(1);	
                int cno = rs.getInt(2);
                String cname = rs.getString(4);
                String description = rs.getString(5);
                int credit = rs.getInt(6);
                String tname = rs.getString(8);
                	
                
                
                bean.setCno(cno);
                bean.setCname(cname);
                bean.setDescription(description);
                bean.setCredit(credit);
                bean.setTname(tname);
                beans.add(bean);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return beans;
	}

	public List<Course> getStudent(int cno) {
		// TODO Auto-generated method stub
		List<Course> beans = new ArrayList<Course>();
		  
        String sql = "select * from sc,user where sc.sno=user.id and cno =" + cno;
  
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
           
            ResultSet rs = ps.executeQuery();
  
            
            while (rs.next()) {
            	Course bean = new Course();
                int sno = rs.getInt(1);
                String name = rs.getString(5);
                
                bean.setCno(sno);
                bean.setCname(name);
                beans.add(bean);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return beans;
	}

	public List<Course> alist() {
		// TODO Auto-generated method stub
	        List<Course> beans = new ArrayList<Course>();
	  
	        String sql = "select * from course order by cno asc ";
	  
	        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
	  
	           
	  
	            ResultSet rs = ps.executeQuery();
	  
	            while (rs.next()) {
	                Course bean = new Course();
	                int cno = rs.getInt(1);
	                String cname = rs.getString(2);
	                String description = rs.getString(3);
	                int credit = rs.getInt(4);
	                int tno = rs.getInt(5);
	                String tname = rs.getString(6);
	                int number = rs.getInt(7);
	                int choosen = new CourseDAO().getTotalClass(cno);
	                
//	                if(choosen == number)
//	                	continue;
	                
	                bean.setCno(cno);
	                bean.setCname(cname);
	                bean.setDescription(description);
	                bean.setCredit(credit);
	                bean.setTno(tno);
	                bean.setTname(tname);
	                bean.setNumber(number);
	                bean.setChoosen(choosen);
	                beans.add(bean);
	            }
	        } catch (SQLException e) {
	  
	            e.printStackTrace();
	        }
	        return beans;
	    }
	
	
  
}