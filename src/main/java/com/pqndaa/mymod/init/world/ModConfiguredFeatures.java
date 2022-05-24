package com.pqndaa.mymod.init.world;

import com.pqndaa.mymod.init.BlockInit;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

public class ModConfiguredFeatures {

    public static final ConfiguredFeature<?, ?> REDWOOD =
            register("ebony", Feature.TREE.configured(
                    new TreeConfiguration.TreeConfigurationBuilder(
                            new SimpleStateProvider(Blocks.ACACIA_LOG.defaultBlockState()),
                            new StraightTrunkPlacer(5, 4, 3),
                            new SimpleStateProvider(BlockInit.TRITIUM_BLOCK.get().defaultBlockState()),
                            new SimpleStateProvider(BlockInit.PLAXOTIUM_BLOCK.get().defaultBlockState()),
                            new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                            new TwoLayersFeatureSize(1, 0, 2)).build()));

    public static final ConfiguredFeature<?, ?> GREENWOOD =
            register("ebony", Feature.TREE.configured(
                    new TreeConfiguration.TreeConfigurationBuilder(
                            new SimpleStateProvider(Blocks.ACACIA_LOG.defaultBlockState()),
                            new StraightTrunkPlacer(5, 5, 3),
                            new SimpleStateProvider(Blocks.OAK_LEAVES.defaultBlockState()),
                            new SimpleStateProvider(BlockInit.PLAXOTIUM_BLOCK.get().defaultBlockState()),
                            new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), 4),
                            new TwoLayersFeatureSize(1, 0, 2)).build()));


    private static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(String key,
                                                                                       ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, key, configuredFeature);
    }

//https://gist.github.com/Kaupenjoe/53e1bcce0a423dea41945b501c0db571
}
