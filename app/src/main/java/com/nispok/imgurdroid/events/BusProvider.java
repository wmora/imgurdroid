package com.nispok.imgurdroid.events;

import com.halfbit.tinybus.TinyBus;

public class BusProvider {

    private static TinyBus BUS = new TinyBus();

    public static TinyBus bus() {
        return BUS;
    }

    private BusProvider() {
    }
}
