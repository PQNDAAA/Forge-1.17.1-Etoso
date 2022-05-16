package com.pqndaa.mymod.event;

import com.pqndaa.mymod.init.customitems.DowsingItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class CommonModEvents {
	
	

	public static void setup() {
		IEventBus bus = MinecraftForge.EVENT_BUS;
		bus.addListener(DowsingItems::pickupItem);
		bus.addListener(DowsingItems::rightclickItem);
	}
	
	public static void init(FMLCommonSetupEvent event) {

		
	}

}
