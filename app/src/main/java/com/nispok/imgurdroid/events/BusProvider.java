package com.nispok.imgurdroid.events;

import com.nispok.imgurdroid.services.Imgur;

import java.util.HashMap;
import java.util.Map;

public class BusProvider {

    public static String ERROR_BUS_KEY = "error";

    private static Map<String, ImgurdroidBus> buses = new HashMap<>();

    static {
        for (String section : Imgur.Section.getSections()) {
            buses.put(section, new ImgurdroidBus());
        }
        buses.put(ERROR_BUS_KEY, new ImgurdroidBus());
    }

    public static ImgurdroidBus bus(String key) {
        return buses.get(key);
    }

    private BusProvider() {
    }
}
