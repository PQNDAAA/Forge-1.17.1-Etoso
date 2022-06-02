package com.pqndaa.mymod.init;

import com.pqndaa.mymod.init.thirstsystem.ThirstBindingMessage;
import org.lwjgl.glfw.GLFW;

import com.pqndaa.mymod.MainMod;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fmlclient.registry.ClientRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class KeyInit {
    public static final KeyMapping THIRST_BINDING = new KeyMapping("key.mymod.thirst_binding", GLFW.GLFW_KEY_K, "key.categories.misc");

    @SubscribeEvent
    public static void registerKeyBindings(FMLClientSetupEvent event) {
        ClientRegistry.registerKeyBinding(THIRST_BINDING);
    }

    @Mod.EventBusSubscriber({Dist.CLIENT})
    public static class KeyEventListener {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.KeyInputEvent event) {
            if (Minecraft.getInstance().screen == null) {
                if (event.getKey() == THIRST_BINDING.getKey().getValue()) {
                    if (event.getAction() == GLFW.GLFW_PRESS) {
                        MainMod.PACKET_HANDLER.sendToServer(new ThirstBindingMessage(0, 0));
                        ThirstBindingMessage.pressAction(Minecraft.getInstance().player, 0, 0);
                    }
                }
            }
        }
    }

}
