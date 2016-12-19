/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlitejdbcdriverconnection;

/**
 *
 * @author anhkh
 */
public class Location {
    String provider;
    double latitude;
    double longitude;
    public Location(String provider){
        this.provider = provider;
    }
    public Location(String provider, double latitude, double longitude){
        this.provider = provider;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public void setlatitude(double latitude){
        this.latitude = latitude;
    }
    public void setlongitude(double longitude){
        this.longitude = longitude;
    }
    public String getprovider(){
        return this.provider;
    }
    public double getlatitude(){
        return this.latitude;
    }
    public double getlongitude(){
        return this.longitude;
    }
    public LatLng getLatLng(){
        return new LatLng(this.latitude, this.longitude);
    }
    
    public double distanceTo(Location location) {

    final int R = 6371; // Radius of the earth
    double lat1 = this.latitude;
    double lon1 = this.longitude;
    double lat2 = location.getlatitude();
    double lon2 = location.getlongitude();
    Double latDistance = Math.toRadians(lat2 - lat1);
    Double lonDistance = Math.toRadians(lon2 - lon1);
    Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
    Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    double distance = R * c * 1000; // convert to meters

    double height = 0;

    distance = Math.pow(distance, 2) + Math.pow(height, 2);

    return Math.sqrt(distance);
}
}
