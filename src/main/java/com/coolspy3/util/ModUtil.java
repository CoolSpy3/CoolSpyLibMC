package com.coolspy3.util;

import com.mojang.brigadier.LiteralMessage;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Util;
import net.minecraft.util.text.TextComponentUtils;

public class ModUtil {

    public static void executeAsync(Runnable function) {
        Thread thread = new Thread(function);
        thread.setDaemon(true);
        thread.start();
    }

    public static void sendMessage(String msg) {
        Minecraft.getInstance().player.sendMessage(TextComponentUtils.fromMessage(new LiteralMessage(msg)), Util.NIL_UUID);
    }
    
}
