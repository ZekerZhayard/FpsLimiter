package io.github.zekerzhayard.fpslimiter;

import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import io.github.zekerzhayard.fpslimiter.command.CommandFpsLimiter;
import io.github.zekerzhayard.fpslimiter.config.ConfigLoader;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class FpsLimiter extends DummyModContainer {
    public final static String MODID = "fpslimiter";
    public final static String NAME = "FpsLimiter";
    public final static String VERSION = "@VERSION@";
    
    public FpsLimiter() {
        super(new ModMetadata());
        ModMetadata md = this.getMetadata();
        md.modId = FpsLimiter.MODID;
        md.name = FpsLimiter.NAME;
        md.version = FpsLimiter.VERSION;
        md.authorList = Lists.newArrayList("ZekerZhayard");
        md.description = "Limit the frames rate when the Minecraft form is not active.";
        md.credits = "GPLv3";
        md.updateJSON = "https://raw.githubusercontent.com/ZekerZhayard/FpsLimiter/master/update.json";
    }
    
    @Override
    public boolean registerBus(EventBus bus, LoadController controller) {
        bus.register(this);
        return true;
    }
    
    @Subscribe
    public void preInit(FMLPreInitializationEvent event) {
        ConfigLoader.setConfig(event.getSuggestedConfigurationFile());
        ConfigLoader.loadConfig();
    }
    
    @Subscribe
    public void init(FMLInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new CommandFpsLimiter());
    }
}
