package com.pqndaa.mymod.init.customitems;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.List;

public class Water_Bottle_Full extends PotionItem {
    public Water_Bottle_Full(Properties properties) {
        super(properties);
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player p, InteractionHand interactionhand) {
        List<AreaEffectCloud> list = level.getEntitiesOfClass(AreaEffectCloud.class, p.getBoundingBox().inflate(2.0D), (predicate) -> {
            return predicate != null && predicate.isAlive() && predicate.getOwner() instanceof EnderDragon;
        });
        ItemStack itemstack = p.getItemInHand(interactionhand);
        HitResult hitresult = getPlayerPOVHitResult(level, p, ClipContext.Fluid.SOURCE_ONLY);
        BlockPos blockpos = ((BlockHitResult)hitresult).getBlockPos();

        if (!list.isEmpty()) {
            AreaEffectCloud areaeffectcloud = list.get(0);
            areaeffectcloud.setRadius(areaeffectcloud.getRadius() - 0.5F);
            level.playSound((Player)null, p.getX(), p.getY(), p.getZ(), SoundEvents.BOTTLE_FILL_DRAGONBREATH, SoundSource.NEUTRAL, 1.0F, 1.0F);
            return InteractionResultHolder.success(itemstack);
        } else {
            if (hitresult.getType() == HitResult.Type.MISS) {
                return InteractionResultHolder.success(itemstack);
            } else {
                if (hitresult.getType() == HitResult.Type.BLOCK) {

                    if (!level.mayInteract(p, blockpos)) {
                        return InteractionResultHolder.success(itemstack);
                    }
                  //  if (level.getFluidState(blockpos).is(FluidTags.WATER) && this.Sips <= this.MaxSips) {
                        level.playSound(p, p.getX(), p.getY(), p.getZ(), SoundEvents.BOTTLE_FILL, SoundSource.NEUTRAL, 1.0F, 1.0F);
                     //   ++this.Sips;
                     //   System.out.println(this.Sips + "/" + this.MaxSips);
                     //   return InteractionResultHolder.sidedSuccess(this.turnBottleIntoItem(itemstack, p, PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER)), level.isClientSide());

                    }
                }
                return InteractionResultHolder.success(itemstack);
            }
        }
    }
