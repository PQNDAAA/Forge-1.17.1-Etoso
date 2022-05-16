package com.pqndaa.mymod.init.customitems;

import javax.swing.text.JTextComponent.KeyBinding;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DowsingItems extends Item{
	
	public DowsingItems(Properties pProperties) {
		super(pProperties);
	}
	
    @SubscribeEvent
    public static void pickupItem(EntityItemPickupEvent event) {
        System.out.println("Item picked up!");
        event.getPlayer().setSpeed(10);
    }
    
    @SubscribeEvent
    public static void rightclickItem(PlayerInteractEvent event) {
    	
    	Player p = event.getPlayer();

    	if(event instanceof PlayerInteractEvent.RightClickItem && p.isUnderWater()) {
    		event.setCanceled(true);
    		System.out.println("OK");
    		
    	}
    	
    }
   
     
    
}
