package sqlitejdbcdriverconnection;


import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import sqlitejdbcdriverconnection.DataBaseHelper;
import sqlitejdbcdriverconnection.Location;
import sqlitejdbcdriverconnection.MaTranKe;
import sqlitejdbcdriverconnection.Route;
import sqlitejdbcdriverconnection.Station;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author anhkh
 */
public class Manghang {
    int maxvalue = 1000000;
    int max = 2200;
    List<Station> all_station;
    List<Route> all_route;
    DataBaseHelper myData;
    Diem[][] array_time = new Diem[max][200];
    class Diem{
        int id_station;
        int time;
        String phuongtien;
    }
    public Manghang(DataBaseHelper my_data){
        this.myData = my_data;
        all_route = myData.getAllRoute();
        all_station = myData.getAllStation();
    }
    void duyet(){
        int length = all_station.size();
    }
    void runStation(Station station, int chieu){
        JSONArray jsonArray = null;
        if (chieu == 0) jsonArray = station.getRoute_go();
        if (chieu == 1) jsonArray = station.getRoute_return();
        int length = jsonArray.length();
        for (int i = 0; i < length; i++){
            try {
                String code_route = jsonArray.getString(i);
                Route route = myData.getRoutebyCode(code_route);
                runRoute(station, route, chieu);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (chieu == 0) jsonArray = station.getFirst();
        if (chieu == 1) jsonArray = station.getFinish();
        length = jsonArray.length();
        for (int i = 0; i < length; i++){
            try {
                String code_route = jsonArray.getString(i);
                Route route = myData.getRoutebyCode(code_route);
//                System.out.println("khong  " + station.getId() + "  " + route.getCode() + "  " + chieu);
                runRoute(station, route, chieu);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    void runStation2(Station station, int chieu){
        JSONArray jsonArray = null;
        if (chieu == 0) jsonArray = station.getRoute_go();
        if (chieu == 1) jsonArray = station.getRoute_return();
        int length = jsonArray.length();
        for (int i = 0; i < length; i++){
            try {
                String code_route = jsonArray.getString(i);
                Route route = myData.getRoutebyCode(code_route);
                runRoute2(station, route, chieu);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (chieu == 0) jsonArray = station.getFirst();
        if (chieu == 1) jsonArray = station.getFinish();
        length = jsonArray.length();
        for (int i = 0; i < length; i++){
            try {
                String code_route = jsonArray.getString(i);
                Route route = myData.getRoutebyCode(code_route);
                runRoute2(station, route, chieu);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    //TODO chuyen tu canh la noi tiep giua hai tuyen sang canh la noi tu ben den diem dau hoac cuoi tuyen
    void runRoute(Station station, Route route, int chieu){
        ArrayList<Integer> arrayIdStation, arrayTime;
        if (chieu == 0) {
            arrayIdStation = convertToArray(route.getStation_go());
            arrayTime = convertToArray(route.getTime_go());
        }
        else {
            arrayIdStation = convertToArray(route.getStation_return());
            arrayTime = convertToArray(route.getTime_return());
        }
        int id_station = station.getId();
        int id_station_cuoi = arrayIdStation.get(arrayIdStation.size() - 1);
        int time_cuoi = arrayTime.get(arrayIdStation.size() - 1);
        int i = arrayIdStation.size() - 1;
        int id_station_i = arrayIdStation.get(i);
        mark_one[id_station_i] = true;
        while (id_station_i != id_station){
            insertToArray(id_station_cuoi, arrayIdStation.get(i - 1), time_cuoi - arrayTime.get(i - 1), route.getCode());
            --i;
            id_station_i = arrayIdStation.get(i);
            mark_one[id_station_i] = true;
        }

    }
    void runRoute2(Station station, Route route, int chieu){
        ArrayList<Integer> arrayIdStation, arrayTime;
        if (chieu == 0) {
            arrayIdStation = convertToArray(route.getStation_go());
            arrayTime = convertToArray(route.getTime_go());
        }
        else {
            arrayIdStation = convertToArray(route.getStation_return());
            arrayTime = convertToArray(route.getTime_return());
        }
        int id_station = station.getId();
        int i = 0;
        int id_station_i = arrayIdStation.get(i);
        mark_one[id_station_i] = true;
        while (id_station_i != id_station){
            insertToArray(arrayIdStation.get(i + 1), arrayIdStation.get(0), arrayTime.get(i + 1), route.getCode());
            ++i;
            id_station_i = arrayIdStation.get(i);
            mark_one[id_station_i] = true;
        }
    }
        Station getStation(int id_station){
        
        return all_station.get(id_station - 1);
    }
        ArrayList<Integer> convertToArray(JSONArray jsonArray) {
        ArrayList<Integer> list = new ArrayList<>();
        if (jsonArray != null) {
            int len = jsonArray.length();
            for (int i = 0; i < len; i++) {
                try {
                    list.add(jsonArray.getInt(i));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    
}
