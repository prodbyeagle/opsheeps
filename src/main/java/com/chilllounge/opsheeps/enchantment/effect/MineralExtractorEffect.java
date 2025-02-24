package com.chilllounge.opsheeps.enchantment.effect;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;

import java.util.List;

public record MineralExtractorEffect(EnchantmentLevelBasedValue chance) implements EnchantmentEntityEffect {
	public static final MapCodec<MineralExtractorEffect> CODEC = RecordCodecBuilder.mapCodec(instance ->
			instance.group(
					EnchantmentLevelBasedValue.CODEC.fieldOf("chance").forGetter(MineralExtractorEffect::chance)
			).apply(instance, MineralExtractorEffect::new)
	);

	private static final List<ItemStack> MINERAL_DROPS = List.of(
			new ItemStack(Items.IRON_INGOT),
			new ItemStack(Items.GOLD_INGOT),
			new ItemStack(Items.COPPER_INGOT),
			new ItemStack(Items.DIAMOND),
			new ItemStack(Items.EMERALD),
			new ItemStack(Items.REDSTONE, 2),
			new ItemStack(Items.LAPIS_LAZULI, 2)
	);

	@Override
	public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity target, Vec3d pos) {
		if (target instanceof LivingEntity victim) {
			if (context.owner() instanceof PlayerEntity player) {
				float chanceValue = 3 * level;
				float dropChance = chanceValue / 100f;
				Random random = world.getRandom();

				if (random.nextFloat() < dropChance) {
					BlockPos spawnPos = victim.getBlockPos();
					ItemStack drop = MINERAL_DROPS.get(random.nextInt(MINERAL_DROPS.size()));

					ItemEntity itemEntity = new ItemEntity(world, spawnPos.getX() + 0.5, spawnPos.getY() + 1.0, spawnPos.getZ() + 0.5, drop);
					world.spawnEntity(itemEntity);
				}
			}
		}
	}

	@Override
	public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
		return CODEC;
	}
}
