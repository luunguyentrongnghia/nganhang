/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nganhang.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Thuvien.thuvien;
/**
 *
 * @author Dell
 */
public class JDBCconnection {
   private Connection con = null;
    public   JDBCconnection(){
    String url="net.sourceforge.jtds.jdbc.Driver";
        try {
            Class.forName(url);
            String dbUrl ="jdbc:jtds:sqlserver://DESKTOP-A8J0MGG:1433/Nganhang;instance=SQLEXPRESS;";
             String user="sa";
            String password="12345";
            try{
              con = DriverManager.getConnection(dbUrl,user,password);
            }catch(SQLException ex){
            Logger.getLogger(JDBCconnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JDBCconnection.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
    }

   public void tracuutaikhoan(int noAccount) throws SQLException{
       String sql="Select noAccount from nganhang where noAccount="+noAccount;
        PreparedStatement pstm=con.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()==true) {
            System.out.println("tìm thấy tài khoản");
        }
        else{
            System.out.println("tìm không thấy tài khoản");
        }
    }
     public ResultSet GetResultSet(String tableName) throws SQLException {
        ResultSet rs = null;
        Statement stmt = con.createStatement();
        String sql = "select * from " + tableName;
        rs = stmt.executeQuery(sql);
        return rs;
    }
   public void infoAll() throws SQLException{
      
   ResultSet rs = GetResultSet("nganhang");
       while (rs.next()) {
           System.out.print(rs.getString("noAccount"));
           System.out.print(" - ");
           System.out.print(rs.getString("nameAccount"));
           System.out.print(" - ");
           System.out.println(rs.getString("moneyAccount"));
       }
        
   
   }
   public void createAccont() throws SQLException{
   List<Nguoidung> listuser = new ArrayList<Nguoidung>();
   int n = thuvien.getInt("nhập số lượng người thêm vào hệ thống");
   for(int i = 0;i<n;i++){
   listuser.add(new Nguoidung(thuvien.getInt("nhập số tài khoản"),thuvien.getString("nhập tên tài khoản"),thuvien.getDouble("nhập số tiền")));
   
   }
   String sql = "INSERT INTO nganhang(noAccount,nameAccount,moneyAccount) Values(?,?,?)";
   PreparedStatement pstmt = con.prepareStatement(sql);
   for(Nguoidung user : listuser){
   pstmt.setInt(1, user.getNoAccount());
   pstmt.setString(2, user.getNameAccount());
   pstmt.setDouble(3, user.getMoneyAccount());
   pstmt.execute();
   }
   
   }
   public double testMoney(int noAccount) throws SQLException{
   double soTien=0;
   String sql = "select noAccount,moneyAccount from nganhang where noAccount"+noAccount;
   PreparedStatement pstm = con.prepareStatement(sql);
   ResultSet rs = pstm.executeQuery();
   if(rs.next()==true){
   soTien=Double.parseDouble(rs.getString("moneyAccount"));
   
   }
   return soTien;
   }
  
    
}
