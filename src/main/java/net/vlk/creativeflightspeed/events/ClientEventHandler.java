package net.vlk.creativeflightspeed.events;

import net.vlk.creativeflightspeed.setup.Keybinds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ClientEventHandler {

    private static final float SPEED_INCREMENT = 0.05f; // How much speed changes per key press
    private static final float MIN_SPEED = 0.05f;       // Minimum flight speed
    private static final float MAX_SPEED = 2.0f;        // Maximum flight speed (Vanilla is 0.05 * 2 = 0.1, we go higher)
    private static final float DEFAULT_SPEED_MULTIPLIER = 1.0f; // Multiplier for default vanilla speed

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Minecraft mc = Minecraft.getInstance();
            LocalPlayer player = mc.player;

            if (player == null || !player.isCreative()) {
                // Only apply speed changes to creative mode players
                return;
            }

            if (Keybinds.INCREASE_SPEED_KEY.consumeClick()) {
                modifyFlightSpeed(player, SPEED_INCREMENT);
            }
            if (Keybinds.DECREASE_SPEED_KEY.consumeClick()) {
                modifyFlightSpeed(player, -SPEED_INCREMENT);
            }
            if (Keybinds.RESET_SPEED_KEY.consumeClick()) {
                resetFlightSpeed(player);
            }
        }
    }

    private void modifyFlightSpeed(Player player, float change) {
        float currentSpeed = player.getAbilities().getFlyingSpeed();
        float newSpeed = currentSpeed + change;

        // Clamp speed between min and max
        newSpeed = Math.max(MIN_SPEED, Math.min(MAX_SPEED, newSpeed));

        player.getAbilities().setFlyingSpeed(newSpeed);
        player.onUpdateAbilities(); // Important to send update to client

        sendFeedback(player, "Flight Speed: " + String.format("%.2f", newSpeed * 20) + "x"); // Multiply by 20 for more intuitive display
    }

    private void resetFlightSpeed(Player player) {
        // Vanilla creative flight speed is 0.05. Our default multiplier will make it 0.05 * 1.0 = 0.05
        player.getAbilities().setFlyingSpeed(0.05f * DEFAULT_SPEED_MULTIPLIER);
        player.onUpdateAbilities();

        sendFeedback(player, "Flight Speed: Reset to Default");
    }

    private void sendFeedback(Player player, String message) {
        if (player != null) {
            player.sendSystemMessage(Component.literal("[Flight Speed Mod] " + message));
        }
    }
}