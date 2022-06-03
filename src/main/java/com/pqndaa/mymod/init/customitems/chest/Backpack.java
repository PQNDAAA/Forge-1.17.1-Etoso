package com.pqndaa.mymod.init.customitems.chest;

import io.netty.buffer.Unpooled;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fmllegacy.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class Backpack extends Item{
    public Backpack(Item.Properties properties) {
        super(properties);
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player p, InteractionHand interactionhand) {
        InteractionResultHolder<ItemStack> ar = super.use(level,p, interactionhand);
        ItemStack itemstack = ar.getObject();
        double x = p.getX();
        double y = p.getY();
        double z = p.getZ();


        if(p instanceof ServerPlayer serverPlayer){
            NetworkHooks.openGui(serverPlayer, new MenuProvider() {
                @Override
                public Component getDisplayName() {
                    return new TextComponent("Backpack");
                }
                @Override
                public AbstractContainerMenu createMenu(int id, Inventory inv, Player p) {
                    FriendlyByteBuf fbb = new FriendlyByteBuf(Unpooled.buffer());
                    fbb.writeBlockPos(p.blockPosition());
                    fbb.writeByte(interactionhand == InteractionHand.MAIN_HAND ? 0 : 1);
                    return new BackpackMenu(id, inv, fbb);
                }
            },fbb -> {
                fbb.writeBlockPos(p.blockPosition());
                fbb.writeByte(interactionhand == InteractionHand.MAIN_HAND ? 0 : 1);
            });
        }
        return ar;
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag compound) {
        return new BackpackItemInventoryCapability();
    }

    @Override
    public CompoundTag getShareTag(ItemStack stack) {
        CompoundTag nbt = super.getShareTag(stack);
        if (nbt != null)
            stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)
                    .ifPresent(capability -> nbt.put("Inventory", ((ItemStackHandler) capability).serializeNBT()));
        return nbt;
    }

    @Override
    public void readShareTag(ItemStack stack, @Nullable CompoundTag nbt) {
        super.readShareTag(stack, nbt);
        if (nbt != null)
            stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)
                    .ifPresent(capability -> ((ItemStackHandler) capability).deserializeNBT((CompoundTag) nbt.get("Inventory")));
    }

}
