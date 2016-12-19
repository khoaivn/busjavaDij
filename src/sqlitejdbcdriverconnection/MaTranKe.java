package sqlitejdbcdriverconnection;

//import android.location.Location;

//import com.google.android.gms.location.places.Place;
//import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TruDu on 12/16/16.
 */

public class MaTranKe {
    int maxvalue = 1000000;
    int max = 2200;
    Location placeOne;
    Location placeTwo;
    DataBaseHelper myData;
    Diem[][] array_time = new Diem[max][200];
    List<Station> all_station;
    List<Route> all_route;
    int id_start;
    int id_finish;
    DiemDij[] ngannhat = new DiemDij[max];
    boolean[] mark_one = new boolean[max];
    class Diem{
        int id_station;
        int time;
        String phuongtien;
    }
    class DiemDij{
        boolean mark;
        int time;
        int id_station_noi;
    }
    public MaTranKe(Location place1, Location place2, DataBaseHelper my_data){
        this.placeOne = place1;
        this.placeTwo = place2;
        this.myData = my_data;
        all_route = myData.getAllRoute();
        all_station = myData.getAllStation();
        int length = all_station.size();
        id_start = length;
        id_finish = length + 1;
        all_station.add(convertPlace(placeOne, id_start));
        all_station.add(convertPlace(placeTwo, id_finish));
//        System.out.println(mark_one[0]);
        for (int i = 0; i < max; ++i) {
            for (int j = 0; j < 200; ++j)
                array_time[i][j] = new Diem();
            array_time[i][0].id_station = 0;
        }
        for (int i = 0; i < max; ++i)
            ngannhat[i] = new DiemDij();

    }
    Station convertPlace(Location place, int id){
        Location location = new Location((String) place.getprovider());
        location.setlatitude(place.getLatLng().latitude);
        location.setlongitude(place.getLatLng().longitude);
        Station station = new Station(id, location);
        return station;
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
//        if  (id_station_i != id_station) {
//            mark_one[arrayIdStation.get(i - 1)] = true;
//            insertToArray(arrayIdStation.get(i), arrayIdStation.get(i - 1), arrayTime.get(i));
//        }
//        else if (arrayIdStation.get(i - 1) != id_station) {
//            while (id_station_i != id_station) {
//                --i;
//                id_station_i = arrayIdStation.get(i - 1);
//                mark_one[id_station_i] = true;
//                insertToArray(arrayIdStation.get(i), arrayIdStation.get(i - 1), arrayTime.get(i));
//                insertToArray(arrayIdStation.get(i + 1), arrayIdStation.get(i), arrayTime.get(i + 1));
//            }
//            insertToArray(arrayIdStation.get(i), arrayIdStation.get(i - 1), arrayTime.get(i));
//        }

//        int length = jsonArray.length();
//        int v = getLocation(station.getId(), jsonArray);
//
//        for (int i = v ; i < length; ++i){
//            try {
//                if (i == v){
//                    int
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//        return result;
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
    void inmatran(){
        for (int i = 0; i < max; ++i){
            if (array_time[i][0].id_station != 0) {
                String string = i + ":";
                for (int j = 0; j <= array_time[i][0].id_station; ++j) {
                    string += " (" + array_time[i][j].id_station + "," + array_time[i][j].time + "," + array_time[i][j].phuongtien + ") ";
                }
                System.out.println(string);
            }
        }
    }
    void inNganNhat(){
        for (int i = 0; i < max; ++i){
            if (ngannhat[i].time != 0 && ngannhat[i].time != maxvalue)
                System.out.println( i + ":  " + ngannhat[i].id_station_noi + " " + ngannhat[i].time);
        }
    }
    void tinh(){
        ArrayList<Integer> list_one = new ArrayList<>();
        ArrayList<Integer> list_two = new ArrayList<>();
        int thoigian = Chung.tinhthoigiandibo(placeOne.getLatLng(), placeTwo.getLatLng());

        List<Station> list_station_one = myData.getStationsRange(placeOne.getLatLng(), 1000);
        for (int i = 0; i < max; ++i) mark_one[i] = false;
        for (Station station : list_station_one) {
            mark_one[station.getId()] = true;
            insertToArray(station.getId(), id_start, Chung.tinhthoigiandibo(placeOne.getLatLng(), Chung.getLatLngfromLocation(station.getLocation())), "bo");
            runStation(station, 0);
            runStation(station, 1);
        }
        for (int i = 0; i < max; ++i)
            if (mark_one[i])
                list_one.add(i);

        List<Station> list_station_two = myData.getStationsRange(placeTwo.getLatLng(), 1000);
        for (int i = 0; i < max; ++i) mark_one[i] = false;
        for (Station station : list_station_two) {
            mark_one[station.getId()] = true;
            insertToArray(id_finish, station.getId(), Chung.tinhthoigiandibo(placeTwo.getLatLng(), Chung.getLatLngfromLocation(station.getLocation())), "bo");
            runStation2(station, 0);
            runStation2(station, 1);
        }
        for (int i = 0; i < max; ++i)
            if (mark_one[i])
                list_two.add(i);
        for (int i : list_one)
            for (int j : list_two){
//                System.out.println(i + "  " + j);
                int thoi = Chung.tinhthoigiandibo(Chung.getLatLngfromLocation(getStation(i).getLocation()),Chung.getLatLngfromLocation(getStation(j).getLocation()));
                if (thoi < thoigian / 2)
                    insertToArray(j, i, thoi, "bo");
            }
        inmatran();
        for (int i : list_one){
//            DiemDij diemDij = new DiemDij();
            ngannhat[i].mark = true;
            ngannhat[i].time = maxvalue;
        }
        for (int i : list_two){
            ngannhat[i].mark = true;
            ngannhat[i].time = maxvalue;
        }
        for (Station station : list_station_two){
            ngannhat[station.getId()].time = Chung.tinhthoigiandibo(placeTwo.getLatLng(), Chung.getLatLngfromLocation(station.getLocation()));
            ngannhat[station.getId()].id_station_noi = id_finish;
        }
        ngannhat[id_start].mark = true;
        ngannhat[id_start].time = maxvalue;
//        for (Station station : list_station_two)
//            System.out.println(station.getId() + "  " + station._location.getprovider());
//        for (int i = 0; i < max; ++i)
//            System.out.println("Ngan nhat  " + ngannhat[i].id_station + "  " + ngannhat[i].time + "  " + ngannhat[i].mark);

//        for (int i = 0; i <= array_time[id_start][0].id_station; ++i){
//            System.out.println(array_time[id_start][i].id_station);
//        }
    }
    void Dij(){

        
        while (getMinNganNhat() != 0){
            inNganNhat();
            int v = getMinNganNhat();
            System.out.println(v + "------------------------");
//            System.out.println(v);
            ngannhat[v].mark = false;
            
            for (int i = 1; i <= array_time[v][0].id_station; ++i){
                if (v == 18) System.out.println("tuyet " + array_time[v][i].id_station + "  " + ngannhat[array_time[v][i].id_station].time);
                if (ngannhat[array_time[v][i].id_station].time > ngannhat[v].time + array_time[v][i].time) {
                    ngannhat[array_time[v][i].id_station].time = ngannhat[v].time + array_time[v][i].time;
                    ngannhat[array_time[v][i].id_station].id_station_noi = v;
                }

            }
        }
        inNganNhat();
//        for (int i = 0; i < max; ++i)
//            if (ngannhat[i].time != 0)
//                System.out.println(i +  "  " + ngannhat[i].id_station_noi + "  " + ngannhat[i].time);
    }
    int getMinNganNhat(){
        int v = 0;
        ngannhat[v].time = maxvalue;
        for (int i = 1; i < max; ++i)
            if (ngannhat[i].mark && ngannhat[i].time < ngannhat[v].time){
                v = i;
            }
        return v;
    }

    Station getStation(int id_station){
        
        return all_station.get(id_station - 1);
    }
    void insertToArray(int id_station_row, int id_station, int time, String phuongtien){
        int i = 1;
        while (i <= array_time[id_station_row][0].id_station && array_time[id_station_row][i].id_station != id_station){
            ++i;
        }
        if (i > array_time[id_station_row][0].id_station) {
            ++array_time[id_station_row][0].id_station;
            array_time[id_station_row][array_time[id_station_row][0].id_station].time = time;
            array_time[id_station_row][array_time[id_station_row][0].id_station].id_station = id_station;
            array_time[id_station_row][array_time[id_station_row][0].id_station].phuongtien = phuongtien;
        }
        else
            if (array_time[id_station_row][i].time > time){
                array_time[id_station_row][i].time = time;
                array_time[id_station_row][i].phuongtien = phuongtien;
            }
    }

    int getLocation(int idstation, JSONArray jsonArray){
        int result = 0, id_station = 0;
        do {
            try {
                id_station = jsonArray.getInt(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            result++;
        } while (id_station != idstation);
        return --result;
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
