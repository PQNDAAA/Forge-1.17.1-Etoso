package com.pqndaa.mymod.init;

import com.pqndaa.mymod.MainMod;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public class DimensionInit {
	

	public static final ResourceKey<Level> MYSTERIOUS_DIMENSION = ResourceKey.create(Registry.DIMENSION_REGISTRY,
			new ResourceLocation(MainMod.MOD_ID, "mysterious"));

	
}
