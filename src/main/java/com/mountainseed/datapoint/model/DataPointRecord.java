package com.mountainseed.datapoint.model;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class DataPointRecord {
    private static List<String> propertyTypes = new ArrayList<>();
    static {
        propertyTypes.add("Condominium");
        propertyTypes.add("Single Family");
        propertyTypes.add("Apartment");
        propertyTypes.add("Co-op");
        propertyTypes.add("Townhome");
        propertyTypes.add("Bungalow");
        propertyTypes.add("Ranch-Style");
    }

    private static List<String> propertyAddresses = new ArrayList<>();
    static {
        propertyAddresses.add("5813 Baseline Road, Little Rock, AR");
        propertyAddresses.add("8701 Interstate 30, Little Rock, AR");
        propertyAddresses.add("7510 & 7515 Geyer Springs Road, Little Rock, AR");
        propertyAddresses.add("5300 Baseline Road, Little Rock, AR");
        propertyAddresses.add("5701 Dreher Lane, Little Rock, AR");
        propertyAddresses.add("6510 Mabelvale Cut Off Road, Little Rock, Arkansas");
        propertyAddresses.add("27 Sand Flea Drive, Inlet Beach, FL 32461");
        propertyAddresses.add("470 Beach Bike Way, Inlet Beach, FL 32461");
        propertyAddresses.add("405 Beach Bike Way, Inlet Beach, FL 32461");
        propertyAddresses.add("100 Beach Bike Way, Inlet Beach, FL 32461");
        propertyAddresses.add("46 Blue Dolphin Loop, Inlet Beach, FL 32461");
        propertyAddresses.add("5100 Peachtree Parkway, Norcross, Ga 30092");
        propertyAddresses.add("5429 South Commerce Drive, Salt Lake City, Utah 84107");
        propertyAddresses.add("	1401 East Susquehanna Avenue, Philadelphia,Pennsylvania 19125");
        propertyAddresses.add("2358 WATERLOO RD, Stockton, California 95205");
        propertyAddresses.add("Multiple Parcels, Covington, Georgia 30014");
        propertyAddresses.add("2430 Leann Cir, Chattanooga, Tennessee 37406");
        propertyAddresses.add("665-669 High Street, Holyoke, Massachusetts 01040");
        propertyAddresses.add("1300 Upper Hembree Road, Roswell, Georgia 30076");
        propertyAddresses.add("402/40 Walnut St, Lynchburg, Virginia 24504");
        propertyAddresses.add("1222 DE LA TORRE ST, Salinas, California 93905");
        propertyAddresses.add("708 W F ST, Oakdale, California 95361");
        propertyAddresses.add("963 Ocean Avenue, Lakewood, New Jersey 08701");
        propertyAddresses.add("531 Jonestown Road, Jonestown, Pennsylvania 17038");
        propertyAddresses.add("109 Station Place Way, Hurricane, West Virginia 25526");
        propertyAddresses.add("407 MAJESTIC HEIGHTS, Ringgold, Georgia 30736");
        propertyAddresses.add("168 Larrabee Way, Royersford, Pennsylvania 19468");
        propertyAddresses.add("423 BEAUMONT AVE. Charlotte, North Carolina 28204");
        propertyAddresses.add("800 W Broad St unit 102, Falls Church, Virginia 22046");
        propertyAddresses.add("Highway 24 Tract 4 of Grand Overlook Farms, Seneca South Carolina, 29678");
        propertyAddresses.add("Ruben M. Torres Sr. Blvd. & Coulter Rd. Brownsville, Texas 78520");
        propertyAddresses.add("1546 Shenandoah Dr, Zeeland, Michigan 49464");
        propertyAddresses.add("1250 S. Koeller St., Oshkosh, Wisconsin 54902");
        propertyAddresses.add("614 E 5th Street, Tifton, Georgia 31794");
    }

    public static List<String> getPropertyTypes() {
        return propertyTypes;
    }

    public static List<String> getPropertyAddresses() {
        return propertyAddresses;
    }

    public static String getRandomPropertyType() {
        return propertyTypes.get(ThreadLocalRandom.current().nextInt(0, propertyTypes.size() - 1));
    }

    public static String getRandomPropertyAddress() {
        return propertyAddresses.get(ThreadLocalRandom.current().nextInt(0, propertyAddresses.size() - 1));
    }

    public static Date getRandomReportDate() {
        GregorianCalendar gc = new GregorianCalendar();

        int year = ThreadLocalRandom.current().nextInt(
                Calendar.getInstance().get(Calendar.YEAR) - 15,
                Calendar.getInstance().get(Calendar.YEAR)
        );

        gc.set(Calendar.YEAR, year);

        int dayOfYear = ThreadLocalRandom.current().nextInt(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));

        gc.set(Calendar.DAY_OF_YEAR, dayOfYear);
        return gc.getTime();
    }
}
