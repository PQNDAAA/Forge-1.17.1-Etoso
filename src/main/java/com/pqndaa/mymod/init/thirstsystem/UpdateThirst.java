package com.pqndaa.mymod.init.thirstsystem;

import net.minecraft.world.food.FoodData;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;

public class UpdateThirst {
	
	private static ThirstData thirstdata = new ThirstData();

	public static ThirstData getThirstData() {
		return thirstdata;
	}


	public static void Tick(PlayerTickEvent event) {
		 getThirstData().tick(event.player);

		 if(event.player.isSprinting()) {
			 getThirstData().addExhaustion(1.001F);
			 System.out.println(getThirstData().getExhaustionLevel());
		 }
	}

}
