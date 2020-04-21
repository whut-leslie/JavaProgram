package FileSystem;
import java.util.Vector;
import java.util.Hashtable;
import java.util.Enumeration;
import java.sql.*;
@SuppressWarnings("unused")
public class DataProcessing {
	private static Connection conn;
	private static Statement statement;
                private static PreparedStatement preparedStatement;
	private static ResultSet resultSet;
	private static boolean connectToDB=false;
	
	
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
	
	public static void connectToDatabase(String driveName,String url,String user,String password) throws ClassNotFoundException, SQLException {
		Class.forName(driveName);
		conn=DriverManager.getConnection(url, user, password);
        connectToDB=true;
	}
	
	public static void disconnectFromDatabase() {
		if(connectToDB) {
			try {
				resultSet.close();
				statement.close();
				conn.close();
			}catch(SQLException sqlException) {
				sqlException.printStackTrace();
			}finally {
				connectToDB=false;
			}
		}
	}
	

	public static Enumeration<Doc> getAllDocs() throws SQLException{		
		Enumeration<Doc> e ; 
		Hashtable<String,Doc> docs=new Hashtable<String,Doc>();
		Doc temp=null;
		if(!connectToDB) throw new SQLException("Not Connected to Database2.");
		
			 statement = conn.createStatement();
			String sql="select * from doc_info";
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				String str;
				str=resultSet.getString(1);
				String str1;
				str1=resultSet.getString(2);
				String str2;
				str2=resultSet.getString(3);
				 Timestamp timestamp = new Timestamp(System.currentTimeMillis()); 

				String str4;
				str4=resultSet.getString(5);
				System.out.println(str+str1+str2);
				temp= new Doc(str, str2,timestamp,str4,str1);
				docs.put(str,temp);
				}
			e=docs.elements();
			return e;
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
		
		 if(!connectToDB) throw new SQLException("Not Connected to Database0.");
			statement = conn.createStatement();
			
			String sql="select * from doc_info where Id='"+ID+"'";
			resultSet=statement.executeQuery(sql);
			 
		     if(resultSet.next()) return false;
		     sql="insert into doc_info(Id,creator,timestamp,description,filename) values "+"('"+ID+"','"+creator+"','"+timestamp+"','"+description+"','"+filename+"')";
		     if(statement.executeUpdate(sql)>0) return true;
		     else return false;
	}
//	
	public static Doc searchDoc(String ID) throws SQLException {
	//	if (docs.containsKey(ID)) {
	//		Doc temp =docs.get(ID);
	//		return temp;
	//	}
	//	return null;
		Doc temp=null;
		if(!connectToDB) throw new SQLException("Not Connected to Database1.");
			statement = conn.createStatement();
			String sql="select * from doc_info where id='"+ID+"'";
		 resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				String str;
				str=resultSet.getString(1);
				String str1;
				str1=resultSet.getString(2);
				String str2;
				str2=resultSet.getString(3);
				Timestamp timestamp = new Timestamp(System.currentTimeMillis()); 
				String str4;
				str4=resultSet.getString(5);
				System.out.println(str+str2+timestamp+str4+str1);
				temp= new Doc(str,str2,timestamp,str4,str1);
			}
		return temp;
		
	}
	
	
public static User searchUser(String name)throws SQLException{
	User temp=null;
	 if(!connectToDB) throw new SQLException("Not Connected to Database3.");
		statement=conn.createStatement();
		String sql="select * from user_info where username='"+name+"'";
		resultSet = statement.executeQuery(sql);
		if (resultSet.next()) {
			String str=resultSet.getString(1);
			String str1=resultSet.getString(2);
			String str2=resultSet.getString(3);
			temp=new User(str,str1,str2);
			} 
		return temp;
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
	
		
		User temp=null;
		 if(!connectToDB) throw new SQLException("Not Connected to Database3.");
			statement=conn.createStatement();
			
			String sql="select * from user_info where username='"+name+"' && password ='"+password+"'";
			resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				String str=resultSet.getString(1);
				String str1=resultSet.getString(2);
				String str2=resultSet.getString(3);
				temp=new User(str,str1,str2);
				} 
			return temp;
		
	}
	
	public static Enumeration<User> getAllUser()throws SQLException{
//获得全部使用者
		Hashtable<String,User> users=new Hashtable<String,User>();
		 User temp = null;
		 Enumeration<User> e;
		 if(!connectToDB) throw new SQLException("Not Connected to Database.");
		 statement=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		 String sql="select * from user_info";
		 resultSet=statement.executeQuery(sql);
			while (resultSet.next()) {
				User user;
				String str;
				str=resultSet.getString(1);
				String str1;
				str1=resultSet.getString(2);
				String str2;
				str2=resultSet.getString(3);
			temp=new User(str,str1,str2);
				users.put(str, temp);
				}
			
        e=users.elements();
		 return e;
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
	
 if(!connectToDB) throw new SQLException("Not Connected to Database.");
		 
		 statement=conn.createStatement();
		 String sql="select * from user_info where username='"+name+"'";
		 resultSet=statement.executeQuery(sql);

		 if(!resultSet.next()) return false;
		 sql="update user_info set password='"+password+"',role='"+role+"' where username='"+name+"'";
		 if(statement.executeUpdate(sql)>0) return true;
		 else return false;
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
		if(!connectToDB) throw new SQLException("Not Connected to Database.");
		 
		 statement=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		 String sql="select * from user_info where username='"+name+"'";
		 resultSet=statement.executeQuery(sql);
		 
		 if(resultSet.next()) return false;
		 sql="insert into user_info(username,password,role) values "+"('"+name+"','"+password+"','"+role+"')";
		 if(statement.executeUpdate(sql)>0) return true;
		 else return false;
		
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
		if(!connectToDB) throw new SQLException("Not Connected to Database.");
		 
		 statement=conn.createStatement();
		 String sql="select * from user_info where username='"+name+"'";
		 resultSet=statement.executeQuery(sql);
		 
		 if(!resultSet.next()) return false;
		 sql="delete from user_info where username='"+name+"'";
		 if(statement.executeUpdate(sql)>0) return true;
		 else return false;
	
	
	}
	public static boolean deleteDoc(String ID) throws SQLException{
//		if ( !connectToDB ) 
//        throw new SQLException( "Not Connected to Database" );
//	
//	double ranValue=Math.random();
//	if (ranValue>0.5)
//		throw new SQLException( "Error in excecuting Delete" );
	
//	if (docs.containsKey(ID)){
//		docs.remove(ID);
//		return true;
//	}else
//		return false;
	try {
		Statement stmt = conn.createStatement();
		
		String sql="delete from files where id = '"+ID+"'";
		stmt.executeUpdate(sql);
//		return true;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return true;
	
}	


	}
