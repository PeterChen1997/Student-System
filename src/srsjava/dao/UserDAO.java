package srsjava.dao;
 
import java.sql.Connection;
 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 
import srsjava.bean.User;
import srsjava.util.DBUtil;
  
public class UserDAO {
  
//    public int getTotal() {
//        int total = 0;
//        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
//  
//            String sql = "select count(*) from user";
//  
//            ResultSet rs = s.executeQuery(sql);
//            while (rs.next()) {
//                total = rs.getInt(1);
//            }
//        } catch (SQLException e) {
//  
//            e.printStackTrace();
//        }
//        return total;
//    }
  
    public void add(User bean) {
  
        String sql = "insert into user values(? ,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ps.setString(1, bean.getId());
            ps.setString(2, bean.getPassword());
            ps.setString(3, bean.getName());
            ps.setString(4,"student");
            ps.execute();
  
            
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
    }
  
//    public void update(User bean) {
//  
//        String sql = "update user set name= ? , password = ? where id = ? ";
//        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
//  
//            ps.setString(1, bean.getName());
//            ps.setString(2, bean.getPassword());
//            ps.setInt(3, bean.getId());
//  
//            ps.execute();
//  
//        } catch (SQLException e) {
//  
//            e.printStackTrace();
//        }
//  
//    }
  
    public void delete(int id) {
  
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
  
            String sql = "delete from user where id = " + id;
  
            s.execute(sql);
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
    }
  
    public User get(String id) {
        User bean = null;
        
        String sql = "select * from user where id = " + id;
  
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
  
            
  
            ResultSet rs = s.executeQuery(sql);
  
            if (rs.next()) {
                bean = new User();
                String oid = rs.getString("id");
                String password = rs.getString("password");
                String name = rs.getString("name");
                String level = rs.getString("level");
                bean.setName(name);
                bean.setLevel(level);
                bean.setPassword(password);
                bean.setId(oid);
            }
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return bean;
    }
  
    public User getName(String user) {
        User bean = null;
  
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
  
            String sql = "select * from user where name = " + user;
  
            ResultSet rs = s.executeQuery(sql);
  
            if (rs.next()) {
                bean = new User();
                String oid = rs.getString("id");
                String password = rs.getString("password");
                String name = rs.getString("name");
                String level = rs.getString("level");
                bean.setName(name);
                bean.setLevel(level);
                bean.setPassword(password);
                bean.setId(oid);
            }
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return bean;
    }
    
    
    public List<User> list() {
        return list(0, Short.MAX_VALUE);
    }
  
    public List<User> list(int start, int count) {
        List<User> beans = new ArrayList<User>();
  
        String sql = "select * from User order by id desc limit ?,? ";
  
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ps.setInt(1, start);
            ps.setInt(2, count);
  
            ResultSet rs = ps.executeQuery();
  
            while (rs.next()) {
                User bean = new User();
                String id = rs.getString(1);
 
                String password = rs.getString("password");
                bean.setPassword(password);
                 
                bean.setId(id);
                beans.add(bean);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return beans;
    }

	public void setPassword(String id, String password2) {
		// TODO Auto-generated method stub
		String sql = "update user set password = " + password2 + " where id = " + id + "";
		  
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            
            
            ps.execute(sql);
            System.out.println("ÃÜÂëÐÞ¸Ä³É¹¦");
            
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
	}

	
 
    public boolean isExist(String name) {
        User user = get(name);
        return user!=null;
 
    }

	public boolean isExistName(String ruser) {
		// TODO Auto-generated method stub
		User user = getName(ruser);
        return user!=null;
 
	}
 
//    public User get(String name) {
//        User bean = null;
//          
//        String sql = "select * from User where name = ?";
//        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
//            ps.setString(1, name);
//            ResultSet rs =ps.executeQuery();
//  
//            if (rs.next()) {
//                bean = new User();
//                int id = rs.getInt("id");
//                bean.setName(name);
//                String password = rs.getString("password");
//                bean.setPassword(password);
//                bean.setId(id);
//            }
//  
//        } catch (SQLException e) {
//  
//            e.printStackTrace();
//        }
//        return bean;
//    }
 
//    public User get(int id, String password) {
//        User bean = null;
//          
//        String sql = "select * from User where id = ? and password=?";
//        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
//            ps.setInt(1, id);
//            ps.setString(2, password);
//            ResultSet rs =ps.executeQuery();
//  
//            if (rs.next()) {
//                bean = new User();
//                int id = rs.getInt("id");
//                bean.setName(name);
//                bean.setPassword(password);
//                bean.setId(id);
//            }
//  
//        } catch (SQLException e) {
//  
//            e.printStackTrace();
//        }
//        return bean;
//    }
  
}