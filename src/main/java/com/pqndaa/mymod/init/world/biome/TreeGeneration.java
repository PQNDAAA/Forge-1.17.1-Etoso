package com.pqndaa.mymod.init.world.biome;

import com.pqndaa.mymod.init.world.ModConfiguredFeatures;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.Features;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;
import net.minecraft.world.level.levelgen.placement.FrequencyWithExtraChanceDecoratorConfiguration;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.Set;
import java.util.List;
import java.util.function.Supplier;

public class TreeGeneration {

    public static void generateTrees(final BiomeLoadingEvent event)
    {
        ResourceKey<Biome> key = ResourceKey.create(Registry.BIOME_REGISTRY, event.getName());
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);

        if(types.contains(BiomeDictionary.Type.DEAD))
        {
            List<Supplier<ConfiguredFeature<?, ?>>> base =
                    event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION);

            base.add(() -> ModConfiguredFeatures.REDWOOD
                    .decorated(Features.Decorators.HEIGHTMAP_WITH_TREE_THRESHOLD_SQUARED)
                    .decorated(FeatureDecorator.COUNT_EXTRA
                            .configured(new FrequencyWithExtraChanceDecoratorConfiguration(
                                    1, 0.1f, 1))));
        }
        if(types.contains(BiomeDictionary.Type.SWAMP))
        {
            List<Supplier<ConfiguredFeature<?, ?>>> base =
                    event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION);

            base.add(() -> ModConfiguredFeatures.GRAVEYARDWOOD
                    .decorated(Features.Decorators.HEIGHTMAP_WITH_TREE_THRESHOLD_SQUARED)
                    .decorated(FeatureDecorator.COUNT_EXTRA
                            .configured(new FrequencyWithExtraChanceDecoratorConfiguration(
                                    1, 0.3f, 1))));
        }
    }
    }
