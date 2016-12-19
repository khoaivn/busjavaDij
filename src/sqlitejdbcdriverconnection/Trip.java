/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlitejdbcdriverconnection;

import org.json.JSONArray;

/**
 *
 * @author anhkh
 */
public class Trip {
    int _id;
    int _id_start;
    int _id_finish;
    int _time;
    JSONArray _list_station;

    
    public Trip(){
        JSONArray jsonArray = new JSONArray();
        this._list_station = jsonArray;
    }
    public Trip(int id, int id_start, int id_finish, int time, JSONArray list_station) {
        this._id = id;
        this._id_start = id_start;
        this._id_finish = id_finish;
        this._time = time;
        this._list_station = list_station;
    }
    public Trip( int id_start, int id_finish, int time, JSONArray list_station) {
        this._id_start = id_start;
        this._id_finish = id_finish;
        this._time = time;
        this._list_station = list_station;
    }
    

    public int getID(){ return this._id; }
    public int getIDStart(){ return this._id_start; }
    public int getIDFinish(){ return this._id_finish; }
    public int getTime(){ return  this._time; }
    public JSONArray getListStation(){ return this._list_station; }

    public void setID(int id){ this._id = id; }
    public void setIDStart(int id_start){ this._id_start = id_start; }
    public void setIDFinish(int id_finish){ this._id_finish = id_finish; }
    public void setTime(int time){ this._time = time; }
    public void setListStation(JSONArray trip){ this._list_station = trip; }
}
