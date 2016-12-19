package sqlitejdbcdriverconnection;

import org.json.JSONArray;


public class Route {
    int _id;
    String _name;
    String _code;
    int _cost;
    String _tghd;
    String _tgdk;
    String _ts;
    String _dv;
    JSONArray _station_go;
    JSONArray _station_return;
    int _time_avg_go;
    int _time_avg_return;
    JSONArray _time_go;
    JSONArray _time_return;
//    int _id_first;
//    int _id_finish;

    public Route(){
        JSONArray jsonArray = new JSONArray();
        this._station_go = jsonArray;
        this._station_return = jsonArray;
        this._time_avg_go = 0;
        this._time_avg_return = 0;
        this._time_go = jsonArray;
        this._time_return = jsonArray;
    }
    public Route(int id, String name, String code, int cost, String tgdh, String tgdk, String ts, String dv,
                 JSONArray station_go, JSONArray station_return, int time_avg_go, int time_avg_return, JSONArray time_go, JSONArray time_return){
        this._id = id;
        this._name = name;
        this._code = code;
        this._cost = cost;
        this._tghd = tgdh;
        this._tgdk = tgdk;
        this._ts = ts;
        this._dv = dv;
        this._station_go = station_go;
        this._station_return = station_return;
        this._time_avg_go = time_avg_go;
        this._time_avg_return = time_avg_return;
        this._time_go = time_go;
        this._time_return = time_return;
    }
    public Route(String name, String code, int cost, String tgdh, String tgdk, String ts, String dv,
                 JSONArray station_go, JSONArray station_return, int time_avg_go, int time_avg_return, JSONArray time_go, JSONArray time_return){
        this._name = name;
        this._code = code;
        this._cost = cost;
        this._tghd = tgdh;
        this._tgdk = tgdk;
        this._ts = ts;
        this._dv = dv;
        this._station_go = station_go;
        this._station_return = station_return;
        this._time_avg_go = time_avg_go;
        this._time_avg_return = time_avg_return;
        this._time_go = time_go;
        this._time_return = time_return;
    }
    public Route(String name, String code, int cost, String tgdh, String tgdk, String ts, String dv){
        JSONArray jsonArray = new JSONArray();
        this._name = name;
        this._code = code;
        this._cost = cost;
        this._tghd = tgdh;
        this._tgdk = tgdk;
        this._ts = ts;
        this._dv = dv;
        this._station_go = jsonArray;
        this._station_return = jsonArray;
        this._time_avg_go = 0;
        this._time_avg_return = 0;
        this._time_go = jsonArray;
        this._time_return = jsonArray;
    }

    public int getID(){ return this._id; }
    public String getName(){ return this._name; }
    public String getCode(){ return this._code; }
    public int getCost(){ return  this._cost; }
    public String getTgdh(){ return this._tghd; }
    public String getTgdk(){ return this._tgdk; }
    public String getTs(){ return this._ts; }
    public String getDv(){ return this._dv; }
    public JSONArray getStation_go(){ return this._station_go; }
    public JSONArray getStation_return(){ return this._station_return; }
    public int getTime_avg_go(){ return  this._time_avg_go; }
    public int getTime_avg_return(){ return  this._time_avg_return; }
    public JSONArray getTime_go(){ return this._time_go; }
    public JSONArray getTime_return(){ return this._time_return; }

    public void setID(int id){ this._id = id; }
    public void setName(String name){ this._name = name; }
    public void setCode(String code){ this._code = code; }
    public void setCost(int cost){ this._cost = cost; }
    public void setTghd(String tghd){ this._tghd = tghd; }
    public void setTgdk(String tgdk){ this._tgdk = tgdk; }
    public void setTs(String ts){ this._ts = ts; }
    public void setDv(String dv){ this._dv = dv; }
    public void setStation_go(JSONArray station_go){ this._station_go = station_go; }
    public void setStation_return(JSONArray station_return){ this._station_return = station_return; }
    public void setTime_avg_go(int time_avg_go){ this._time_avg_go = time_avg_go; }
    public void setTime_avg_return(int time_avg_return){ this._time_avg_return = time_avg_return; }
    public void setTime_go(JSONArray time_go){ this._time_go = time_go; }
    public void setTime_return(JSONArray time_return){ this._time_return = time_return; }

    public void pushGo(int station){
        this._station_go.put(station);
    }
    public void pushReturn(int station){        this._station_return.put(station);    }
    public void pushTime_go(int time_run){
        this._time_go.put(time_run);
    }
    public void pushTime_return(int time_run){
        this._time_return.put(time_run);
    }


}
