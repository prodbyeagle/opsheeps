package com.chilllounge.opsheeps.util;

import com.chilllounge.opsheeps.Opsheeps;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
	public static class Items {
		public static final TagKey<Item> SUPER_SHEARS = createTag("super_shears");

		private static TagKey<Item> createTag(String name) {
			return TagKey.of(RegistryKeys.ITEM, Identifier.of(Opsheeps.MOD_ID, name));
		}
	}
}