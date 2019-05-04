package io.github.zekerzhayard.fpslimiter.command;

import io.github.zekerzhayard.fpslimiter.config.ConfigLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class CommandFpsLimiter extends CommandBase {
    @Override
    public boolean canCommandSenderUse(ICommandSender sender) {
        return true;
    }
    
    @Override
    public String getName() {
        return "fpslimiter";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/fpslimiter <5 - " + Integer.MAX_VALUE + ">";
    }

    @Override
    public void execute(ICommandSender sender, String[] args) throws CommandException {
        try {
            ConfigLoader.setFpslimiteramount(Integer.valueOf(args[0]));
            ConfigLoader.saveConfig();
        } catch (Exception e) {
            e.printStackTrace();
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(this.getCommandUsage(sender)));
        }
    }
}
