package model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GoogleMapsModel {

    private List<Result> results;


    @Getter
    @Setter
    public class Result {

        private AddressComponent addressComponents;

        private String formattedAddress;

        private Geometry geometry;

        private String placeId;
    }


    @Getter
    @Setter
    public class AddressComponent {
        private String longName;
        private String shortName;
        private String[] types;
    }

    @Getter
    @Setter
    public class Geometry {
        private Location location;
        private String locationType;
        private Viewport viewport;
    }

    @Getter
    @Setter
    public class Location {
        private Double lat;
        private Double lng;
    }

    @Getter
    @Setter
    public class Viewport {
        private Location northeast;
        private Location southwest;
    }

}
