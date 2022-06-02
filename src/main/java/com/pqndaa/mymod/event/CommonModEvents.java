package com.pqndaa.mymod.event;

import com.pqndaa.mymod.init.MenuInit;
import com.pqndaa.mymod.init.thirstsystem.ThirstInfoMenu;
import com.pqndaa.mymod.init.thirstsystem.ThirstInfoScreen;
import com.pqndaa.mymod.init.world.biome.ModBiomes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class CommonModEvents {
	public static void setup() {
		IEventBus bus = MinecraftForge.EVENT_BUS;
	}
	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			ModBiomes.generateBiomes();

		});
		
	}

	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(MenuInit.THIRST_INFO, ThirstInfoScreen::new);
		});
	}

}
