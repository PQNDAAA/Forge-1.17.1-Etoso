package com.pqndaa.mymod.init.world.biome;

import com.pqndaa.mymod.MainMod;
import com.pqndaa.mymod.init.BlockInit;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderBaseConfiguration;

public class ModSurfaceConfigs {

    public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> LAVA_SURFACE_BUILDER =
            register("lava_surface", SurfaceBuilder.DEFAULT.configured(new SurfaceBuilderBaseConfiguration(
                    BlockInit.BURN_GRASS.get().defaultBlockState(),
                    Blocks.DIRT.defaultBlockState(),
                    Blocks.SAND.defaultBlockState())));


    private static <T extends  SurfaceBuilderBaseConfiguration> ConfiguredSurfaceBuilder<T> register(String name,
                                                                                                     ConfiguredSurfaceBuilder<T>surfaceBuilder) {
        return Registry.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER, new ResourceLocation(MainMod.MOD_ID, name), surfaceBuilder);

    }

}
