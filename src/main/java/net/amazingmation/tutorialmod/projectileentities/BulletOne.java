package net.amazingmation.tutorialmod.projectileentities;

import net.amazingmation.tutorialmod.TutorialMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class BulletOne extends ThrownItemEntity {
    public BulletOne(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public BulletOne(World world, LivingEntity owner) {
        super(TutorialMod.BulletOneEntityType, owner, world);
    }
    public BulletOne(World world, double x, double y, double z) {
        super(TutorialMod.BulletOneEntityType, x, y, z, world);
    }

    @Override
    protected Item getDefaultItem() {
        return TutorialMod.BulletOneItem;
    }

    @Environment(EnvType.CLIENT)
    private ParticleEffect getParticleParameters() {
        ItemStack itemStack = this.getItem();
        return (ParticleEffect)(itemStack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack));
    }

    @Environment(EnvType.CLIENT)
    public void handleStatus(byte status) {
        if (status == 3) {
            ParticleEffect particleEffect = this.getParticleParameters();

            for(int i = 0; i < 8; ++i) {
                this.getEntityWorld().addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        int i = entity instanceof LivingEntity ? 3 : 0;
        entity.damage(getDamageSources().generic(), 4.0F);

        if (entity instanceof LivingEntity livingEntity) {
            livingEntity.addStatusEffect((new StatusEffectInstance(StatusEffects.NAUSEA, 5, 0)));
            livingEntity.playSound(SoundEvents.ENTITY_VILLAGER_HURT, 2F, 1F);
            livingEntity.pushAwayFrom(this.getOwner());
        }
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getEntityWorld().isClient) {
            this.getEntityWorld().sendEntityStatus(this, (byte)3);
            this.kill();
        }
    }
}
