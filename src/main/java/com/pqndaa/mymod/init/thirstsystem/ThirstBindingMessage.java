package com.pqndaa.mymod.init.thirstsystem;

import com.pqndaa.mymod.MainMod;
import com.pqndaa.mymod.init.KeyInit;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ThirstBindingMessage {
    int type, pressedms;

    public ThirstBindingMessage(int type, int pressedms) {
        this.type = type;
        this.pressedms = pressedms;
    }

    public ThirstBindingMessage(FriendlyByteBuf buffer) {
        this.type = buffer.readInt();
        this.pressedms = buffer.readInt();
    }

    public static void buffer(ThirstBindingMessage message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.type);
        buffer.writeInt(message.pressedms);
    }

    public static void handler(ThirstBindingMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            pressAction(context.getSender(), message.type, message.pressedms);
        });
        context.setPacketHandled(true);
    }

    public static void pressAction(Player entity, int type, int pressedms) {
        Level world = entity.level;
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        // security measure to prevent arbitrary chunk generation
        if (!world.hasChunkAt(entity.blockPosition()))
            return;
        if (type == 0) {

            ThirstBindingOnPressedProcedure.execute(world, x, y, z, entity);
        }
    }

    @SubscribeEvent
    public static void registerMessage(FMLCommonSetupEvent event) {
        MainMod.addNetworkMessage(ThirstBindingMessage.class, ThirstBindingMessage::buffer, ThirstBindingMessage::new,
                ThirstBindingMessage::handler);
    }

}
