package com.pqndaa.mymod;

import com.pqndaa.mymod.event.CommonModEvents;
import com.pqndaa.mymod.init.BlockInit;
import com.pqndaa.mymod.init.ItemInit;
import com.pqndaa.mymod.init.thirstsystem.ManageThirst;
import com.pqndaa.mymod.init.world.biome.ModBiomes;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fmllegacy.network.NetworkEvent;
import net.minecraftforge.fmllegacy.network.NetworkRegistry;
import net.minecraftforge.fmllegacy.network.simple.SimpleChannel;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Mod("mymod")
public class MainMod {

    public static final String MOD_ID = "mymod";
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation(MOD_ID, MOD_ID), () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
    private static int messageID = 0;

    public static final CreativeModeTab TAB = new CreativeModeTab(MOD_ID) {

        @Override
        @OnlyIn(Dist.CLIENT)
        public ItemStack makeIcon() {
            return new ItemStack(ItemInit.EXAMPLE_ITEM.get());
        }

    };

    public MainMod() {

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        Minecraft mc = Minecraft.getInstance();

        ItemInit.ITEMS.register(bus);
        BlockInit.BLOCKS.register(bus);
        ModBiomes.register(bus);

        MinecraftForge.EVENT_BUS.register(this);

        MinecraftForge.EVENT_BUS.register(new ManageThirst(mc));

        CommonModEvents.setup();
        bus.addListener(CommonModEvents::init);
        bus.addListener(CommonModEvents::clientLoad);
    }

    public static <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder,
                                             BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
        PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
        messageID++;
    }


}   //TESTdzadza
