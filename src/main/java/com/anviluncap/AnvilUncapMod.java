package com.anviluncap;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnvilUncapMod implements ModInitializer {
	public static final String MOD_ID = "anvil-uncap";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		AnvilUncapConfig.load();
		LOGGER.info("[Anvil Uncap] loaded - anvil-combined enchantment levels are no longer capped at the vanilla max.");
	}
}
