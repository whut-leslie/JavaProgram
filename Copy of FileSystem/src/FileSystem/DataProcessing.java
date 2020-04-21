package FileSystem;
import java.util.Vector;
import java.util.Hashtable;
import java.util.Enumeration;
import java.sql.*;
@SuppressWarnings("unused")
public class DataProcessing {
	
	private static boolean connectToDB=false;
	static Connection conn=null;
	static String sql;
	static String url="jdbc:mysql;//localhost:3306/document";//数据库的url;
	static String a="root";//数据库用户名
	static String b="";//数据库密码
	/*public static Hashtable<String, User> users;//系统中已存在用户的信息放置在哈希表中
	public static Hashtable<String, Doc> docs;

	static {
		users = new Hashtable<String, User>();
		users.put("jack", new Operator("jack","123","operator"));
		users.put("rose", new Browser("rose","123","browser"));
		users.put("kate", new Administrator("kate","123","administrator"));
		Init();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis()); 
		docs = new Hashtable<String,Doc>();
		docs.put("0001",new Doc("0001","jack",timestamp,"Doc Source Java","Doc.java"));
		
	}*/
	
	public static void Init(){
		//if(Math.random()>0.2)
		//	connectToDB=true;
		//else
			//connectToDB=false;
		 try {
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("成功加载MySQL驱动程序");
				
				conn = DriverManager.getConnection(url,a,b);//建立数据库连接
		} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("连接数据库出错！");
			}
		}
	
