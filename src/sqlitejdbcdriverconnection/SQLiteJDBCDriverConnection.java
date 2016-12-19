/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlitejdbcdriverconnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.json.JSONArray;


/**
 *
 * @author anhkh
 */
public class SQLiteJDBCDriverConnection {


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DataBaseHelper data = new DataBaseHelper();
        JSONArray jsonArray = new JSONArray();
        Trip trip = new Trip(2, 3, 34, jsonArray);
        data.addTrip(trip);

//        Location diem1 = new Location("Bat dau", 21.049468, 105.785459);
//        Location diem2 = new Location("Ket thuc",  20.982390, 105.791012);
//
//        MaTranKe maTranKe = new MaTranKe(diem1, diem2, data);
//        maTranKe.tinh();
//        maTranKe.Dij();
        
    }
//    public static void connect() {
//        try {
//
//            String url = "jdbc:sqlite:C:/Users/anhkh/AndroidStudioProjects/khoai_bus/app/src/main/assets/dbname.sqlite";
//            // create a connection to the database
//            conn = DriverManager.getConnection(url);
//            
//            System.out.println("Connection to SQLite has been established.");
//            
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        } finally {
//            try {
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException ex) {
//                System.out.println(ex.getMessage());
//            }
//        }
//    }
}
