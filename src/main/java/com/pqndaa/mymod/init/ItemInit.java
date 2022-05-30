package com.pqndaa.mymod.init;

import com.google.common.base.Supplier;
import com.pqndaa.mymod.MainMod;
import com.pqndaa.mymod.init.customitems.Compass;
import com.pqndaa.mymod.init.customitems.Water_Bottle;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MainMod.MOD_ID);

	public static final RegistryObject<Item> EXO_INGOT = register("exo_ingot",
			() -> new Item(new Item.Properties().tab(MainMod.TAB)));

	public static final RegistryObject<Item> TRITIUM_INGOT = register("tritium_ingot",
			() -> new Item(new Item.Properties().tab(MainMod.TAB)));

	public static final RegistryObject<Item> PLAXOTIUM_INGOT = register("plaxotium_ingot",
			() -> new Item(new Item.Properties().tab(MainMod.TAB)));
	public static final RegistryObject<Item> EXAMPLE_ITEM = register("example_item",
			() -> new Item(new Item.Properties().tab(MainMod.TAB)));

	public static final RegistryObject<Item> ROPE = register("rope",
			() -> new Item(new Item.Properties().tab(MainMod.TAB)));
	
	public static final RegistryObject<SwordItem> EXO_SWORD = ITEMS.register("exo_sword", 
			() -> new SwordItem(ModTiers.EXO,4,-2.3f,new Item.Properties().tab(MainMod.TAB)));

	public static final RegistryObject<PickaxeItem> EXO_PICKAXE = ITEMS.register("exo_pickaxe",
			() -> new PickaxeItem(ModTiers.EXO,2,-2.8f,new Item.Properties().tab(MainMod.TAB).durability(1500)));

	public static final RegistryObject<AxeItem> EXO_AXE = ITEMS.register("exo_axe",
			() -> new AxeItem(ModTiers.EXO,6,-3.1f,new Item.Properties().tab(MainMod.TAB)));

	public static final RegistryObject<Item> WATER_BOTTLE = ITEMS.register("water_bottle", 
			() -> new Water_Bottle(new Item.Properties().tab(MainMod.TAB).stacksTo(1),0,6));

	public static final RegistryObject<Item> BACKPACK = ITEMS.register("backpack",
			() -> new Item(new Item.Properties().tab(MainMod.TAB).stacksTo(1)));

	public static final RegistryObject<Item> COMPASS = ITEMS.register("compass",
			() -> new Compass(new Item.Properties().tab(MainMod.TAB).stacksTo(1)));
   
	
	private static <T extends Item> RegistryObject<T> register(final String name, final Supplier<T> item) {
		return ITEMS.register(name, item);
	}

}
