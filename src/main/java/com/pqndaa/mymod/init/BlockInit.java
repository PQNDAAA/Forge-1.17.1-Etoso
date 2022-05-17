package com.pqndaa.mymod.init;

import com.google.common.base.Supplier;
import com.pqndaa.mymod.MainMod;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Function;

public class BlockInit {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
			MainMod.MOD_ID);

	public static final DeferredRegister<Item> ITEMS = ItemInit.ITEMS;

	public static final RegistryObject<Block> EXO_ORE = register("exo_ore",
			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3.0f)),
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(MainMod.TAB)));
	
	public static final RegistryObject<Block> PLAXOTIUM_ORE = register("plaxotium_ore",
			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3.0f)),
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(MainMod.TAB)));
	
	public static final RegistryObject<Block> PLAXOTIUM_BLOCK = register("plaxotium_block",
			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3.0f)),
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(MainMod.TAB)));

	public static final RegistryObject<Block> EXO_BLOCK = register("exo_block",
			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3.0f)),
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(MainMod.TAB)));

	public static final RegistryObject<Block> PORTAL_BLOCK = register("portal", PortalBlock::new,
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(MainMod.TAB)));

	public static final RegistryObject<Block> BURN_GRASS = register("burn_grass",
			() -> new Block(BlockBehaviour.Properties.of(Material.GRASS).sound(SoundType.GRASS).strength(2.0F,8.0F)),
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(MainMod.TAB)));

	private static <T extends Block> RegistryObject<T> registerBlock(final String name,
			final Supplier<? extends T> block) {
		return BLOCKS.register(name, block);
	}

	private static <T extends Block> RegistryObject<T> register(final String name, final Supplier<? extends T> block,
			Function<RegistryObject<T>, Supplier<? extends Item>> item) {
		RegistryObject<T> obj = registerBlock(name, block);
		ITEMS.register(name, item.apply(obj));
		return obj;
	}
}
