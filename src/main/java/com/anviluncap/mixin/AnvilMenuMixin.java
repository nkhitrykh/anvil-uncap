package com.anviluncap.mixin;

import com.anviluncap.AnvilUncapConfig;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

// Untyped by design: fires for every enchantment in vanilla's merge loop, vanilla or modded.
@Mixin(AnvilMenu.class)
public abstract class AnvilMenuMixin {

	// @ModifyExpressionValue (not @Redirect) so other mods can target this call too.
	@ModifyExpressionValue(
			method = "createResult",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/item/enchantment/Enchantment;getMaxLevel()I"
			)
	)
	private int anviluncap$uncapMaxLevel(int original) {
		return AnvilUncapConfig.INSTANCE.effectiveCap();
	}
}
