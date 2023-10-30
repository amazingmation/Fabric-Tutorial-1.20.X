package net.amazingmation.tutorialmod.item;

import net.amazingmation.tutorialmod.TutorialMod;
import net.amazingmation.tutorialmod.projectileentities.BulletOne;
import net.fabricmc.loader.impl.lib.sat4j.core.Vec;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Objects;

public class BulletOneItem extends Item {


    public BulletOneItem(Settings settings) {
        super(settings);
    }
}
