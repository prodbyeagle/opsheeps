package com.chilllounge.opsheeps.enchantment;

import com.chilllounge.opsheeps.Opsheeps;
import com.chilllounge.opsheeps.enchantment.effect.EchoShearEffect;
import com.chilllounge.opsheeps.enchantment.effect.MineralExtractorEffect;
import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEnchantmentEffects {
	public static final RegistryKey<Enchantment> MINERAL_EXTRACTOR = of("mineral_extractor");
	public static final RegistryKey<Enchantment> ECHO_SHEAR = of("echo_shear"); // Echo Shear Key

	public static MapCodec<MineralExtractorEffect> MINERAL_EXTRACTOR_EFFECT = register("mineral_extractor_effect", MineralExtractorEffect.CODEC);
	public static MapCodec<EchoShearEffect> ECHO_SHEAR_EFFECT = register("echo_shear_effect", EchoShearEffect.CODEC); // Echo Shear Codec

	private static RegistryKey<Enchantment> of(String path) {
		Identifier id = Identifier.of(Opsheeps.MOD_ID, path);
		return RegistryKey.of(RegistryKeys.ENCHANTMENT, id);
	}

	private static <T extends EnchantmentEntityEffect> MapCodec<T> register(String id, MapCodec<T> codec) {
		return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Identifier.of(Opsheeps.MOD_ID, id), codec);
	}

	public static void registerModEnchantmentEffects() {
		Opsheeps.LOGGER.info("🐑 REGISTERING MOD ENCHANTMENTS");
	}
}
