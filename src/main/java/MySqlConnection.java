import java.sql.*;

public class MySqlConnection
{

    //连接mysql数据库，连接其他的数据库需要改变格式
    String url = "jdbc:mysql://localhost:3306/school";
    String user;//用户名
    String password;//密码
    String sqlInsert;//数据插入
    String sqlSelect;//数据查询
    String sqlUpdate;//数据更新
    String sqlDelete;//数据删除
    Connection con; //连接
    Statement st;  //对象
    ResultSet rs;  //查询结果

    //String sqlStr = "select SNO,SNAME,SAGE,SSEX,SDEPT from student";
    //String sqlUpdate = "update person " +"set Department = 'Marketing' where ID in (1,3)";
    public void connect()throws ClassNotFoundException
    {
        user ="root";//替换成你自已的数据库用户名
        password = "gyt20011228";//这里替换成你自已的数据库用户密码
        try {   //异常处理语句是必需的.否则不能通过编译!
            Class.forName("com.mysql.cj.jdbc.Driver"); //mysql语句
            System.out.println("加载驱动成功!");

            con = DriverManager.getConnection(url, user, password);

            System.out.println("连接数据库成功!");

            st = con.createStatement();
            System.out.println("创建Statement成功!");
        }

        //添加数据
        //st.executeUpdate(sqlStr);
        //System.out.println("添加新数据成功");

        //更新数据
        //st.executeUpdate(sqlUpdate);
        //System.out.println("更新数据成功");

        //查询数据
        //ResultSet rs = st.executeQuery( sqlStr );
        //System.out.println( "查询数据操作成功!" );
        //System.out.println( "----------------!" );

//                while(rs.next())
//                {
//                    System.out.print(rs.getString("SNO") + "   ");
//                    System.out.print(rs.getString("SNAME") + "   ");
//                    System.out.print(rs.getString("SAGE")+"   ");
//                    System.out.print(rs.getString("SSEX")+"   ");
//                    System.out.println(rs.getString("SDEPT")+"  ");
//
//                }
//
//                rs.close();
//                st.close();
//                con.close();
//            }
        catch(SQLException e)
        {
            System.out.println("ErrorCode:"+e.getErrorCode());
            System.out.println("SQLState:"+e.getSQLState());
            System.out.println("reason:"+e.getMessage());
        }

    }
    public void insertMetadata(String carID,String date,boolean rif)throws ClassNotFoundException
    {
        sqlInsert="insert into RentalStore.car"+"VALUES('"+carID+"','"+date+"',"+false+")";
        try
        {
            st.executeUpdate(sqlInsert);
            System.out.println("新数据添加成功");
        } catch (SQLException e)
        {
            System.out.println("ErrorCode:"+e.getErrorCode());
            System.out.println("SQLState:"+e.getSQLState());
            System.out.println("reason:"+e.getMessage());
        }
    }
    public void updateMetadata(String carID,String date,boolean rif)throws ClassNotFoundException
    {
        sqlUpdate="update the RentalStore.car"+"'which carID is'"+carID+"'and set the car's entrydate is'"+date+"'";
        try
        {
            st.executeUpdate(sqlUpdate);
            sqlUpdate="update the RentalStore.car"+"set rented or not is:"+rif+"its carID is"+carID+"'";
            st.executeUpdate(sqlUpdate);
            System.out.println("数据更新成功");
        } catch (SQLException e)
        {
            System.out.println("ErrorCode:"+e.getErrorCode());
            System.out.println("SQLState:"+e.getSQLState());
            System.out.println("reason:"+e.getMessage());
        }
    }

    public void selectMetadata(String carID)throws ClassNotFoundException
    {
        try
        {
            rs=st.executeQuery(sqlSelect);
            System.out.println("数据查询成功");
            while (rs.next())
            {
                System.out.println(rs.getString(("carID")+" "));
                System.out.println(rs.getString("its entrydate is"+" "));
                System.out.println(rs.getBoolean("rented or not?"));
            }
        }
        catch (SQLException e)
        {
            System.out.println("ErrorCode:"+e.getErrorCode());
            System.out.println("SQLState:"+e.getSQLState());
            System.out.println("reason:"+e.getMessage());
        }
    }

    public void deleteMetadata(String carID)throws ClassNotFoundException
    {
        sqlDelete="delete the car's information and its carID is "+carID+" ";
        try
        {
            st.executeUpdate(sqlDelete);
            System.out.println("数据删除成功");
        }
        catch (SQLException e)
        {
            System.out.println("ErrorCode:"+e.getErrorCode());
            System.out.println("SQLState:"+e.getSQLState());
            System.out.println("reason:"+e.getMessage());
        }
    }
    public void closeDatebase()throws ClassNotFoundException
    {
        try
        {
            if(rs!=null)
                rs.close();
            if(st!=null)
                st.close();
            if(con!=null)
                con.close();
            System.out.println("数据库关闭成功");
        }
        catch (SQLException e)
        {
            System.out.println("ErrorCode:"+e.getErrorCode());
            System.out.println("SQLState:"+e.getSQLState());
            System.out.println("reason:"+e.getMessage());
        }
    }

    public void createDatabase()throws ClassNotFoundException
    {
        String databaseCreate="create database RentalStore";
        try
        {
            st.executeUpdate(databaseCreate);
            System.out.println("创建数据库成功");
        }
        catch (SQLException e)
        {
            System.out.println("ErrorCode:"+e.getErrorCode());
            System.out.println("SQLState:"+e.getSQLState());
            System.out.println("reason:"+e.getMessage());
        }
    }

    public void createTable()throws ClassNotFoundException
    {
        String baseUse="use RentalStore";
        String tableCreate = "create table car(" +
                "carId varchar(10) primary key," +
                "entry_date varchar(20) not null," +
                "rented bool not null)";
        try
        {
            st.executeUpdate(baseUse);
            st.executeUpdate(tableCreate);
            System.out.println("创建表成功");
        }
        catch (SQLException e)
        {
            System.out.println("ErrorCode:"+e.getErrorCode());
            System.out.println("SQLState:"+e.getSQLState());
            System.out.println("reason:"+e.getMessage());
        }
    }

    public void deleteDatabase()throws ClassNotFoundException
    {
        String tableDelete="drop database RentalStore";
        try
        {
            st.executeUpdate(tableDelete);
            System.out.println("删除表成功");
        }
        catch (SQLException e)
        {
            System.out.println("ErrorCode:"+e.getErrorCode());
            System.out.println("SQLState:"+e.getSQLState());
            System.out.println("reason:"+e.getMessage());
        }
    }
}

