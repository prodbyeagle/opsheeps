package com.chilllounge.opsheeps.enchantment.effect;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;

import java.util.List;

public record EchoShearEffect(EnchantmentLevelBasedValue chance) implements EnchantmentEntityEffect {
	public static final MapCodec<EchoShearEffect> CODEC = RecordCodecBuilder.mapCodec(instance ->
			instance.group(
					EnchantmentLevelBasedValue.CODEC.fieldOf("chance").forGetter(EchoShearEffect::chance)
			).apply(instance, EchoShearEffect::new)
	);

	@Override
	public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity target, Vec3d pos) {
		if (!(target instanceof SheepEntity sheared)) {
			return;
		}
		Random random = world.getRandom();
		float dropChance = (level == 1 ? 0.2f : 0.4f);

		List<SheepEntity> nearbySheep = world.getEntitiesByClass(SheepEntity.class, sheared.getBoundingBox().expand(5),
				e -> !e.isSheared() && e != sheared);

		for (SheepEntity nearby : nearbySheep) {
			if (random.nextFloat() < dropChance) {
				BlockPos dropPos = nearby.getBlockPos();
				ItemStack woolDrop = getWoolDrop(nearby);
				ItemEntity itemEntity = new ItemEntity(world, dropPos.getX() + 0.5, dropPos.getY() + 1.0, dropPos.getZ() + 0.5, woolDrop);
				world.spawnEntity(itemEntity);
				nearby.setSheared(true);
			}
		}
	}

	private ItemStack getWoolDrop(SheepEntity sheep) {
		DyeColor color = sheep.getColor();
		return switch (color) {
			case ORANGE -> new ItemStack(Items.ORANGE_WOOL);
			case MAGENTA -> new ItemStack(Items.MAGENTA_WOOL);
			case LIGHT_BLUE -> new ItemStack(Items.LIGHT_BLUE_WOOL);
			case YELLOW -> new ItemStack(Items.YELLOW_WOOL);
			case LIME -> new ItemStack(Items.LIME_WOOL);
			case PINK -> new ItemStack(Items.PINK_WOOL);
			case GRAY -> new ItemStack(Items.GRAY_WOOL);
			case LIGHT_GRAY -> new ItemStack(Items.LIGHT_GRAY_WOOL);
			case CYAN -> new ItemStack(Items.CYAN_WOOL);
			case PURPLE -> new ItemStack(Items.PURPLE_WOOL);
			case BLUE -> new ItemStack(Items.BLUE_WOOL);
			case BROWN -> new ItemStack(Items.BROWN_WOOL);
			case GREEN -> new ItemStack(Items.GREEN_WOOL);
			case RED -> new ItemStack(Items.RED_WOOL);
			case BLACK -> new ItemStack(Items.BLACK_WOOL);
			default -> new ItemStack(Items.WHITE_WOOL);
		};
	}

	@Override
	public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
		return CODEC;
	}
}
