package totemic_commons.pokefenn.block.music;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import totemic_commons.pokefenn.ModSounds;
import totemic_commons.pokefenn.Totemic;
import totemic_commons.pokefenn.lib.Strings;
import totemic_commons.pokefenn.recipe.HandlerInitiation;
import totemic_commons.pokefenn.tileentity.music.TileWindChime;
import totemic_commons.pokefenn.util.TotemUtil;

public class BlockWindChime extends Block implements ITileEntityProvider
{
    public BlockWindChime()
    {
        super(Material.IRON);
        setRegistryName(Strings.WIND_CHIME_NAME);
        setUnlocalizedName(Strings.WIND_CHIME_NAME);
        setHardness(1.5F);
        setCreativeTab(Totemic.tabsTotem);
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block neighbor)
    {
        breakStuffs(world, pos);
    }

    @Override
    public boolean canPlaceBlockAt(World world, BlockPos pos)
    {
        IBlockState upState = world.getBlockState(pos.up());
        return world.isAirBlock(pos.down())
                && (world.isSideSolid(pos.up(), EnumFacing.DOWN) || upState.getBlock().isLeaves(upState, world, pos.up()));
    }

    public void breakStuffs(World world, BlockPos pos)
    {
        if(!world.isRemote)
        {
            if(!canPlaceBlockAt(world, pos))
            {
                world.setBlockToAir(pos);
                spawnAsEntity(world, pos, new ItemStack(this));
            }
        }
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
            ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        TileWindChime tileWindChime = (TileWindChime) world.getTileEntity(pos);

        if(!world.isRemote && player.isSneaking())
        {
            tileWindChime.canPlay = false;
            TotemUtil.playSound(world, pos, ModSounds.windChime, SoundCategory.PLAYERS, 1.0f, 1.0f);
            TotemUtil.playMusicForSelector(world, pos, HandlerInitiation.windChime, 0);
            ((WorldServer) world).spawnParticle(EnumParticleTypes.NOTE, pos.getX() + 0.5, pos.getY() - 0.5, pos.getZ() + 0.5, 6, 0.0, 0.0, 0.0, 0.0);
            ((WorldServer) world).spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, pos.getX() + 0.5, pos.getY() - 0.5, pos.getZ() + 0.5, 6, 0.0, 0.0, 0.0, 0.0);
        }
        return true;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return new AxisAlignedBB(0.2F, 0.0F, 0.2F, 0.8F, 1F, 0.8F);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileWindChime();
    }
}
