package sqlitejdbcdriverconnection;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


import org.json.JSONArray;
import org.json.JSONException;

import java.sql.*;

public class DataBaseHelper
{
    private Connection con;
    private Statement st;
    private ResultSet re;
    
    private static String TAG = "DataBaseHelper"; // Tag just for the LogCat window
    //destination path (location) of our database on device
    private static String DB_PATH = "";
    private static String DB_NAME ="dbname.sqlite";// Database name
    

    private static final String TABLE_STATION = "tbl_station";
    private static final String TABLE_ROUTE = "tbl_route";
    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_LON = "lon";
    public static final String KEY_LAT = "lat";
    public static final String KEY_STATION_GO = "station_go";
    public static final String KEY_STATION_RETURN = "station_return";
    public static final String KEY_ROUTE_GO = "route_go";
    public static final String KEY_ROUTE_RETURN = "route_return";
    public static final String KEY_COST = "cost";
    public static final String KEY_TGHD = "tghd";
    public static final String KEY_TGDK = "tgdk";
    public static final String KEY_TS = "ts";
    public static final String KEY_DV = "dv";
    public static final String KEY_CODE = "code";
    public static final String KEY_FIRST = "first";
    public static final String KEY_FINISH = "finish";
    public static final String KEY_TIME_AVG_GO = "time_avg_go";
    public static final String KEY_TIME_AVG_RETURN = "time_avg_return";
    public static final String KEY_TIME_GO = "time_go";
    public static final String KEY_TIME_RETURN = "time_return";

    public DataBaseHelper() {
        try{
            String url = "jdbc:sqlite:C:/Users/anhkh/AndroidStudioProjects/khoai_bus/app/src/main/assets/dbname.sqlite";
            // create a connection to the database
            con = DriverManager.getConnection(url);
            st = con.createStatement();
        }catch(Exception ex){
            System.out.println("Erro:" + ex);
        }
        
    }
    public void getData(){
        try{
            String query = "select * from tbl_route";
            re = st.executeQuery(query);
            while(re.next()){
                String name = re.getString("name");
                System.out.println(name);
            }
        }catch(Exception ex){
            
        }
    }




