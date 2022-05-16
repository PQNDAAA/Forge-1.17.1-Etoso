package com.pqndaa.mymod.event;

import com.pqndaa.mymod.init.customitems.DowsingItems;
import com.pqndaa.mymod.init.thirstsystem.MenuUI;
import com.pqndaa.mymod.init.thirstsystem.UpdateThirst;
import com.pqndaa.mymod.world.OreGeneration;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class CommonModEvents {
	
	

	public static void setup() {
		IEventBus bus = MinecraftForge.EVENT_BUS;
		bus.addListener(DowsingItems::pickupItem);
		bus.addListener(DowsingItems::rightclickItem);
		bus.addListener(MenuUI::GUITest);
		bus.addListener(UpdateThirst::Tick);
	}
	
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {

		});
		
	}
}
