package sqlitejdbcdriverconnection;

//import android.content.Context;
//import android.location.Location;
//import android.support.annotation.Nullable;
//import android.text.style.CharacterStyle;
//
//import com.google.android.gms.location.places.AutocompletePrediction;
//import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by TruDu on 9/17/16.
 */
public class Chung {

    static String placeID_ha_noi = "ChIJoRyG2ZurNTERqRfKcnt_iOc";
    static float vantocdibo = 5000/3600;

//    static ArrayList<AutocompletePrediction> getList(DataBaseHelper my_data_base, Context context){
//        ArrayList<AutocompletePrediction> result = new ArrayList<AutocompletePrediction>();
//        List<Route> routeList = my_data_base.getAllRoute();
//        for (Route route : routeList){
//            String code_route = route.getCode();
//            code_route = code_route.toUpperCase();
//            code_route = "Tuyến " + code_route;
//            AutocompletePrediction autocompletePrediction = createAutocompletePrediction(code_route,
//                    code_route, route.getName());
//            result.add(autocompletePrediction);
//        }
//        return result;
//    }
//
//    static AutocompletePrediction createAutocompletePrediction(final String fullText, final String primaryText, final String secondaryText){
//        AutocompletePrediction autocompletePrediction = new AutocompletePrediction() {
//            @Override
//            public CharSequence getFullText(@Nullable CharacterStyle characterStyle) {
//                return fullText;
//            }
//
//            @Override
//            public CharSequence getPrimaryText(@Nullable CharacterStyle characterStyle) {
//                return primaryText;
//            }
//
//            @Override
//            public CharSequence getSecondaryText(@Nullable CharacterStyle characterStyle) {
//                return secondaryText;
//            }
//
//            @Nullable
//            @Override
//            public String getPlaceId() {
//                return placeID_ha_noi;
//            }
//
//            @Nullable
//            @Override
//            public List<Integer> getPlaceTypes() {
//                return null;
//            }
//
//            @Override
//            public AutocompletePrediction freeze() {
//                return null;
//            }
//
//            @Override
//            public boolean isDataValid() {
//                return false;
//            }
//        };
//        return autocompletePrediction;
//    }
    static String[] laber = {
    "01",
    "02",
    "03",
    "03b",
    "04",
    "05",
    "06a",
    "06b",
    "06c",
    "06d",
    "06e",
    "07",
    "08",
    "09",
    "10a",
    "10b",
    "11",
    "12",
    "13",
    "14",
    "15",
    "16a",
    "16b",
    "17",
    "18",
    "19",
    "20a",
    "20b",
    "20c",
    "21a",
    "21b",
    "22",
    "23",
    "24",
    "25",
    "26",
    "27",
    "28",
    "29",
    "30",
    "31",
    "32",
    "33",
    "34",
    "35a",
    "35b",
    "36",
    "37",
    "38",
    "39",
    "40a",
    "40b",
    "41",
    "42",
    "43",
    "44",
    "45",
    "46",
    "47a",
    "47b",
    "48",
    "49",
    "50",
    "51",
    "52a",
    "52b",
    "53a",
    "53b",
    "54",
    "55",
    "56a",
    "56b",
    "56c",
    "57",
    "58",
    "59",
    "60a",
    "60b",
    "61",
    "62",
    "63",
    "64",
    "65",
    "6b",
    "70a",
    "70b",
    "71",
    "71b",
    "72",
    "73",
    "74",
    "75",
    "76",
    "77",
    "78",
    "79",
    "84",
    "85",
    "86",
    "87",
    "88",
    };
    static String Conver_name_to_code(String name){
            name = name.split("\\W+")[1];
            name = name.toLowerCase();
        return name;
    }
    static String[] Add_name(String[] list_name, DataBaseHelper data){
        int length = list_name.length;
        for (int i = 0; i < length; ++i){
            String string = list_name[i];
            String code = Conver_name_to_code(string);
            Route route = data.getRoutebyCode(code);
            code = code.toUpperCase();
            string = code + ":" + route.getName();
            list_name[i] = string;
        }
        return list_name;
    }
    static List<String> get_list(List<Station> stations) {
        List<String> list = new ArrayList<String>();
        for (Station station : stations) {
            list.add(station.getLocation().getprovider());
        }
        return list;
    }
    static String ghepCodeRoute(String string, JSONArray jsonArray){
        int length = jsonArray.length();
        for (int i = 0; i < length; i++){
            try {
                String thu = jsonArray.getString(i);
                if  (string == "") string += thu;
                else  string += ", " + thu;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return string;
    }
    ArrayList<Integer> get_list_dau_toi_i(JSONArray jsonArray, int giatri){
        ArrayList<Integer> list_ketqua = new ArrayList<>();
        int i = 0, station = 0;
        do {
            try {
                station = jsonArray.getInt(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            list_ketqua.add(station);
            i++;
        }while (station != giatri);
        return list_ketqua;
    }
    ArrayList<Integer> get_list_i_toi_het(JSONArray jsonArray, int giatri){
        ArrayList<Integer> list_ketqua = new ArrayList<>();
        int i = jsonArray.length() - 1, station = 0, length = jsonArray.length();
        do {
            try {
                station = jsonArray.getInt(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            list_ketqua.add(station);
            i--;
        }while (station != giatri);
        return list_ketqua;
    }
    public static double khoangCach(LatLng latLng1, LatLng latLng2) {

        final int R = 6371; // Radius of the earth
//        double lat1 = this.Latitude;
//        double lon1 = this.Longitude;
//        double lat2 = location.getLatitude();
//        double lon2 = location.getLongitude();
        Double latDistance = Math.toRadians(latLng2.latitude - latLng1.latitude);
        Double lonDistance = Math.toRadians(latLng2.longitude - latLng1.longitude);
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(latLng1.latitude)) * Math.cos(Math.toRadians(latLng2.latitude))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = 0;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }
    static int tinhthoigiandibo (LatLng latLng1, LatLng latLng2){
        double khoang = khoangCach(latLng1, latLng2);
        return (int) (khoang/vantocdibo);
    }

    static LatLng getLatLngfromLocation(Location location){
        return new LatLng(location.getlatitude(), location.getlongitude());
    }
}