    public List<Route> getAllRoute() {
        ArrayList<Route> names = new ArrayList<Route>();
        try{
            String query = "SELECT *"  +
                " FROM " + TABLE_ROUTE;     
            re = st.executeQuery(query);
//            if (re.first())
//                System.out.println(re.getString(0));
            while(re.next()){
                Route route = new Route();
                route.setID(Integer.parseInt(re.getString(KEY_ID)));
                route.setName(re.getString(KEY_NAME));
                route.setCode(re.getString(KEY_CODE));
                route.setCost(Integer.parseInt(re.getString(KEY_COST)));
                route.setTghd(re.getString(KEY_TGHD));
                route.setTgdk(re.getString(KEY_TGDK));
                route.setTs(re.getString(KEY_TS));
                route.setDv(re.getString(KEY_DV));
                try {
                    route.setStation_go(new JSONArray(re.getString(KEY_STATION_GO)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    route.setStation_return(new JSONArray(re.getString(KEY_STATION_RETURN)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                route.setTime_avg_go(Integer.parseInt(re.getString(KEY_TIME_AVG_GO)));
                route.setTime_avg_return(Integer.parseInt(re.getString(KEY_TIME_AVG_RETURN)));
                try {
                    route.setTime_go(new JSONArray(re.getString(KEY_TIME_GO)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    route.setTime_return(new JSONArray(re.getString(KEY_TIME_RETURN)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                names.add(route);
            }
        }catch(Exception ex){
            
        }
        return names;
    }
    
    public Station getStation(int id) {
        Station station = new Station();
        try{
            String sql = "SELECT * FROM tbl_station WHERE _id="+id;        
            re = st.executeQuery(sql);
            if  (re.next()) {
                station.setId(Integer.parseInt(re.getString(KEY_ID)));
                Location location = new Location(re.getString(KEY_NAME));
                location.setlatitude(Double.parseDouble(re.getString(KEY_LAT)));
                location.setlongitude(Double.parseDouble(re.getString(KEY_LON)));
                station.setLocation(location);
                try {
                    station.setRoute_go(new JSONArray(re.getString(KEY_ROUTE_GO)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    station.setRoute_return(new JSONArray(re.getString(KEY_ROUTE_RETURN)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    station.setFirst(new JSONArray(re.getString(KEY_FIRST)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    station.setFinish(new JSONArray(re.getString(KEY_FINISH)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }catch(Exception ex){
            
        }
        return station;
    }
    
    public Route getRoutebyCode(String code) {
        Route route = new Route();
        try{
            String query = "SELECT *"  +
                " FROM " + TABLE_ROUTE +
                " WHERE code='" + code + "'";      
            re = st.executeQuery(query);
            if  (re.next()) {
                route.setID(Integer.parseInt(re.getString(KEY_ID)));
                route.setName(re.getString(KEY_NAME));
                route.setCode(re.getString(KEY_CODE));
                route.setCost(Integer.parseInt(re.getString(KEY_COST)));
                route.setTghd(re.getString(KEY_TGHD));
                route.setTgdk(re.getString(KEY_TGDK));
                route.setTs(re.getString(KEY_TS));
                route.setDv(re.getString(KEY_DV));
                try {
                    route.setStation_go(new JSONArray(re.getString(KEY_STATION_GO)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    route.setStation_return(new JSONArray(re.getString(KEY_STATION_RETURN)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                route.setTime_avg_go(Integer.parseInt(re.getString(KEY_TIME_AVG_GO)));
                route.setTime_avg_return(Integer.parseInt(re.getString(KEY_TIME_AVG_RETURN)));
                try {
                    route.setTime_go(new JSONArray(re.getString(KEY_TIME_GO)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    route.setTime_return(new JSONArray(re.getString(KEY_TIME_RETURN)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }catch(Exception ex){
            
        };
        return route;
    }
    
//    public void addRoute(Route route) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//
//        values.put(KEY_NAME, route.getName());
//        values.put(KEY_CODE, route.getCode());
//        values.put(KEY_COST, route.getCost());
//        values.put(KEY_TGHD, route.getTgdh());
//        values.put(KEY_TGDK, route.getTgdk());
//        values.put(KEY_TS, route.getTs());
//        values.put(KEY_DV, route.getDv());
//        values.put(KEY_STATION_GO, route.getStation_go().toString());
//        values.put(KEY_STATION_RETURN, route.getStation_return().toString());
//        values.put(KEY_TIME_AVG_GO, route.getTime_avg_go());
//        values.put(KEY_TIME_AVG_RETURN, route.getTime_avg_return());
//        values.put(KEY_TIME_GO, route.getTime_go().toString());
//        values.put(KEY_TIME_RETURN, route.getTime_return().toString());
//
//        db.insert(TABLE_ROUTE, null, values);
//        db.close();
//    }
//
//    public Route getRoutebyID(int id) {
//        Route route = new Route();
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.query(TABLE_ROUTE, new String[] {KEY_ID, KEY_NAME, KEY_CODE, KEY_COST, KEY_TGHD, KEY_TGDK, KEY_TS, KEY_DV, KEY_STATION_GO, KEY_STATION_RETURN, KEY_TIME_AVG_GO, KEY_TIME_AVG_RETURN, KEY_TIME_GO, KEY_TIME_RETURN}, KEY_ID + "=?",
//                new String[] {String.valueOf(id)}, null, null, null, null);
//        if  (cursor != null) {
//            cursor.moveToFirst();
//            route.setID(Integer.parseInt(cursor.getString(0)));
//            route.setName(cursor.getString(1));
//            route.setCode(cursor.getString(2));
//            route.setCost(Integer.parseInt(cursor.getString(3)));
//            route.setTghd(cursor.getString(4));
//            route.setTgdk(cursor.getString(5));
//            route.setTs(cursor.getString(6));
//            route.setDv(cursor.getString(7));
//            try {
//                route.setStation_go(new JSONArray(cursor.getString(8)));
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            try {
//                route.setStation_return(new JSONArray(cursor.getString(9)));
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            route.setTime_avg_go(Integer.parseInt(cursor.getString(10)));
//            route.setTime_avg_return(Integer.parseInt(cursor.getString(11)));
//            try {
//                route.setTime_go(new JSONArray(cursor.getString(12)));
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            try {
//                route.setTime_return(new JSONArray(cursor.getString(13)));
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//        cursor.close();
//        return route;
//    }
//    public int getIdRoute(String code) {
//        int id = 0;
//        SQLiteDatabase db = this.getReadableDatabase();
//        String query = "select _id from tbl_route where code='" + code + "'";
//        Cursor cursor = db.rawQuery(query, null);
//
//        if  (cursor != null) {
//            cursor.moveToFirst();
//            id = Integer.parseInt(cursor.getString(0));
//        }
//        cursor.close();
//        return id;
//    }
    public List<Station> getAllStation() {

        ArrayList<Station> names = new ArrayList<Station>();
        try{
            String query = "SELECT *"  +
                    " FROM " + TABLE_STATION;       
            re = st.executeQuery(query);
            while(re.next()){
                Station station = new Station();
                station.setId(Integer.parseInt(re.getString(KEY_ID)));
                Location location = new Location(re.getString(KEY_NAME));
                location.setlatitude(Double.parseDouble(re.getString(KEY_LAT)));
                location.setlongitude(Double.parseDouble(re.getString(KEY_LON)));
                station.setLocation(location);
                try {
                    station.setRoute_go(new JSONArray(re.getString(KEY_ROUTE_GO)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    station.setRoute_return(new JSONArray(re.getString(KEY_ROUTE_RETURN)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    station.setFirst(new JSONArray(re.getString(KEY_FIRST)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    station.setFinish(new JSONArray(re.getString(KEY_FINISH)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                names.add(station);
            }
        }catch(Exception ex){
            
        }
        return names;
    }
     public List<Station> getStationsRange(LatLng latLng, int range) {
        Location location1 = new Location("chu");
        location1.setlatitude(latLng.latitude);
        location1.setlongitude(latLng.longitude);
        ArrayList<Station> names = new ArrayList<Station>();
        try{
            String query = "SELECT *"  +
                " FROM " + TABLE_STATION;
            re = st.executeQuery(query);
            
            while(re.next()){
                Station station = new Station();
                station.setId(Integer.parseInt(re.getString(KEY_ID)));
                Location location = new Location(re.getString(KEY_NAME));
                location.setlatitude(Double.parseDouble(re.getString(KEY_LAT)));
                location.setlongitude(Double.parseDouble(re.getString(KEY_LON)));
                station.setLocation(location);
                try {
                    station.setRoute_go(new JSONArray(re.getString(KEY_ROUTE_GO)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    station.setRoute_return(new JSONArray(re.getString(KEY_ROUTE_RETURN)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    station.setFirst(new JSONArray(re.getString(KEY_FIRST)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    station.setFinish(new JSONArray(re.getString(KEY_FINISH)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if  (location.distanceTo(location1) < range)
                names.add(station);
            }
        }catch(Exception ex){
            
        }
        return names;
    
        
    }
//    public int updateRoute(Route route) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//
//        values.put(KEY_STATION_GO, route.getStation_go().toString());
//        values.put(KEY_STATION_RETURN, route.getStation_return().toString());
//        values.put(KEY_TIME_AVG_GO, route.getTime_avg_go());
//        values.put(KEY_TIME_AVG_RETURN, route.getTime_avg_return());
//        values.put(KEY_TIME_GO, route.getTime_go().toString());
//        values.put(KEY_TIME_RETURN, route.getTime_return().toString());
//
//        return db.update(TABLE_ROUTE, values, KEY_ID + " = ?", new String[] {String.valueOf(route.getID())});
//    }
//    public int getVersion(){
//        SQLiteDatabase db = this.getReadableDatabase();
//        String query = "PRAGMA user_version";
//        Cursor cursor = db.rawQuery(query, null);
//        cursor.moveToFirst();
//        int version = Integer.parseInt(cursor.getString(0));
//        return version;
//    }
}