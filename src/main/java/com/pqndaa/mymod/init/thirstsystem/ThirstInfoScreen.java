package com.pqndaa.mymod.init.thirstsystem;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.pqndaa.mymod.MainMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.text.DecimalFormat;

public class ThirstInfoScreen extends AbstractContainerScreen<ThirstInfoMenu> {

    private final Level level;
    private final int x,y,z;
    private final Player p;

    private final int thirstlevel;
    private final float thirstsaturation,thirstexhaustion;

    private static final DecimalFormat df = new DecimalFormat("0.00");

    public ThirstInfoScreen(ThirstInfoMenu container, Inventory inv, Component text){
        super(container,inv,text);
        this.x = container.x;
        this.y = container.y;
        this.z = container.z;
        this.thirstlevel = container.thirstlevel;
        this.thirstexhaustion = container.thirstexhaustion;
        this.thirstsaturation = container.thirstsaturation;
        this.p = container.p;
        this.level = container.level;
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    private static final ResourceLocation texture = new ResourceLocation(MainMod.MOD_ID, "textures/gui/thirst_info.png");

    @Override
    public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(ms);
        super.render(ms, mouseX, mouseY, partialTicks);
        this.renderTooltip(ms, mouseX, mouseY);
    }

    @Override
    protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderTexture(0, texture);
        this.blit(ms, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

        RenderSystem.setShaderTexture(0, new ResourceLocation(MainMod.MOD_ID,"textures/gui/goutte_deau.png"));
        this.blit(ms, this.leftPos + 71, this.topPos + 13, 0, 0, 210, 210, 210, 210);

        RenderSystem.disableBlend();
    }

    @Override
    public boolean keyPressed(int key, int b, int c) {
        if (key == 256) {
            this.minecraft.player.closeContainer();
            return true;
        }
        return super.keyPressed(key, b, c);
    }

    @Override
    public void containerTick() {
        super.containerTick();
    }

    @Override
    protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
        this.font.draw(poseStack, "Thirst Level: " + this.thirstlevel , 10, 56, -12829636);
        this.font.draw(poseStack, "Thirst Saturation: " + thirstsaturation, 10, 76, -12829636);
        this.font.draw(poseStack, "Thirst Exhaustion: " + df.format(thirstexhaustion), 10, 96, -12829636);
        this.font.draw(poseStack, "THIRST SYSTEM", 51, 14, -12829636);
    }

    @Override
    public void onClose() {
        super.onClose();
        Minecraft.getInstance().keyboardHandler.setSendRepeatsToGui(false);
    }

    @Override
    public void init() {
        super.init();
        this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
    }

}
