package net.amazingmation.tutorialmod.item;

import net.amazingmation.tutorialmod.projectileentities.BulletOne;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class BulletOneItem extends Item {
    public BulletOneItem(Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.NEUTRAL, 0.5F, 1F);

        user.getItemCooldownManager().set(this, 1);

        if (!world.isClient) {
            BulletOne bulletOneEntity = new BulletOne(world, user);
            bulletOneEntity.setItem(itemStack);
            bulletOneEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
            world.spawnEntity(bulletOneEntity);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }

        return TypedActionResult.success(itemStack, world.isClient());
    }

}
