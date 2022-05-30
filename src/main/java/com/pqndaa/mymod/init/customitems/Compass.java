package com.pqndaa.mymod.init.customitems;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class Compass extends Item {


    public Compass(Properties properties) {
        super(properties);
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player p, InteractionHand interactionhand) {

        Minecraft mc = Minecraft.getInstance();
        ItemStack itemstack = p.getItemInHand(interactionhand);
        HitResult hitresult = getPlayerPOVHitResult(level, p, ClipContext.Fluid.SOURCE_ONLY);
        BlockPos blockpos = ((BlockHitResult)hitresult).getBlockPos();

     //   if(p.level.isClientSide && p.getServer() != null){
       //     p.getServer().getCommands().performCommand(p.createCommandSourceStack().withSuppressedOutput().withPermission(4), "locatebiome mymod:lava_land");
        //}


        System.out.println("cc " + getDistanceToEntity(p,blockpos));

        return InteractionResultHolder.success(itemstack);

    }

    public static double getDistanceToEntity(Player p, BlockPos pos) {
        double deltaX = p.position().x - pos.getX();
        double deltaY = p.position().y - pos.getY();
        double deltaZ = p.position().z - pos.getZ();

        return Math.sqrt((deltaX * deltaX) + (deltaY * deltaY) + (deltaZ * deltaZ));
    }
}
