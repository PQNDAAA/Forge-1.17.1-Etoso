package com.pqndaa.mymod.init;

import com.pqndaa.mymod.MainMod;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum ModArmorMaterial implements ArmorMaterial {
    EXO("exo", 55, new int[]{2, 5, 6, 2}, 5, SoundEvents.ARMOR_EQUIP_NETHERITE,
            1.0F, 0.0F, () -> {
        return Ingredient.of(ItemInit.EXO_INGOT.get());
    }),
    TRITIUM("tritium", 75, new int[]{4, 7, 9, 5}, 5, SoundEvents.ARMOR_EQUIP_NETHERITE,
            4.0F, 0.2F, () -> {
        return Ingredient.of(ItemInit.TRITIUM_INGOT.get());
    }),
    PLAXOTIUM("plaxotium", 65, new int[]{3, 7, 8, 3}, 5, SoundEvents.ARMOR_EQUIP_NETHERITE,
            2.0F, 0.0F, () -> {
        return Ingredient.of(ItemInit.PLAXOTIUM_INGOT.get());
    });
    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    private ModArmorMaterial(String name, int durability, int[] slotprotect, int enchantmentvalue,
                             SoundEvent sound, float toughness, float knockbackresist, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durability;
        this.slotProtections = slotprotect;
        this.enchantmentValue = enchantmentvalue;
        this.sound = sound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackresist;
        this.repairIngredient = new LazyLoadedValue<>(repairIngredient);
    }


    @Override
    public int getDurabilityForSlot(EquipmentSlot p_40410_) {
        return HEALTH_PER_SLOT[p_40410_.getIndex()] * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlot p_40411_) {
        return this.slotProtections[p_40411_.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.sound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public String getName() {
        return MainMod.MOD_ID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
