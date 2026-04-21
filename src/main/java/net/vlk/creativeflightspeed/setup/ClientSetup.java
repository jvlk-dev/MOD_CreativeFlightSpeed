package net.vlk.creativeflightspeed.setup;

import net.vlk.creativeflightspeed.events.ClientEventHandler;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.vlk.creativeflightspeed.CreativeFlightSpeed.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    public static void init() {
        // We can do other client-side setup here if needed
        Keybinds.register(); // Ensure keybinds are initialized
    }

    @SubscribeEvent
    public static void onRegisterKeyMappings(final RegisterKeyMappingsEvent event) {
        event.register(Keybinds.INCREASE_SPEED_KEY);
        event.register(Keybinds.DECREASE_SPEED_KEY);
        event.register(Keybinds.RESET_SPEED_KEY);

        // Also register our client-side event handler here
        // We use the Forge event bus for this, not the mod event bus
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
    }
}