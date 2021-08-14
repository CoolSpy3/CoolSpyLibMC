package com.coolspy3.util;

import net.minecraftforge.client.event.ClientPlayerNetworkEvent.LoggedInEvent;
import net.minecraftforge.eventbus.api.Event;

public class ServerJoinEvent extends Event {

    public final LoggedInEvent event;

    public ServerJoinEvent(LoggedInEvent event) {
        this.event = event;
    }

}