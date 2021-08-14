package com.coolspy3.util;

import java.util.HashMap;
import java.util.Map.Entry;

import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class HelpCommand {

    public static final String justABunchOfDashes = "-----------------------------";
    public final String trigger;
    protected final HashMap<String, String> helpInfo;

    public HelpCommand(String trigger) {
        this.trigger = trigger;
        helpInfo = new HashMap<>();
    }

    @SubscribeEvent
    public void register(ClientChatEvent event) {
        if(event.getMessage().matches(trigger + "( .*)?")) {
            event.setCanceled(true);
            ModUtil.sendMessage(TextFormatting.BLUE + justABunchOfDashes);
            for(Entry<String, String> command: helpInfo.entrySet()) {
                ModUtil.sendMessage(TextFormatting.YELLOW + command.getKey() + TextFormatting.AQUA + " - " + command.getValue());
            }
            ModUtil.sendMessage(TextFormatting.BLUE + justABunchOfDashes);
        }
    }

    protected void addCommand(String command, String description) {
        helpInfo.put(command, description);
    }

}
