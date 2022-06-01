package com.pqndaa.mymod.init;

import com.google.common.base.Supplier;
import com.pqndaa.mymod.MainMod;
import com.pqndaa.mymod.init.customitems.Compass;
import com.pqndaa.mymod.init.customitems.Water_Bottle;
import com.pqndaa.mymod.init.customitems.Water_Bottle_Full;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.data.Main;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.Level;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MainMod.MOD_ID);
	public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, MainMod.MOD_ID);

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
			() -> new SwordItem(ModTiers.EXO,3,-2.3f,new Item.Properties().tab(MainMod.TAB)));

	public static final RegistryObject<PickaxeItem> EXO_PICKAXE = ITEMS.register("exo_pickaxe",
			() -> new PickaxeItem(ModTiers.EXO,1,-2.8f,new Item.Properties().tab(MainMod.TAB).durability(1500)));

	public static final RegistryObject<AxeItem> EXO_AXE = ITEMS.register("exo_axe",
			() -> new AxeItem(ModTiers.EXO,5,-3.1f,new Item.Properties().tab(MainMod.TAB)));

	public static final RegistryObject<SwordItem> TRITIUM_SWORD = ITEMS.register("tritium_sword",
			() -> new SwordItem(ModTiers.TRITIUM,7,-2.2f,new Item.Properties().tab(MainMod.TAB)));

	public static final RegistryObject<PickaxeItem> TRITIUM_PICKAXE = ITEMS.register("tritium_pickaxe",
			() -> new PickaxeItem(ModTiers.TRITIUM,4,-2.8f,new Item.Properties().tab(MainMod.TAB).durability(1500)));

	public static final RegistryObject<AxeItem> TRITIUM_AXE = ITEMS.register("tritium_axe",
			() -> new AxeItem(ModTiers.TRITIUM,7,-2.8f,new Item.Properties().tab(MainMod.TAB)));

	public static final RegistryObject<SwordItem> PLAXOTIUM_SWORD = ITEMS.register("plaxotium_sword",
			() -> new SwordItem(ModTiers.PLAXOTIUM,5,-2.4f,new Item.Properties().tab(MainMod.TAB)));

	public static final RegistryObject<PickaxeItem> PLAXOTIUM_PICKAXE = ITEMS.register("plaxotium_pickaxe",
			() -> new PickaxeItem(ModTiers.PLAXOTIUM,3,-2.8f,new Item.Properties().tab(MainMod.TAB).durability(1500)));

	public static final RegistryObject<AxeItem> PLAXOTIUM_AXE = ITEMS.register("plaxotium_axe",
			() -> new AxeItem(ModTiers.PLAXOTIUM,7,-3.0f,new Item.Properties().tab(MainMod.TAB)));
	public static final RegistryObject<Item> WATER_BOTTLE = ITEMS.register("water_bottle", 
			() -> new Water_Bottle(new Item.Properties().tab(MainMod.TAB).stacksTo(1),0,7));
	public static final RegistryObject<Item> WATER_BOTTLE_FULL = ITEMS.register("water_bottle_full",
			() -> new Water_Bottle_Full(new Item.Properties().tab(MainMod.TAB).stacksTo(1)));

	public static final RegistryObject<Item> BACKPACK = ITEMS.register("backpack",
			() -> new Item(new Item.Properties().tab(MainMod.TAB).stacksTo(1)));

	public static final RegistryObject<Item> COMPASS = ITEMS.register("compass",
			() -> new Compass(new Item.Properties().tab(MainMod.TAB).stacksTo(1)));
   
	
	private static <T extends Item> RegistryObject<T> register(final String name, final Supplier<T> item) {
		return ITEMS.register(name, item);
	}

}
