package io.github.zekerzhayard.fpslimiter.asm;

import java.util.Map;

import io.github.zekerzhayard.fpslimiter.FpsLimiter;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

public class FMLLoadingPlugin implements IFMLLoadingPlugin {
    @Override
    public String[] getASMTransformerClass() {
        return new String[] { ClassTransformer.class.getName() };
    }

    @Override
    public String getModContainerClass() {
        return FpsLimiter.class.getName();
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
