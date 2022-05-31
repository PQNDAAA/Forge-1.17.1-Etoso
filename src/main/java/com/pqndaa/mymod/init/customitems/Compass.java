package com.pqndaa.mymod.init.customitems;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Compass extends Item {

    public Compass(Properties properties) {
        super(properties);
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player p, InteractionHand interactionhand) {

        ItemStack itemstack = p.getItemInHand(interactionhand);

        //execute(p);

        return InteractionResultHolder.success(itemstack);

    }

    private static void execute(Entity entity) {
        if (entity == null)
            return;
        {
            Entity _ent = entity;
            if (!_ent.level.isClientSide() && _ent.getServer() != null)
                _ent.getServer().getCommands().performCommand(_ent.createCommandSourceStack().withSuppressedOutput(), "locatebiome minecraft:dark_forest");
        }
    }


}
