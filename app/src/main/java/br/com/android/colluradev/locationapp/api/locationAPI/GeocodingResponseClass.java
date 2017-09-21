package br.com.android.colluradev.locationapp.api.locationAPI;


public class GeocodingResponseClass {

    public String status ;
    public results[] results ;
    public GeocodingResponseClass() {}


    public class results{
        public String formatted_address ;
        public geometry geometry ;
        public String[] types;
        public address_component[] address_components;
    }

    class address_component{
        public String long_name;
        public String short_name;
        public String[] types ;
    }

    class geometry{
        public bounds bounds;
        public String location_type ;
        public location location;
        public bounds viewport;
    }

    class bounds {
        public location northeast ;
        public location southwest ;
    }

    class location{
        public String lat ;
        public String lng ;
    }
}
