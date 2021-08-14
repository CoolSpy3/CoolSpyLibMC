package com.coolspy3.util;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public abstract class ListCommand {

    public final String prefix;
    public final Pattern addPattern;
    public final Pattern removePattern;
    public final String listType;

    public ListCommand(String prefix, String listType) {
        this.prefix = prefix;
        addPattern = Pattern.compile(prefix + " add (.+)");
        removePattern = Pattern.compile(prefix + " remove (.+)");
        this.listType = "<" + listType + ">";
    }

    @SubscribeEvent
    public void register(ClientChatEvent event) {
        String msg = event.getMessage();
        if(msg.matches(prefix + "( .*)?")) {
            event.setCanceled(true);
            try {
                if(msg.matches(prefix + " list( .*)?")) {
                    list();
                    return;
                }
                if(msg.matches(prefix + " add( .*)?")) {
                    Matcher addMatcher = addPattern.matcher(msg);
                    if(addMatcher.matches()) {
                        String str = addMatcher.group(1);
                        if(validate(str)) {
                            add(str);
                            return;
                        }
                    }
                    ModUtil.sendMessage(TextFormatting.RED + "Usage: " + prefix + " add " + listType);
                    return;
                }
                if(msg.matches(prefix + " remove( .*)?")) {
                    Matcher removeMatcher = removePattern.matcher(msg);
                    if(removeMatcher.matches()) {
                        String str = removeMatcher.group(1);
                        if(validate(str)) {
                            remove(str);
                            return;
                        }
                    }
                    ModUtil.sendMessage(TextFormatting.RED + "Usage: " + prefix + " remove " + listType);
                    return;
                }
                ModUtil.sendMessage(TextFormatting.RED + "Usage: " + prefix + " [add|remove|list] " + listType);
            } catch(IOException e) {
                e.printStackTrace(System.err);
            }
        }
    }

    public abstract boolean validate(String str);
    public abstract void add(String str) throws IOException;
    public abstract void remove(String str) throws IOException;
    public abstract void list();

}
