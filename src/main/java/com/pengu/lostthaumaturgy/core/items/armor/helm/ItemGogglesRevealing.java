package com.pengu.lostthaumaturgy.core.items.armor.helm;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

import com.pengu.lostthaumaturgy.api.items.iGoggles;
import com.pengu.lostthaumaturgy.core.Info;
import com.pengu.lostthaumaturgy.init.ItemMaterialsLT;

public class ItemGogglesRevealing extends ItemArmor implements iGoggles
{
	public ItemGogglesRevealing()
	{
		super(ItemMaterialsLT.armor_goggles, 2, EntityEquipmentSlot.HEAD);
		setUnlocalizedName("goggles_revealing");
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
	{
		return Info.MOD_ID + ":textures/armor/goggles.png";
	}
	
	@Override
	public int getRevealType()
	{
		return 2;
	}
	
	@Override
	public boolean canReveal(EntityPlayer player)
	{
		return true;
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack)
	{
		return EnumRarity.RARE;
	}
	
	public static iGoggles getWearing(EntityPlayer player)
	{
		ItemStack stack = player.inventory.armorInventory.get(3);
		if(stack.getItem() instanceof iGoggles)
		{
			iGoggles g = (iGoggles) stack.getItem();
			if(g.canReveal(player))
				return g;
		}
		return null;
	}
}