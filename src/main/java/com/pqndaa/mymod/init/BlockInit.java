package com.pqndaa.mymod.init;

import com.google.common.base.Supplier;
import com.pqndaa.mymod.MainMod;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
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

	public static final RegistryObject<Block> TRITIUM_ORE = register("tritium_ore",
			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3.0f)),
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(MainMod.TAB)));
	public static final RegistryObject<Block> EXO_BLOCK = register("exo_block",
			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3.0f)),
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(MainMod.TAB)));

	public static final RegistryObject<Block> PLAXOTIUM_BLOCK = register("plaxotium_block",
			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3.0f)),
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(MainMod.TAB)));

	public static final RegistryObject<Block> TRITIUM_BLOCK = register("tritium_block",
			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3.0f)),
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(MainMod.TAB)));
	public static final RegistryObject<Block> BURN_GRASS = register("burn_grass",
			() -> new Block(BlockBehaviour.Properties.of(Material.GRASS).sound(SoundType.GRASS).strength(2.0F,8.0F)),
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(MainMod.TAB)));

	public static final RegistryObject<Block> GRAVEYARD_GRASS = register("graveyard_grass",
			() -> new Block(BlockBehaviour.Properties.of(Material.GRASS).sound(SoundType.GRASS).strength(2.0F,8.0F)),
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(MainMod.TAB)));


	public static final RegistryObject<Block> REDWOOD_LEAVES = registerBlockLeaves("redwood_leaves",
			() -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)) {
				@Override
				public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return true;
				}

				@Override
				public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 60;
				}

				@Override
				public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 30;
				}
			}, MainMod.TAB);


	public static final RegistryObject<Block> GRAVEYARD_LEAVES = registerBlockLeaves("graveyard_leaves",
			() -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)) {
				@Override
				public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return true;
				}

				@Override
				public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 60;
				}

				@Override
				public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 30;
				}
			}, MainMod.TAB);

	private static <T extends Block> RegistryObject<T> registerBlock(final String name,
			final Supplier<? extends T> block) {
		return BLOCKS.register(name, block);
	}

	private static <T extends Block> RegistryObject<T> registerBlockLeaves(String name, Supplier<T> block, CreativeModeTab tab) {
		RegistryObject<T> toReturn = BLOCKS.register(name, block);
		registerBlockItem(name, toReturn, tab);
		return toReturn;
	}

	private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
																			CreativeModeTab tab) {
		return ItemInit.ITEMS.register(name, () -> new BlockItem(block.get(),
				new Item.Properties().tab(tab)));
	}

	private static <T extends Block> RegistryObject<T> register(final String name, final Supplier<? extends T> block,
			Function<RegistryObject<T>, Supplier<? extends Item>> item) {
		RegistryObject<T> obj = registerBlock(name, block);
		ITEMS.register(name, item.apply(obj));
		return obj;
	}
}
