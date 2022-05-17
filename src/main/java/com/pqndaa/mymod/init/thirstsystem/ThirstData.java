package com.pqndaa.mymod.init.thirstsystem;

import net.minecraft.client.Minecraft;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;

public class ThirstData {

    private int thirstLevel = 20;
    private float saturationLevel;
    private int test = 20;
    private int tickTimer;
    private float exhaustionLevel;
    private int lastThirstLevel;
    private Minecraft mc = Minecraft.getInstance();

    public ThirstData() {
        saturationLevel = 5.0F;
    }

    @SuppressWarnings("static-access")
    public void tick(Player p) {
        Difficulty difficulty = p.level.getDifficulty();
        this.lastThirstLevel = this.thirstLevel;

        addExhaustion(0.0003F);

        if(p.isSprinting()){
            addExhaustion(0.001F);
        }

        if(this.exhaustionLevel > 4.0F) {
            this.exhaustionLevel -= 4.0F;
            if(this.saturationLevel > 0.0F) {
                this.saturationLevel = Math.max(this.saturationLevel - 1.0F, 0.0F);
            } else if(difficulty != difficulty.PEACEFUL) {
                this.thirstLevel = Math.max(this.thirstLevel - 1 , 0);
            }
        }
        boolean flag = p.level.getGameRules().getBoolean(GameRules.RULE_NATURAL_REGENERATION);
        if(flag && this.saturationLevel > 0.0F && p.isHurt() && this.thirstLevel >= 20) {
            ++this.tickTimer;
            if(this.tickTimer >= 20) {
                float f = Math.min(this.saturationLevel, 6.0F);
                p.heal(f / 6.0F);
                addExhaustion(f);
                this.tickTimer = 0;
            }

        } else if(flag && this.thirstLevel >= 18 && p.isHurt()) {
            ++this.tickTimer;
            if(this.tickTimer >= 100) {
                p.heal(1);
                addExhaustion(6.0F);
                this.tickTimer = 0;
            }
        } else if(this.thirstLevel <= 0) {
            ++this.tickTimer;
            if(this.tickTimer >= 40) {
                if(p.getHealth() > 1.0F && difficulty == Difficulty.NORMAL || difficulty == Difficulty.HARD || p.getHealth() > 10.0F) {
                    p.hurt(DamageSource.STARVE, 1.0F);
                }
                this.tickTimer = 0;
            }
        } else {
            this.tickTimer = 0;
        }
    }

    public void addExhaustion(float exhaustion) {
        this.exhaustionLevel = Math.min(this.exhaustionLevel + exhaustion, 40.0F);
    }

    //GET VARIABLES

    public int getThirstLevel() {
        return this.thirstLevel;
    }
    public float getSaturationLevel() {
        return this.saturationLevel;
    }
    public int getLastThirstLevel() {
        return this.lastThirstLevel;
    }
    public  float getExhaustionLevel() {
        return this.exhaustionLevel;
    }

    //SET VARIABLES

    public void setThirstLevel(int thirst) {
        this.thirstLevel = thirst;
    }
    public void setSaturationLevel(float saturation) {
        this.saturationLevel = saturation;
    }
    public void setExhaustion(float exhaustion) {
        this.exhaustionLevel = exhaustion;
    }

    public boolean needsThirst() {
        return this.thirstLevel < 20;
    }


}
