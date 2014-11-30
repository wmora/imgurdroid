package com.nispok.imgurdroid.events;

public class BusProvider {

    private static ImgurdroidBus BUS = new ImgurdroidBus();

    public static ImgurdroidBus bus() {
        return BUS;
    }

    private BusProvider() {
    }
}
