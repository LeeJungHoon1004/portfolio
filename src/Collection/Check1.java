package Collection;



import java.io.*;
import java.sql.*;
import oracle.jdbc.*;
 
 
public class Check1
{
    public static void main(String [] args) throws Exception
    {
 
        BufferedReader reader=null;
        try
        {
            reader = new BufferedReader(new InputStreamReader(System.in));
 
            System.out.println("1. Insert rows");
            System.out.println("2. Select rows");
            System.out.println("Enter your option : ");
 
            int option = Integer.parseInt(reader.readLine());
 
            switch(option)
            {
                case 1:
                    insertRows();
                    break;
 
                case 2:
                    selectRows();
                    break;
                default:
                    System.out.println("Pls enter your option between 1 to 2");
            }
 
 
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
 
    public static void insertRows()
    {
        Connection connection =null;
 
        try
        {
                Class.forName("oracle.jdbc.driver.OracleDriver");
 
                connection = DriverManager.getConnection("jdbc:oracle:thin:@servername:1521:orcl", "username", "password");
 
                Data1 data1=new Data1(300,500);
 
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream objOstream = new ObjectOutputStream(baos);
                objOstream.writeObject(data1);
                byte[] bArray = baos.toByteArray();
 
                System.out.println("*** bArray = " + bArray);
 
                PreparedStatement objStatement = connection.prepareStatement("insert into sample(user_id,data_object) values (?,?)");
 
                objStatement.setInt(1,10);
                objStatement.setBytes(2, bArray);
                objStatement.execute();
 
        }
        catch(SQLException sqlEx)
        {
            sqlEx.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            connection=null;
        }
 
    }
 
    public static void selectRows()
    {
        Statement stmt = null;
        ResultSet rs = null;
        String sQuery ="";
 
        Connection connection =null;
 
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
 
            connection = DriverManager.getConnection("jdbc:oracle:thin:@servername:1521:orcl", "username", "password");
 
            sQuery = "select * from sample";
            byte [] newArray = null;
            stmt = connection.createStatement();
            stmt.executeQuery(sQuery);
            rs = stmt.getResultSet();
 
            while(rs.next())
            {
                newArray = rs.getBytes("data_object");
 
                System.out.println("newArray = " + newArray);
 
                ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(newArray));
 
                Data1 objData1 = (Data1) ois.readObject();
 
                ois.close();
 
                if(objData1 == null)
                {
                    System.out.println("Data1 Object is null");
                }
                else
                {
                    System.out.println("a = " + objData1.geta() + " b = " + objData1.getb());
                }
 
            }
            rs.close();
 
        }
        catch(SQLException sqlEx)
        {
            sqlEx.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            connection=null;
        }
    }
}
 
 
