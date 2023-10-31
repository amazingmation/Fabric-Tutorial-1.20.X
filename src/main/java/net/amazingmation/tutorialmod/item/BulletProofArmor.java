package net.amazingmation.tutorialmod.item;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class BulletProofArmor implements ArmorMaterial {

    @Override
    public int getDurability(ArmorItem.Type type) {
        return 40;
    }

    @Override
    public int getProtection(ArmorItem.Type type) {
        return 40;
    }

    @Override
    public int getEnchantability() {
        return 0;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }

    @Override
    public String getName() {
        // Must be all lowercase
        return "bullet_proof";
    }

    @Override
    public float getToughness() {
        return 2.0F;
    }

    @Override
    public float getKnockbackResistance() {
        return 2.5F;
    }
}
