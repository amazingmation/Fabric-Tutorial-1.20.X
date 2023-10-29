package net.amazingmation.tutorialmod;

import net.amazingmation.tutorialmod.item.BulletOneItem;
import net.amazingmation.tutorialmod.projectileentities.BulletOne;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
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
public static final Item BulletOneItem = new BulletOneItem(new Item.Settings().maxCount(1));
	@Override
	public void onInitialize() {
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "bullet_one"), BulletOneItem);

		LOGGER.info("Hello Fabric world!");
	}
}