package com.pqndaa.mymod.init.thirstsystem;


import com.mojang.blaze3d.systems.RenderSystem;
import com.pqndaa.mymod.MainMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
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
		    GuiUtils.drawTexturedModalRect(event.getMatrixStack(), 240,221,0,0,tex_width,tex_height,0);
		    GuiUtils.drawTexturedModalRect(event.getMatrixStack(), 240,221,1,tex_height,currentWidth,bar_height,0);

			}
		}
    }
	@SubscribeEvent
	public void Tick(TickEvent.PlayerTickEvent event) {
		this.thirstdata.tick(event.player);
	}
	
	
	
	
	
}
