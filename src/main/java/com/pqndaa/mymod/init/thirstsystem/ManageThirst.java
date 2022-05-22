package com.pqndaa.mymod.init.thirstsystem;


import com.mojang.blaze3d.systems.RenderSystem;
import com.pqndaa.mymod.MainMod;
import com.pqndaa.mymod.init.ItemInit;
import com.pqndaa.mymod.init.customitems.Water_Bottle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fmlclient.gui.GuiUtils;

public class ManageThirst extends Gui {

	private static final ResourceLocation bar = new ResourceLocation(MainMod.MOD_ID, "textures/gui/hpbar_2.png");
	private static final int tex_width = 102, tex_height = 8;
	private static final int bar_width = 100;
	private static final int bar_height = 6;
	protected ThirstData thirstdata = new ThirstData();

	public Level level;
	private static Minecraft mc = Minecraft.getInstance();

	public ManageThirst(Minecraft mc) {
		super(mc);
	}

	@SubscribeEvent
	public void GUIThirst(RenderGameOverlayEvent event) {
		if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
			if (!mc.player.isCreative() && (!mc.player.isSpectator())) {
				TextureManager tm = mc.getTextureManager();
				RenderSystem.setShaderTexture(0, bar);
				float oneUnit = (float) bar_width / 20;
				int currentWidth = (int) (oneUnit * this.thirstdata.getThirstLevel());
				//mc.player.sendMessage(new TextComponent("Waterlevel: " + this.thirstdata.getExhaustionLevel()), mc.player.getUUID());
				GuiUtils.drawTexturedModalRect(event.getMatrixStack(), 240, 221, 0, 0, tex_width, tex_height, 10);
				GuiUtils.drawTexturedModalRect(event.getMatrixStack(), 240, 221, 1, tex_height, currentWidth, bar_height, 10);
			}
		}
	}

	@SubscribeEvent
	public void Tick(TickEvent.PlayerTickEvent event) {
		this.thirstdata.tick(event.player);
	}


	@SubscribeEvent
	public void onRightClick(PlayerInteractEvent.LeftClickBlock event){


	}
}


