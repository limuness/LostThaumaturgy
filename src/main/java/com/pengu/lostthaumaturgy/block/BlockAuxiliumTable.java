package com.pengu.lostthaumaturgy.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.mrdimka.hammercore.api.ITileBlock;
import com.mrdimka.hammercore.common.utils.WorldUtil;
import com.mrdimka.hammercore.gui.GuiManager;
import com.mrdimka.hammercore.tile.TileSyncable;
import com.pengu.lostthaumaturgy.LTInfo;
import com.pengu.lostthaumaturgy.block.def.BlockRendered;
import com.pengu.lostthaumaturgy.tile.TileAuxiliumTable;

public class BlockAuxiliumTable extends BlockRendered implements ITileBlock<TileAuxiliumTable>, ITileEntityProvider
{
	public static final AxisAlignedBB STUDIUM_AABB = new AxisAlignedBB(0, 0, 0, 1, 11 / 16D, 1);
	
	public BlockAuxiliumTable()
	{
		super(Material.IRON);
		setUnlocalizedName("auxilium_table");
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileAuxiliumTable();
	}
	
	@Override
	public Class<TileAuxiliumTable> getTileClass()
	{
		return TileAuxiliumTable.class;
	}
	
	@Override
	public String getParticleSprite(World world, BlockPos pos)
	{
		return LTInfo.MOD_ID + ":blocks/auxilium_table/top";
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return STUDIUM_AABB;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}
	
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public boolean isPassable(IBlockAccess worldIn, BlockPos pos)
	{
		return true;
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state)
	{
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}
	
	@Override
	public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer)
	{
		return false;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		GuiManager.openGui(playerIn, WorldUtil.cast(worldIn.getTileEntity(pos), TileSyncable.class));
		return true;
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		TileAuxiliumTable tile = WorldUtil.cast(worldIn.getTileEntity(pos), TileAuxiliumTable.class);
		if(tile != null) tile.inventory.drop(worldIn, pos);
		super.breakBlock(worldIn, pos, state);
	}
}