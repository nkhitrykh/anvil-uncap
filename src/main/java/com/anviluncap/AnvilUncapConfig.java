package com.anviluncap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class AnvilUncapConfig {
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static final Path PATH = FabricLoader.getInstance().getConfigDir().resolve("anviluncap.json");

	public static AnvilUncapConfig INSTANCE = new AnvilUncapConfig();

	/** Highest level an anvil combine may produce. 0 or lower disables the cap (default). */
	public int maxCombinedLevel = 0;

	public static void load() {
		if (Files.exists(PATH)) {
			try {
				AnvilUncapConfig loaded = GSON.fromJson(Files.readString(PATH), AnvilUncapConfig.class);
				INSTANCE = loaded != null ? loaded : new AnvilUncapConfig();
				return;
			} catch (IOException | JsonSyntaxException e) {
				AnvilUncapMod.LOGGER.warn("[Anvil Uncap] failed to read config, using defaults", e);
			}
		}
		save();
	}

	private static void save() {
		try {
			Files.createDirectories(PATH.getParent());
			Files.writeString(PATH, GSON.toJson(INSTANCE));
		} catch (IOException e) {
			AnvilUncapMod.LOGGER.warn("[Anvil Uncap] failed to write default config", e);
		}
	}

	public int effectiveCap() {
		return maxCombinedLevel <= 0 ? Integer.MAX_VALUE : maxCombinedLevel;
	}
}
