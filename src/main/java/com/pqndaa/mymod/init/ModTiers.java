package com.pqndaa.mymod.init;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModTiers {

    public static final ForgeTier EXO = new ForgeTier(2, 800, 2.0f,
            2.0f, 12, BlockTags.NEEDS_IRON_TOOL,
            () -> Ingredient.of(ItemInit.EXO_INGOT.get()));

    public static final ForgeTier TRITIUM = new ForgeTier(3, 1850, 5.0f,
            2.0f, 16, BlockTags.NEEDS_IRON_TOOL,
            () -> Ingredient.of(ItemInit.TRITIUM_INGOT.get()));

    public static final ForgeTier PLAXOTIUM = new ForgeTier(3, 1500, 3.0f,
            2.0f, 14, BlockTags.NEEDS_IRON_TOOL,
            () -> Ingredient.of(ItemInit.PLAXOTIUM_INGOT.get()));

    //DIAMOND(3, 1561, 8.0F, 3.0F, 10, ()
    //SwordItem(ModTiers.EXO,4 -> 6,-2.4f -> ,new Item.Properties().tab(MainMod.TAB)));
    //MIN SPEED -4.0 = 0 speed attack
}