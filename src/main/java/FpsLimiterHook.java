import org.lwjgl.opengl.Display;

import io.github.zekerzhayard.fpslimiter.config.ConfigLoader;

public class FpsLimiterHook {
    public static int getInactiveLimitFramerate(int fps) {
        return Display.isActive() ? fps : ConfigLoader.getFpslimiteramount();
    }
}
