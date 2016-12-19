package sqlitejdbcdriverconnection;

//import android.app.job.JobInfo;
//import android.content.Context;
//import android.location.Location;

import org.json.JSONArray;

/**
 * Created by khoai on 19/07/2016.
 */
public class Station {
    int _id;
    Location _location;
    JSONArray _route_go;
    JSONArray _route_return;
    JSONArray _first;
    JSONArray _finish;
    public Station(){
        JSONArray jsonArray = new JSONArray();
        this._route_go = jsonArray;
        this._route_return = jsonArray;
        this._first = jsonArray;
        this._finish = jsonArray;
    }
    public Station(int id, Location location){
        JSONArray jsonArray = new JSONArray();
        this._route_go = jsonArray;
        this._route_return = jsonArray;
        this._first = jsonArray;
        this._finish = jsonArray;
        this._location = location;
        this._id = id;
    }
    public Station(Location location, JSONArray route_go, JSONArray route_return){
        this._location = location;
        this._route_go = route_go;
        this._route_return = route_return;
    }
    public Station(int id, Location location, JSONArray route_go, JSONArray route_return){
        this._id = id;
        this._location = location;
        this._route_go = route_go;
        this._route_return = route_return;
    }

    public int getId(){
        return this._id;
    }
    public Location getLocation(){
        return this._location;
    }
    public JSONArray getRoute_go(){
        return this._route_go;
    }
    public JSONArray getRoute_return(){ return this._route_return; }
    public JSONArray getFirst(){ return this._first; }
    public JSONArray getFinish(){ return this._finish; }

    public void setId(int id){
        this._id = id;
    }
    public void setLocation(Location location){
        this._location = location;
    }
    public void setRoute_go(JSONArray route_go){
        this._route_go = route_go;
    }
    public void setRoute_return(JSONArray route_return){ this._route_return = route_return; }
    public void setFirst(JSONArray first){ this._first = first; }
    public void setFinish(JSONArray finish){ this._finish = finish; }
    public String getListRoute(){
        String string = "";
        string = Chung.ghepCodeRoute(string, this._first);
        string = Chung.ghepCodeRoute(string, this._route_go);
        string = Chung.ghepCodeRoute(string, this._finish);
        string = Chung.ghepCodeRoute(string, this._route_return);

//        int length = this._route_go.length();
//        for (int i = 0; i < length; i++){
//            try {
//                String thu = this._route_go.getString(i);
//                if  (i != length - 1)
//                    string += thu + ", ";
//                else
//                    string += thu;
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//        length = this._route_return.length();
//        for (int i = 0; i < length; i++){
//            try {
//                String thu = this._route_return.getString(i);
//                if  (string == "") string += thu;
//                else  string += ", " + thu;
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//        length = this._first.length();
//        for (int i = 0; i < length; i++){
//            try {
//                String thu = this._first.getString(i);
//                if  (string == "") string += thu;
//                else  string += ", " + thu;
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//        length = this._finish.length();
//        for (int i = 0; i < length; i++){
//            try {
//                String thu = this._finish.getString(i);
//                if  (string == "") string += thu;
//                else  string += ", " + thu;
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
        string = string.toUpperCase();
        return string;
    }
}
