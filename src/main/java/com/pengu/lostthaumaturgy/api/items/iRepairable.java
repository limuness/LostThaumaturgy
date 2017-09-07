package com.pengu.lostthaumaturgy.api.items;

import net.minecraft.item.ItemStack;

import com.pengu.hammercore.utils.WorldLocation;

public interface iRepairable
{
	float getRestoreCost(ItemStack stack, WorldLocation loc);
}