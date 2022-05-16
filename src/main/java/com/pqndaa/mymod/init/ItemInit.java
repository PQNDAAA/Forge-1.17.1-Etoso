package com.pqndaa.mymod.init;

import com.google.common.base.Supplier;
import com.pqndaa.mymod.MainMod;
import com.pqndaa.mymod.init.customitems.DowsingItems;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {//dzadaz

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MainMod.MOD_ID);

	public static final RegistryObject<Item> EXO_INGOT = register("exo_ingot",
			() -> new Item(new Item.Properties().tab(MainMod.TAB)));

	public static final RegistryObject<Item> PLAXOTIUM_INGOT = register("plaxotium_ingot",
			() -> new Item(new Item.Properties().tab(MainMod.TAB)));

	public static final RegistryObject<Item> EXAMPLE_ITEM = register("example_item",
			() -> new Item(new Item.Properties().tab(MainMod.TAB)));
	
	public static final RegistryObject<SwordItem> EXO_SWORD = ITEMS.register("exo_sword", 
			() -> new SwordItem(ModTiers.EXO,20,5f,new Item.Properties().tab(MainMod.TAB)));

	public static final RegistryObject<Item> WATER_BOTTLE = ITEMS.register("water_bottle", 
			() -> new DowsingItems(new Item.Properties().tab(MainMod.TAB)));
   
	
	private static <T extends Item> RegistryObject<T> register(final String name, final Supplier<T> item) {
		return ITEMS.register(name, item);
	}

}
