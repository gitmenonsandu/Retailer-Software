/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingmall;

import java.sql.Connection;
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
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/test";
    
    static final String USER = "root";
    static final String PASS = "1234";
    
    static Connection conn=null;
    
    static SqlLogin sqlData= new SqlLogin();
    static ObservableList<ObservableList> data;

   
    private SqlLogin(){
        
    }
    
    public static SqlLogin getUser() throws SQLException{
        SqlLogin.connectDatabase();
        return sqlData;
    }
    
    static void connectDatabase() throws SQLException{
        
        try {
                Class.forName(JDBC_DRIVER);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SqlLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("Connecting to database");
            conn=DriverManager.getConnection(DB_URL,USER,PASS);
    }
    static String getShopName() throws SQLException{
        
        SqlLogin.connectDatabase();      
        Statement stmt=null;
        stmt=SqlLogin.conn.createStatement();
        ResultSet rs;
        rs=stmt.executeQuery("select shopName from ShopTable");
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
        
        SqlLogin.connectDatabase();      
        Statement stmt=null;
        stmt=SqlLogin.conn.createStatement();
        ResultSet rs;
        rs=stmt.executeQuery("select shopPassword from ShopTable");
        String shopPassword = null;
        while(rs.next()){
            shopPassword=rs.getString("shopPassword");
        }
        rs.close();
        stmt.close();
        SqlLogin.conn.close();
        return shopPassword;
    }
    
    static void insertItem(Item item) throws SQLException{
        SqlLogin.connectDatabase();      
        PreparedStatement stmt=null;
     
        String query="insert into ItemTable(`itemName`,`itemPrice`,`itemQuantity`,`itemCategory`,`itemOffer`,"
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
     static void insertOffer(Offer offer) throws SQLException {
         SqlLogin.connectDatabase();      
        PreparedStatement stmt=null;
     
        String query="insert into OfferTable(`offerDesc`,`offerUsers`,`offerCategory`) values(?,?,?)";
        stmt=SqlLogin.conn.prepareStatement(query);
        stmt.setString(1,offer.getOfferDesc());
        stmt.setInt(2, offer.getOfferUses());
        stmt.setString(3, offer.getOfferCategory());
        
        System.out.println(stmt);
        stmt.executeUpdate();
        
        stmt.close();
        SqlLogin.conn.close();
    }
    
    static void getTable(String query, TableView table) throws SQLException{
        data=FXCollections.observableArrayList();
        Statement stmt=null;
        
            try{
               
                SqlLogin.connectDatabase();      
                stmt=SqlLogin.conn.createStatement();
                ResultSet rs;
                rs = stmt.executeQuery(query);

                //adding columns
                if(table!=null){
                for(int i=0;i<rs.getMetaData().getColumnCount();++i){
                    final int j=i;
                    TableColumn col=new TableColumn(rs.getMetaData().getColumnName(i+1));
                    col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
                        @Override
                        public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {                                                                                              
                            return new SimpleStringProperty(param.getValue().get(j).toString());                        
                        }                    
                    });
                    table.getColumns().addAll(col);
                }
                }

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
