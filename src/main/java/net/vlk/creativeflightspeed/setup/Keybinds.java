package net.vlk.creativeflightspeed.setup;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class Keybinds {

    public static final KeyMapping INCREASE_SPEED_KEY = new KeyMapping(
            "key.flightspeedmod.increase_speed", // Translation key for the keybind name
            KeyConflictContext.IN_GAME,          // When the keybind can be used (only in-game)
            InputConstants.Type.KEYSYM,          // Type of input (keyboard key)
            GLFW.GLFW_KEY_O,                     // Default key: O
            "category.flightspeedmod.flight_speed" // Translation key for the keybind category
    );

    public static final KeyMapping DECREASE_SPEED_KEY = new KeyMapping(
            "key.flightspeedmod.decrease_speed",
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_I,                     // Default key: I
            "category.flightspeedmod.flight_speed"
    );

    public static final KeyMapping RESET_SPEED_KEY = new KeyMapping(
            "key.flightspeedmod.reset_speed",
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_U,                     // Default key: U
            "category.flightspeedmod.flight_speed"
    );

    public static void register() {
        // Keymappings are registered automatically by Forge when they are instantiated if they are static final
        // This method is just a placeholder to ensure the class is loaded and the keybinds are created.
    }
}