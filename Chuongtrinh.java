/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nganhang.Dao;

import Thuvien.thuvien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.spi.DirStateFactory;

/**
 *
 * @author Dell
 */
public class Chuongtrinh {
    
    public static void main(String[] args) throws SQLException {
        JDBCconnection kn= new JDBCconnection();
        System.out.println("**Ngân hàng nhà nước Vietj Nam**");
        System.out.println("1*Tra cứu tài khoản");
        System.out.println("2*Hiển thị tất cả tài khoản");
        System.out.println("3*Thêm tài khoản");
        int n=thuvien.getInt("Mời chọn giá trị");
        switch(n){
            case 1:
                int sotk=thuvien.getInt("mời nhập số tài khoản");
                kn.tracuutaikhoan(sotk);
                System.out.println("1* rút tiền");
                System.out.println("2* thêm tiền");
                int chon=thuvien.getInt("mời chọn: ");
                break;
            case 2:
                kn.infoAll();
                break;
            case 3:
                kn.createAccont();
                break;
            default:System.out.println("CHọn giá trị sai!!!!");break;
        
        }
    }
}
