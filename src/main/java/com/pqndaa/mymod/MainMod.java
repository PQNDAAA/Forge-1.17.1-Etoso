package com.pqndaa.mymod;

import com.pqndaa.mymod.event.CommonModEvents;
import com.pqndaa.mymod.init.BlockInit;
import com.pqndaa.mymod.init.ItemInit;
import com.pqndaa.mymod.world.biome.ModBiomes;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("mymod")
public class MainMod {

    public static final String MOD_ID = "mymod";


    public static final CreativeModeTab TAB = new CreativeModeTab(MOD_ID) {

        @Override
        @OnlyIn(Dist.CLIENT)
        public ItemStack makeIcon() {
            return new ItemStack(ItemInit.EXAMPLE_ITEM.get());
        }

    };

    public MainMod() {

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ItemInit.ITEMS.register(bus);
        BlockInit.BLOCKS.register(bus);
        ModBiomes.register(bus);

        MinecraftForge.EVENT_BUS.register(this);

        CommonModEvents.setup();
        bus.addListener(CommonModEvents::init);
    }

}   //TESTdzadza
