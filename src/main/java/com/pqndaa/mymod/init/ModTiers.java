package com.pqndaa.mymod.init;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModTiers {
    public static final ForgeTier EXO = new ForgeTier(2, 1000, 6.0f,
            2.0f, 14, BlockTags.NEEDS_IRON_TOOL,
            () -> Ingredient.of(ItemInit.EXO_INGOT.get()));

    //DIAMOND(3, 1561, 8.0F, 3.0F, 10, ()
    //SwordItem(ModTiers.EXO,4 -> 6,-2.4f -> ,new Item.Properties().tab(MainMod.TAB)));
    //MIN SPEED -4.0 = 0 speed attack
}