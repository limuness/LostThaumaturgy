package com.pengu.lostthaumaturgy.custom.aura;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pengu.hammercore.utils.IndexedMap;
import com.pengu.lostthaumaturgy.LTConfigs;

import net.minecraft.nbt.NBTTagByteArray;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.common.util.INBTSerializable;

public class ThaumosphereChunk implements Serializable, INBTSerializable<NBTTagCompound>
{
	public static final String VAR_DISPERSE = "LT_DisperseAura";
	
	public transient World world;
	public final IndexedMap<String, List<byte[]>> data = new IndexedMap<>();
	public short vis;
	public short taint;
	public short previousVis;
	public short previousTaint;
	public short goodVibes;
	public short badVibes;
	public short boost;
	public int x;
	public int z;
	public boolean updated;
	public int dimension;
	public short monolithVibes;
	public short monolithVibeCap = 0;
	public float radiation = 6.000F;
	public float previousRadiation = radiation;
	public float primordialNodeStrength = 0;
	public final Map<String, String> warded = new HashMap<>();
	
	public boolean isWarded(BlockPos pos)
	{
		String pos2 = pos.getX() + "|" + pos.getY() + "|" + pos.getZ();
		return warded.containsKey(pos2);
	}
	
	public String getWarder(BlockPos pos)
	{
		String pos2 = pos.getX() + "|" + pos.getY() + "|" + pos.getZ();
		return warded.get(pos2);
	}
	
	public UnwardResult removeWard(BlockPos pos)
	{
		String pos2 = pos.getX() + "|" + pos.getY() + "|" + pos.getZ();
		return new UnwardResult(pos, this, warded.remove(pos2));
	}
	
	public boolean ward(BlockPos pos, String warder)
	{
		String pos2 = pos.getX() + "|" + pos.getY() + "|" + pos.getZ();
		return warded.putIfAbsent(pos2, warder) == null;
	}
	
	public List<byte[]> getVar(String name)
	{
		List<byte[]> l = data.get(name);
		if(l == null)
			data.put(name, l = new ArrayList<>());
		return l;
	}
	
	@Override
	public NBTTagCompound serializeNBT()
	{
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setShort("vis", vis);
		nbt.setShort("taint", taint);
		nbt.setShort("previousVis", previousVis);
		nbt.setShort("previousTaint", previousTaint);
		nbt.setShort("goodVibes", goodVibes);
		nbt.setShort("badVibes", badVibes);
		nbt.setShort("boost", boost);
		nbt.setInteger("x", x);
		nbt.setInteger("z", z);
		nbt.setBoolean("updated", updated);
		nbt.setInteger("dimension", dimension);
		nbt.setFloat("radiation", radiation);
		nbt.setFloat("previousRadiation", previousRadiation);
		nbt.setFloat("primordialNodeLevel", primordialNodeStrength);
		
		NBTTagList list = new NBTTagList();
		
		for(int i = 0; i < data.size(); ++i)
		{
			NBTTagCompound tag = new NBTTagCompound();
			tag.setString("Name", data.getKey(i));
			NBTTagList l = new NBTTagList();
			for(byte[] v : data.getValue(i))
				l.appendTag(new NBTTagByteArray(v));
			tag.setTag("Vars", l);
			list.appendTag(tag);
		}
		
		nbt.setTag("Variables", list);
		
		return nbt;
	}
	
	@Override
	public void deserializeNBT(NBTTagCompound nbt)
	{
		vis = nbt.getShort("vis");
		taint = nbt.getShort("taint");
		previousVis = nbt.getShort("previousVis");
		previousTaint = nbt.getShort("previousTaint");
		goodVibes = nbt.getShort("goodVibes");
		badVibes = nbt.getShort("badVibes");
		boost = nbt.getShort("boost");
		x = nbt.getInteger("x");
		z = nbt.getInteger("z");
		updated = nbt.getBoolean("updated");
		dimension = nbt.getInteger("dimension");
		radiation = nbt.getFloat("radiation");
		previousRadiation = nbt.getFloat("previousRadiation");
		primordialNodeStrength = nbt.getFloat("primordialNodeLevel");
		
		NBTTagList list = nbt.getTagList("Variables", NBT.TAG_COMPOUND);
		
		for(int i = 0; i < list.tagCount(); ++i)
		{
			NBTTagCompound tag = list.getCompoundTagAt(i);
			List<byte[]> vars = new ArrayList<>();
			NBTTagList l = tag.getTagList("Vars", NBT.TAG_BYTE_ARRAY);
			for(int j = 0; j < l.tagCount(); ++j)
				vars.add(((NBTTagByteArray) l.get(j)).getByteArray());
			data.put(tag.getString("Name"), vars);
		}
	}
	
	@Override
	public int hashCode()
	{
		ArrayList list = new ArrayList<>();
		list.add(x);
		list.add(z);
		list.add(dimension);
		return list.hashCode();
	}
	
	public boolean isTainted()
	{
		return taint > LTConfigs.aura_max / 2;
	}
}