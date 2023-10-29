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

    public static void movePlayerBackwards() {
        PlayerEntity player = MinecraftClient.getInstance().player;
        // Get the player's facing direction.
        assert player != null;
        Vec3d facingDirection = player.getRotationVec(0.5F);

        // Multiply the facing direction by the desired distance to move.
        Vec3d movementVector = facingDirection.multiply(-0.1);

        Vec3d addedMovementVector = new Vec3d(movementVector.x + player.getVelocity().x, movementVector.y + player.getVelocity().y, movementVector.z + player.getVelocity().z);

        // Set the player's velocity to the movement vector.
        player.setVelocity(addedMovementVector);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.NEUTRAL, 0.5F, 1F);

        user.getItemCooldownManager().set(this, 5);

        if (!world.isClient) {
            BulletOne bulletOneEntity = new BulletOne(world, user);
            bulletOneEntity.setItem(itemStack);
            bulletOneEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
            world.spawnEntity(bulletOneEntity);
            //Objects.requireNonNull(user).pushAwayFrom(bulletOneEntity);
            movePlayerBackwards();
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            //itemStack.decrement(1); will add get rid of some ammo in the future but dont get rid of gun lol
        }

        return TypedActionResult.success(itemStack, world.isClient());
    }

}
