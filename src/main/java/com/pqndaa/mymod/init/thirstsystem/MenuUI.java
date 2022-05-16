package com.pqndaa.mymod.init.thirstsystem;


import com.mojang.blaze3d.systems.RenderSystem;
import com.pqndaa.mymod.MainMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodData;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fmlclient.gui.GuiUtils;

public class MenuUI extends Gui {
	
	private static final ResourceLocation bar = new ResourceLocation(MainMod.MOD_ID, "textures/gui/hpbar_2.png");
	
	private static final int tex_width = 102, tex_height = 8;
	private static final int bar_width = 100;
	private static final int bar_height=6; 
	
	private static Minecraft mc = Minecraft.getInstance();
	
	private static ThirstData thirstdata = new ThirstData();

	private static FoodData fooddata = new FoodData();

    public MenuUI(Minecraft mc) {
		super(mc);
	}
    
    public static ThirstData getThirstData() {
    	return thirstdata;
    }

	public static FoodData getFoodData() {
		return fooddata;
	}

	@SubscribeEvent
    public static void GUITest(RenderGameOverlayEvent event) {
		if(event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
			if(!mc.player.isCreative() && (!mc.player.isSpectator())) {
			TextureManager tm = mc.getTextureManager();
			RenderSystem.setShaderTexture(0, bar);
			float oneUnit = (float)bar_width / 20;
			int currentWidth = (int) (oneUnit * getThirstData().getThirstLevel());
			mc.player.sendMessage(new TextComponent("Waterlevel: " + getThirstData().getExhaustionLevel()), mc.player.getUUID());
		    GuiUtils.drawTexturedModalRect(event.getMatrixStack(), 240,221,0,0,tex_width,tex_height,0);
		    GuiUtils.drawTexturedModalRect(event.getMatrixStack(), 240,221,1,tex_height,currentWidth,bar_height,0);

			}
		}
    }
	
	
	
	
	
}
