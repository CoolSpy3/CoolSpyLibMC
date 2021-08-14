package com.coolspy3.coolspylib;

import com.coolspy3.util.ServerJoinEvent;

import net.minecraftforge.client.event.ClientPlayerNetworkEvent.LoggedInEvent;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent.LoggedOutEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod("coolspylib")
public class CoolSpyLib {

    private boolean isLoggedIn = false;
    
    public CoolSpyLib() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onServerJoined(LoggedInEvent event) {
        if(!isLoggedIn) {
            MinecraftForge.EVENT_BUS.post(new ServerJoinEvent(event));
            isLoggedIn = true;
        }
    }

    @SubscribeEvent
    public void onServerLeft(LoggedOutEvent event) {
        isLoggedIn = false;
    }
}