//
	public static Enumeration<Doc> getAllDocs() throws SQLException{		
		//Enumeration<Doc> e  = docs.elements();
		Vector<Doc> vec=new Vector<Doc>();
		try {
			Statement stmt = conn.createStatement();
			sql="select * from files";
			ResultSet res = stmt.executeQuery(sql);
			while (res.next()) {
				Doc doc;
				String str;
				str=res.getString(1);
				String str1;
				str1=res.getString(2);
				String str2;
				str2=res.getString(3);
				 Timestamp timestamp = new Timestamp(System.currentTimeMillis()); 

				String str4;
				str4=res.getString(5);
				System.out.println(str+str1+str2);
				doc = new Doc(str, str2,timestamp,str4,str1);
				if (doc!=null) {
					vec.add(doc);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vec.elements();
	} 
//	
	public static boolean insertDoc(String ID, String creator, Timestamp timestamp, String description, String filename) throws SQLException{
	//	Doc doc;		
	
	//	if (docs.containsKey(ID))
	//		return false;
	//	else{
	//		doc = new Doc(ID,creator,timestamp,description,filename);
	//		docs.put(ID, doc);
	//		return true;
		try {
			Statement stmt = conn.createStatement();
			
			sql="insert into files values('"+ID+"','"+filename+"','"+creator+"','"+timestamp+"','"+description+"')";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
		
		
	} 
//	
	public static Doc searchDoc(String ID) throws SQLException {
	//	if (docs.containsKey(ID)) {
	//		Doc temp =docs.get(ID);
	//		return temp;
	//	}
	//	return null;
		try{
			Statement stmt = conn.createStatement();
			sql="select * from files where id='"+ID+"'";
			ResultSet res = stmt.executeQuery(sql);
			if (res.next()) {
				String str;
				str=res.getString(1);
				String str1;
				str1=res.getString(2);
				String str2;

				str2=res.getString(3);
				Timestamp timestamp = new Timestamp(System.currentTimeMillis()); 
				String str4;
				str4=res.getString(5);
				System.out.println(str+str2+timestamp+str4+str1);
				return new Doc(str,str2,timestamp,str4,str1);
			}else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
public static User serachUser(String name)throws SQLException{
//	if(!connectToDB)
	//	throw new SQLException("Not Connected to Database");
//	double ranValue=Math.random();
	//if(ranValue>0.5)
		//throw new SQLException("Error in excecuting Query");
//	if (users.containsKey(name)) {
	//	return users.get(name);}
	return null;
}
	
	public static User searchUser(String name, String password)throws SQLException{//查找
		//if(!connectToDB)
			//throw new SQLException("Not Connected to Database");
		//double ranValue=Math.random();
		//if(ranValue>0.5)
			//throw new SQLException("Error in excecuting Query");
		//if (users.containsKey(name)) {
			//User temp =users.get(name);
			//if ((temp.getPassword()).equals(password))
				//return temp;
		//}
		try {
			Statement stmt=conn.createStatement();
			sql="select * from users where name='"+name+"' && password ='"+password+"'";
			ResultSet res = stmt.executeQuery(sql);
			if (res.next()) {
				String str;
				str=res.getString(1);
				String str1;
				str1=res.getString(2);
				String str2;
				str2=res.getString(3);
				System.out.println(str+str1+str2);
				if (str2.equals("operator"))
					return new Operator(str, str1, str2);
				else if (str2.equals("browser"))
					return new Browser(str, str1, str2);
				else if (str2.equals("administrator"))
					return new Administrator(str, str1, str2);
			}else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static Enumeration<User> getAllUser()throws SQLException{
//获得全部使用者
	  Vector<User> vec=new Vector<User>();
	
		try {
			Statement stmt = conn.createStatement();
			sql="select * from users";
			ResultSet res = stmt.executeQuery(sql);
			while (res.next()) {
				User user;
				String str;
				str=res.getString(1);
				String str1;
				str1=res.getString(2);
				String str2;
				str2=res.getString(3);
				System.out.println(str+str1+str2);
				user = User.getUser(str, str1, str2);
				if (user!=null) {
					vec.add(user);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		vec.add();
		return vec.elements();
	}
	
	
	
	
	public static boolean updateUser(String name, String password, String role)throws SQLException{
		//更新
		User user;
		/*if(!connectToDB)
			throw new SQLException("Not Connected to Database");
		double ranValue=Math.random();
		if(ranValue>0.5)
			throw new SQLException("Error in excecuting");
		if (users.containsKey(name)) {
			if (role.equalsIgnoreCase("administrator"))
				user = new Administrator(name,password, role);
			else if (role.equalsIgnoreCase("operator"))
				user = new Operator(name,password, role);
			else
				user = new Browser(name,password, role);
			users.put(name, user);
			return true;
		}else
			return false;*/
		try {
			Statement stmt = conn.createStatement();
			
			sql="update users set name='"+name+"',password = '"+password +"',role ='"
					+role+"' where name='"+name+"'";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
	
	public static boolean insertUser(String name, String password, String role)throws SQLException{
		//插入
		
		User user;
		/*if(!connectToDB)
			throw new SQLException("Not Connected to Database");
		double ranValue=Math.random();
		if(ranValue>0.5)
			throw new SQLException("Error in excecuting");
		if (users.containsKey(name))
			return false;
		else{
			if (role.equalsIgnoreCase("administrator"))
				user = new Administrator(name,password, role);
			else if (role.equalsIgnoreCase("operator"))
				user = new Operator(name,password, role);
			else
				user = new Browser(name,password, role);
			users.put(name, user);
			return true;*/
		try {
			Statement stmt = conn.createStatement();
			
			sql="insert into users values('"+name+"','"+password+"','"+role+"');";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
	
	public static boolean deleteUser(String name) throws SQLException{
		//删除
		/*User user;
		if(!connectToDB)
			throw new SQLException("Not Connected to Database");
		double ranValue=Math.random();
		if(ranValue>0.5)
			throw new SQLException("Error in excecuting");		
		if (users.containsKey(name)){
			users.remove(name);
			return true;
		}else
			return false;)*/
		try {
			Statement stmt = conn.createStatement();
			
			sql="delete from files where name = '"+name+"'";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
		
	}
	
	public static void disconnectFromDB() {
		if (connectToDB){
			// close Statement and Connection            
			try{
//				if (Math.random()>0.5)
//					throw new SQLException( "Error in disconnecting DB" );      
//			}catch ( SQLException sqlException ){                                            
//			    sqlException.printStackTrace();          
			             
			}finally{                                            
				connectToDB = false;              
			}                             
		} 

}
	
	public static void main(String[] args) {		

	}
	}
