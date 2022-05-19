package com.pqndaa.mymod.init.thirstsystem;


import com.mojang.blaze3d.systems.RenderSystem;
import com.pqndaa.mymod.MainMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BottleItem;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fmlclient.gui.GuiUtils;

public class ManageThirst extends Gui {
	
	private static final ResourceLocation bar = new ResourceLocation(MainMod.MOD_ID, "textures/gui/hpbar_2.png");
	
	private static final int tex_width = 102, tex_height = 8;
	private static final int bar_width = 100;
	private static final int bar_height=6;

	protected ThirstData thirstdata = new ThirstData();
	
	private static Minecraft mc = Minecraft.getInstance();

    public ManageThirst(Minecraft mc) {
		super(mc);
	}

	@SubscribeEvent
    public void GUIThirst(RenderGameOverlayEvent event) {
		if(event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
			if(!mc.player.isCreative() && (!mc.player.isSpectator())) {
			TextureManager tm = mc.getTextureManager();
			RenderSystem.setShaderTexture(0, bar);
			float oneUnit = (float)bar_width / 20;
			int currentWidth = (int) (oneUnit * this.thirstdata.getThirstLevel());
			mc.player.sendMessage(new TextComponent("Waterlevel: " + this.thirstdata.getExhaustionLevel()), mc.player.getUUID());
		    GuiUtils.drawTexturedModalRect(event.getMatrixStack(), 240,221,0,0,tex_width,tex_height,10);
		    GuiUtils.drawTexturedModalRect(event.getMatrixStack(), 240,221,1,tex_height,currentWidth,bar_height,10);
			}
		}
    }
	@SubscribeEvent
	public void Tick(TickEvent.PlayerTickEvent event) {
		this.thirstdata.tick(event.player);
	}

	@SubscribeEvent
	public void rightclickItem(PlayerInteractEvent.RightClickBlock event) {

		if (event.getItemStack().getItem() instanceof BottleItem) {

			final BlockPos blockpos = new BlockPos(event.getHitVec().getBlockPos());
			// final BlockHitResult pos = new BlockHitResult(event.getHitVec(),event.getPlayer().getDirection(),event.getPlayer().getOnPos(),false);
			final BlockState state = event.getWorld().getBlockState(blockpos);
			final Player p = event.getPlayer();

			if (state != null && state.getMaterial() == Material.WATER &&
					(state.getBlock() instanceof IFluidBlock || state.getBlock() instanceof LiquidBlock)
			) {

				event.getWorld().playSound(p, p.position().x, p.position().y, p.position().z, SoundEvents.BOTTLE_FILL, SoundSource.NEUTRAL, 1.0F, 1.0F);

				if (!p.isCreative()) {

					event.getItemStack().shrink(1);
					thirstdata.setThirstLevel(thirstdata.getThirstLevel()+1);

				}

			}

		}
	}
}


