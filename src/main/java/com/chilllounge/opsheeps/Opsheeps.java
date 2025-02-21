package com.chilllounge.opsheeps;

import com.chilllounge.opsheeps.item.ModItemGroups;
import com.chilllounge.opsheeps.item.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Opsheeps implements ModInitializer {
	public static final String MOD_ID = "opsheeps";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
	}
}