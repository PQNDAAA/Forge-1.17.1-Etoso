package com.pqndaa.mymod.init.world;


import com.pqndaa.mymod.MainMod;
import com.pqndaa.mymod.init.world.biome.TreeGeneration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MainMod.MOD_ID)
public class WorldGenerationEvents {

    @SubscribeEvent
    public static void ModWorldGeneration(BiomeLoadingEvent event){

        TreeGeneration.generateTrees(event);
        OreGeneration.generateOres(event);
    }

}
