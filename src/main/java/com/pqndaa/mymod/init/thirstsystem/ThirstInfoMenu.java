package com.pqndaa.mymod.init.thirstsystem;

import com.pqndaa.mymod.init.MenuInit;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ThirstInfoMenu extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {
    public final static HashMap<String, Object> guistate = new HashMap<>();
    public final Level level;

    public final Player p;
    public int x, y, z;
    public int thirstlevel;
    public float thirstsaturation,thirstexhaustion;

    private IItemHandler internal;
    private final Map<Integer, Slot> customSlots = new HashMap<>();
    private final ThirstData thirstdata = new ThirstData();
    private boolean bound = false;

    public ThirstInfoMenu(int id, Inventory inv, FriendlyByteBuf fbb) {
        super(MenuInit.THIRST_INFO,id);
        this.p = inv.player;
        this.level = inv.player.level;
        this.internal = new ItemStackHandler(0);
        this.thirstlevel = thirstdata.getThirstLevel();
        this.thirstsaturation = thirstdata.getSaturationLevel();
        this.thirstexhaustion = thirstdata.getExhaustionLevel();
        BlockPos pos = null;
        if (fbb != null) {
            pos = fbb.readBlockPos();
            this.x = pos.getX();
            this.y = pos.getY();
            this.z = pos.getZ();
        }

    }

    public Map<Integer, Slot> get() {
        return customSlots;
    }

    @Override
    public boolean stillValid(Player p) {
        return true;
    }
}
