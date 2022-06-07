package com.pqndaa.mymod.init.world.biome;

import com.pqndaa.mymod.MainMod;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.Features;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;
import net.minecraft.world.level.levelgen.placement.FrequencyWithExtraChanceDecoratorConfiguration;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class ModBiomes {

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, MainMod.MOD_ID);

    public static final RegistryObject<Biome> LAVA_LAND = BIOMES.register("lava_land", ModBiomes::createLavaLand);

    public static final RegistryObject<Biome> GRAVEYARD_LAND = BIOMES.register("graveyard_land", ModBiomes::createGraveyardLand);

    public static void generateBiomes(){
        addBiome(LAVA_LAND.get(),BiomeManager.BiomeType.DESERT,20,Type.HOT,Type.DEAD,Type.DRY);
        addBiome(GRAVEYARD_LAND.get(),BiomeManager.BiomeType.COOL,20,Type.SWAMP,Type.FOREST);
    }
    private static void addBiome(Biome biome, BiomeManager.BiomeType type, int weight, BiomeDictionary.Type... types)
    {
        ResourceKey<Biome> key = ResourceKey.create(ForgeRegistries.Keys.BIOMES,
                Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(biome)));

        BiomeDictionary.addTypes(key,types);
        BiomeManager.addBiome(type, new BiomeManager.BiomeEntry(key,weight));
    }

    private static Biome createLavaLand() {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnSettings);
        BiomeDefaultFeatures.monsters(spawnSettings, 90, 5, 100);

        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder();
        generationSettings.surfaceBuilder(ModSurfaceConfigs.LAVA_SURFACE_BUILDER);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(generationSettings);
        BiomeDefaultFeatures.addDefaultFlowers(generationSettings);
        BiomeDefaultFeatures.addDefaultLakes(generationSettings);
        BiomeDefaultFeatures.addDefaultCarvers(generationSettings);
        generationSettings.addFeature(GenerationStep.Decoration.LAKES, Features.LAKE_LAVA);

        BiomeDefaultFeatures.addDefaultOres(generationSettings);
        BiomeDefaultFeatures.addDefaultSoftDisks(generationSettings);
        BiomeDefaultFeatures.addDefaultSprings(generationSettings);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,  Features.ACACIA
                .decorated(Features.Decorators.HEIGHTMAP_WITH_TREE_THRESHOLD_SQUARED)
                .decorated(FeatureDecorator.COUNT_EXTRA
                        .configured(new FrequencyWithExtraChanceDecoratorConfiguration(
                                2, 0.1f, 1))));

        return (new Biome.BiomeBuilder()).precipitation(Biome.Precipitation.RAIN).biomeCategory(Biome.BiomeCategory.NONE)
                .depth(0.125F).scale(0.05F).temperature(0.8F).downfall(0.4F)
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0xe43f3f).waterFogColor(0x050533)
                        .fogColor(0xc0d8ff).skyColor(0x77adff)
                        .build()).mobSpawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build()).build();

    }

    private static Biome createGraveyardLand() {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnSettings);
        BiomeDefaultFeatures.monsters(spawnSettings, 90, 5, 100);

        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder();
        generationSettings.surfaceBuilder(ModSurfaceConfigs.GRAVEYARD_SURFACE_BUILDER);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(generationSettings);
        BiomeDefaultFeatures.addDefaultFlowers(generationSettings);
        BiomeDefaultFeatures.addDefaultLakes(generationSettings);
        BiomeDefaultFeatures.addDefaultCarvers(generationSettings);
        generationSettings.addFeature(GenerationStep.Decoration.LAKES, Features.LAKE_WATER);

        BiomeDefaultFeatures.addDefaultOres(generationSettings);
        BiomeDefaultFeatures.addDefaultSoftDisks(generationSettings);
        BiomeDefaultFeatures.addDefaultSprings(generationSettings);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,  Features.ACACIA
                .decorated(Features.Decorators.HEIGHTMAP_WITH_TREE_THRESHOLD_SQUARED)
                .decorated(FeatureDecorator.COUNT_EXTRA
                        .configured(new FrequencyWithExtraChanceDecoratorConfiguration(
                                2, 0.1f, 1))));

        return (new Biome.BiomeBuilder()).precipitation(Biome.Precipitation.RAIN).biomeCategory(Biome.BiomeCategory.SWAMP)
                .depth(0.120F).scale(0.13F).temperature(0.7F).downfall(0.9F)
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x190725).waterFogColor(0x190725)
                        .fogColor(0x282D35).skyColor(0x1A1D22)
                        .build()).mobSpawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build()).build();

    }

    public static void register(IEventBus bus){
        BIOMES.register(bus);
    }


}
