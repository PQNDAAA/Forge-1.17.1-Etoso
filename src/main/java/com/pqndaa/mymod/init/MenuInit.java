package com.pqndaa.mymod.init;

import com.pqndaa.mymod.init.thirstsystem.ThirstInfoMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fmllegacy.network.IContainerFactory;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MenuInit {

    private static final List<MenuType<?>> REGISTRY = new ArrayList<>();
    public static final MenuType<ThirstInfoMenu> THIRST_INFO = register("thirst_info",
            (id, inv, extraData) -> new ThirstInfoMenu(id, inv, extraData));

    private static <T extends AbstractContainerMenu> MenuType<T> register(String registryname, IContainerFactory<T> containerFactory) {
        MenuType<T> menuType = new MenuType<T>(containerFactory);
        menuType.setRegistryName(registryname);
        REGISTRY.add(menuType);
        return menuType;
    }

    @SubscribeEvent
    public static void registerContainers(RegistryEvent.Register<MenuType<?>> event) {
        event.getRegistry().registerAll(REGISTRY.toArray(new MenuType[0]));
    }

}
