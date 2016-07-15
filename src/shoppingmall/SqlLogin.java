/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingmall;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

/**
 *
 * @author sandukuttan
 */

//singleton
public class SqlLogin {
    
    //local databasecredential
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/ShoppingMall";
    
    static final String USER = "root";
    static final String PASS = "1234";
    
    //bluemix credential
    static final String BLUEMIX_URL="jdbc:mysql://us-cdbr-iron-east-04.cleardb.net/ad_d50e86b971b02f2";
    static final String BLUEMIX_USER="bf87fb0cec2402";
    static final String BLUEMIX_PASS="60fcb38d";
    static Connection conn=null;
    
    static SqlLogin sqlData= new SqlLogin();
    static ObservableList<ObservableList> data;     //the result of SQLQuery stored... SqlLogin.data -> use after SqlLogin.getTable()

   
    private SqlLogin(){
        
    }
    
    public static SqlLogin getUser() throws SQLException{
        SqlLogin.connectLocalDatabase();
        return sqlData;
    }
    
    static void connectLocalDatabase() throws SQLException{
        
        try {
                Class.forName(JDBC_DRIVER);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SqlLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("Connecting to local database");
            conn=DriverManager.getConnection(DB_URL,USER,PASS);
    }
    static void connectGlobalDatabase() throws SQLException{
        
        try {
                Class.forName(JDBC_DRIVER);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SqlLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("Connecting to Global database");
            conn=DriverManager.getConnection(BLUEMIX_URL,BLUEMIX_USER,BLUEMIX_PASS);
    }
    static String getShopName() throws SQLException{
        
        SqlLogin.connectLocalDatabase();      
        Statement stmt=null;
        stmt=SqlLogin.conn.createStatement();
        ResultSet rs;
        rs=stmt.executeQuery("select shopName from shoptable");
        String shopName = null;
        while(rs.next()){
            shopName=rs.getString("shopName");
        }
        rs.close();
        stmt.close();
        SqlLogin.conn.close();
        return shopName;
    }
    
    static String getShopPassword() throws SQLException{
        
        SqlLogin.connectLocalDatabase();      
        Statement stmt=null;
        stmt=SqlLogin.conn.createStatement();
        ResultSet rs;
        rs=stmt.executeQuery("select shopPassword from shoptable");
        String shopPassword = null;
        while(rs.next()){
            shopPassword=rs.getString("shopPassword");
        }
        rs.close();
        stmt.close();
        SqlLogin.conn.close();
        return shopPassword;
    }
    
    static void insertItem(Item item,String database) throws SQLException{
        if(database.equals("global"))
            SqlLogin.connectGlobalDatabase();
        else if(database.equals("local"))
            SqlLogin.connectLocalDatabase();
        else
            return ;
        PreparedStatement stmt=null;
     
        String query="insert into itemtable(`itemName`,`itemPrice`,`itemQuantity`,`itemCategory`,`itemOffer`,"
                + "`itemImage`,`itemDiscount`) values(?,?,?,?,?,?,?)";
        stmt=SqlLogin.conn.prepareStatement(query);
        stmt.setString(1,item.getItemName());
        stmt.setFloat(2, item.getItemPrice());
        stmt.setInt(3, item.getItemQuantity());
        stmt.setString(4,item.getItemCategory());
        stmt.setBoolean(5, item.isItemOfferAvailability());
        stmt.setString(6,item.getItemImage());
        stmt.setFloat(7, item.getItemDiscount());
        
        System.out.println(stmt);
        stmt.executeUpdate();
        
        stmt.close();
        SqlLogin.conn.close();
      
    }
     static void insertOffer(Offer offer,String database) throws SQLException {
        if(database.equals("global"))
            SqlLogin.connectGlobalDatabase();
        else if(database.equals("local"))
            SqlLogin.connectLocalDatabase();
        else
            return ;      
        PreparedStatement stmt=null;
     
        String query="insert into offertable(`offerDesc`,`offerExpiryDate`,`offerUsers`,`offerCategory`,`offerImage`,`minimPurchase`,`onBuying`,`startDate`) values(?,?,?,?,?,?,?,?)";
        stmt=SqlLogin.conn.prepareStatement(query);
        
        stmt.setString(1,offer.getOfferDesc());
        stmt.setDate(2, offer.getOfferExpiry());
        stmt.setInt(3, offer.getOfferUses());
        stmt.setString(4,offer.getOfferCategory());
        stmt.setString(5,offer.getImage());
        stmt.setInt(6, offer.getMinimumPurchase());
        stmt.setInt(7, offer.getOnBuying());
        stmt.setDate(8, offer.getStartDate());
        
        System.out.println(stmt);
        stmt.executeUpdate();
        
        stmt.close();
        SqlLogin.conn.close();
    }
     
     //store offer details of particular items. itemID and offerID are foreign keys
    static void insertItemOffer(String database,Integer itemID,Integer offerID,boolean param1,int param2,int param3,Date param41,Date param42) throws SQLException{
        if(database.equals("local"))
            SqlLogin.connectLocalDatabase();
        else if(database.equals("global"))
            SqlLogin.connectGlobalDatabase();
        else
            return;
        
        PreparedStatement stmt=null;
     
        String query="insert into itemoffertable(`itemID`,`offerID`,`isAll`,`itemsOlderThan`,`itemsAddedInLast`,`itemsAddedFrom`,`itemsAddedTill`) values(?,?,?,?,?,?,?)";
        stmt=SqlLogin.conn.prepareStatement(query);
        
        stmt.setInt(1, itemID);
        stmt.setInt(2,offerID);
        stmt.setBoolean(3, param1);
        stmt.setInt(4,param2);
        stmt.setInt(5,param3);
        stmt.setDate(6, param41);
        stmt.setDate(7, param42);
        
        System.out.println(stmt);
        stmt.executeUpdate();
        
        stmt.close();
        SqlLogin.conn.close();
    }
    
    //to get offerID of the latest offer added
    //since offerID is generated automatic, it is retrieved after and offer is added
    static int getLastOfferID(String database) throws SQLException{
        if(database.equals("local"))
            SqlLogin.connectLocalDatabase();
        else if(database.equals("global"))
            SqlLogin.connectGlobalDatabase();
        else
            return -1;
        
        Statement stmt=null;
        stmt=SqlLogin.conn.createStatement();
        ResultSet rs;
        rs=stmt.executeQuery("select offerID from offertable ORDER BY offerID DESC LIMIT 1");
        Integer offerID=null;
        while(rs.next()){
            offerID=rs.getInt("offerID");
        }
        rs.close();
        stmt.close();
        SqlLogin.conn.close();
        return offerID;
        
    }
    
    //the query is executed and the data is stored in SqlLogin.data
    static void getTable(String query) throws SQLException{
        data=FXCollections.observableArrayList();
        Statement stmt=null;
        
            try{
               
                SqlLogin.connectLocalDatabase();      
                stmt=SqlLogin.conn.createStatement();
                ResultSet rs;
                rs = stmt.executeQuery(query);

                //adding data to observable list
                while(rs.next()){
                    ObservableList<String> row= FXCollections.observableArrayList();
                    for(int i=1;i<=rs.getMetaData().getColumnCount();++i){
                        if(rs.getString(i)!=null)
                            row.add(rs.getString(i));
                        else
                            row.add("null");
                    }
                    data.add(row);
                }
                    rs.close();
                    stmt.close();
                    SqlLogin.conn.close();
            }
            catch(SQLException se)
                {
                    System.out.println("SQL");

                }
                catch(Exception e)
                {
                    System.out.println("Class");

                }
                finally{
              //finally block used to close resources
              try{
                 if(stmt!=null)
                    stmt.close();
              }catch(SQLException se2){
              }// nothing we can do
              try{
                 if(SqlLogin.conn!=null)
                    SqlLogin.conn.close();
              }catch(SQLException se){
              }//end finally try
     
            }
            
               
    
        }
}
