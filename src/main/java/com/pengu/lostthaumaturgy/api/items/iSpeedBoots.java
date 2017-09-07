package com.pengu.lostthaumaturgy.api.items;

import net.minecraft.item.ItemStack;

public interface iSpeedBoots
{
	float getWalkBoost(ItemStack boots);
	
	float getStepAssist(ItemStack boots);
	
	float getJumpMod(ItemStack boots);
}