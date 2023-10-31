package net.amazingmation.tutorialmod;

import net.amazingmation.tutorialmod.item.BulletOneItem;
import net.amazingmation.tutorialmod.item.BulletProofArmor;
import net.amazingmation.tutorialmod.item.GunOneItem;
import net.amazingmation.tutorialmod.item.GunTwoItem;
import net.amazingmation.tutorialmod.projectileentities.BulletOne;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.render.entity.*;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModelManager;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.entity.*;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TutorialMod implements ModInitializer {
	public static final String MOD_ID = "tutorialmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
public static final EntityType<BulletOne> BulletOneEntityType = Registry.register(
		Registries.ENTITY_TYPE,
		new Identifier(MOD_ID, "bullet_one"),
		FabricEntityTypeBuilder.<BulletOne>create(SpawnGroup.MISC, BulletOne::new).dimensions(EntityDimensions.fixed(0.25F, 0.25F)).build()
);
public static final Item BulletOneItem = new BulletOneItem(new Item.Settings().maxCount(16));
public static final Item GunOneItem = new GunOneItem(new Item.Settings().maxCount(1));
public static final ArmorMaterial BulletProofArmorMaterial = new BulletProofArmor();
public static final Item BulletProofHelmet = new ArmorItem(BulletProofArmorMaterial, ArmorItem.Type.HELMET, new Item.Settings());
public static final Item BulletProofChestplate = new ArmorItem(BulletProofArmorMaterial, ArmorItem.Type.CHESTPLATE, new Item.Settings());
public static final Item BulletProofLeggings = new ArmorItem(BulletProofArmorMaterial, ArmorItem.Type.LEGGINGS, new Item.Settings());
public static final Item BulletProofBoots = new ArmorItem(BulletProofArmorMaterial, ArmorItem.Type.BOOTS, new Item.Settings());


	public static final Item GunTwoItem = new GunTwoItem(new Item.Settings().maxCount(1));
	@Override
	public void onInitialize() {
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "bullet_one_item"), BulletOneItem);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "gun_one_item"), GunOneItem);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "gun_two_item"), GunTwoItem);

		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "bullet_proof_helmet"), BulletProofHelmet);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "bullet_proof_chesptplate"), BulletProofChestplate);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "bullet_proof_leggings"), BulletProofLeggings);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "bullet_proof_boots"), BulletProofBoots);


		EntityRendererRegistry.register(TutorialMod.BulletOneEntityType, FlyingItemEntityRenderer::new);
		//EntityRendererRegistry.register(TutorialMod.BulletOneEntityType, new EntityRenderer());

		LOGGER.info("Hello Fabric world!");
	}
}