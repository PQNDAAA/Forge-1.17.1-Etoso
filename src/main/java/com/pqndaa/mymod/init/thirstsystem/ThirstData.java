package com.pqndaa.mymod.init.thirstsystem;

import net.minecraft.client.Minecraft;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.level.GameRules;

public class ThirstData {

    private static int thirstLevel = 20;
    private static float saturationLevel;
    private int tickTimer;
    private static float exhaustionLevel;
    private int lastThirstLevel;
    private Minecraft mc = Minecraft.getInstance();
    public ThirstData() {
        saturationLevel = 3.0F;
    }

    @SuppressWarnings("static-access")
    public void tick(Player p) {
        Difficulty difficulty = p.level.getDifficulty();
        this.lastThirstLevel = thirstLevel;

        if (!p.isCreative()) {


            if (p.isSprinting()) {
                addExhaustion(0.002F);
            } else {
                addExhaustion(0.0003F);
            }
            if(p.isCrouching()){
                addExhaustion(0.0001F);
            }
            if(p.isSwimming()){
                addExhaustion(0.0002F);
            }

            if (exhaustionLevel > 3.0F) {
                exhaustionLevel -= 3.0F;
                if (saturationLevel > 0.0F) {
                    saturationLevel = Math.max(saturationLevel - 1.0F, 0.0F);
                } else if (difficulty != difficulty.PEACEFUL) {
                    thirstLevel = Math.max(thirstLevel - 1, 0);
                }
            }
            boolean flag = p.level.getGameRules().getBoolean(GameRules.RULE_NATURAL_REGENERATION);
            if (flag && saturationLevel > 0.0F && p.isHurt() && thirstLevel >= 20) {
                ++this.tickTimer;
                if (this.tickTimer >= 20) {
                    float f = Math.min(saturationLevel, 6.0F);
                    p.heal(f / 6.0F);
                    addExhaustion(f);
                    this.tickTimer = 0;
                }

            } else if (flag && thirstLevel >= 18 && p.isHurt()) {
                ++this.tickTimer;
                if (this.tickTimer >= 100) {
                    p.heal(1);
                    addExhaustion(4.0F);
                    this.tickTimer = 0;
                }
            } else if (thirstLevel <= 0) {
                ++this.tickTimer;
                if (this.tickTimer >= 40) {
                    if (p.getHealth() > 1.0F && difficulty == Difficulty.NORMAL || difficulty == Difficulty.HARD
                            || p.getHealth() > 10.0F) {
                        p.hurt(DamageSource.STARVE, 1.0F);
                    }
                    this.tickTimer = 0;
                }
            } else {
                this.tickTimer = 0;
            }
        }
    }

    public void addExhaustion(float exhaustion) {
        exhaustionLevel = Math.min(exhaustionLevel + exhaustion, 40.0F);
    }

    //GET VARIABLES

    public int getThirstLevel() {
        return thirstLevel;
    }
    public float getSaturationLevel() {
        return saturationLevel;
    }
    public int getLastThirstLevel() {
        return this.lastThirstLevel;
    }
    public float getExhaustionLevel() {
        return exhaustionLevel;
    }

    //SET VARIABLES

    public void setThirstLevel(int thirst) {
        thirstLevel = thirst;
    }
    public void setSaturationLevel(float saturation) {
        saturationLevel = saturation;
    }
    public void setExhaustion(float exhaustion) {
        exhaustionLevel = exhaustion;
    }

    public boolean needsThirst() {
        return thirstLevel < 20;
    }


}
