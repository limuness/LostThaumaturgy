package com.pengu.lostthaumaturgy.core.items.armor;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.pengu.lostthaumaturgy.core.Info;
import com.pengu.lostthaumaturgy.custom.aura.AtmosphereChunk;
import com.pengu.lostthaumaturgy.custom.aura.AtmosphereTicker;
import com.pengu.lostthaumaturgy.init.ItemMaterialsLT;

public class ItemVoidArmor extends ItemArmor
{
	public ItemVoidArmor(EntityEquipmentSlot equipmentSlotIn)
	{
		super(ItemMaterialsLT.armor_void, 2, equipmentSlotIn);
		if(equipmentSlotIn == EntityEquipmentSlot.HEAD)
			setUnlocalizedName("void_helmet");
		if(equipmentSlotIn == EntityEquipmentSlot.CHEST)
			setUnlocalizedName("void_chestplate");
		if(equipmentSlotIn == EntityEquipmentSlot.LEGS)
			setUnlocalizedName("void_leggings");
		if(equipmentSlotIn == EntityEquipmentSlot.FEET)
			setUnlocalizedName("void_boots");
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
	{
		AtmosphereChunk si = AtmosphereTicker.getAuraChunkFromBlockCoords(world, player.getPosition());
		if(itemStack.getItemDamage() > 0 && player.ticksExisted % 60 == 0)
		{
			itemStack.setItemDamage(itemStack.getItemDamage() - 1);
			++si.badVibes;
			--si.vis;
		}
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
	{
		return Info.MOD_ID + ":textures/armor/void_" + (slot == EntityEquipmentSlot.LEGS ? 2 : 1) + ".png";
	}
}