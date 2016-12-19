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
    public class LatLng {
    double latitude;
    double longitude;
        public LatLng(){
        
        }
        public LatLng(double latitude1, double longitude1){
            this.latitude = latitude1;
            this.longitude = longitude1;
        }
        public void setlatitude(double latitude){
            this.latitude = latitude;
        }
        public void setlongitude(double longitude){
            this.longitude = longitude;
        }

        public double getlatitude(){
            return this.latitude;
        }
        public double getlongitude(){
            return this.longitude;
        }
    }

