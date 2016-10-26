package totemic_commons.pokefenn.block.totem;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import totemic_commons.pokefenn.Totemic;
import totemic_commons.pokefenn.api.TotemicStaffUsage;
import totemic_commons.pokefenn.block.BlockTileTotemic;
import totemic_commons.pokefenn.lib.Strings;
import totemic_commons.pokefenn.lib.WoodVariant;
import totemic_commons.pokefenn.tileentity.totem.TileTotemBase;
import totemic_commons.pokefenn.tileentity.totem.TileTotemPole;

/**
 * Created with IntelliJ IDEA.
 * User: Pokefenn
 * Date: 02/02/14
 * Time: 13:03
 */
public class BlockTotemPole extends BlockTileTotemic implements TotemicStaffUsage
{
    public static final PropertyEnum<WoodVariant> WOOD = PropertyEnum.create("wood", WoodVariant.class);

    public BlockTotemPole()
    {
        super(Material.wood);
        setUnlocalizedName(Strings.TOTEM_POLE_NAME);
        setCreativeTab(Totemic.tabsTotem);
        setBlockBounds(0.1875F, 0.0F, 0.1875F, 0.8125F, 1.0F, 0.8125F);
        setStepSound(soundTypeWood);
    }

    @Override
    public boolean onTotemicStaffRightClick(World world, BlockPos pos, EntityPlayer player, ItemStack itemStack)
    {
        if(world.isRemote)
        {
            TileTotemPole tileTotemSocket = (TileTotemPole) world.getTileEntity(pos);
            if(tileTotemSocket.getEffect() != null)
            {
                player.addChatComponentMessage(new ChatComponentTranslation("totemicmisc.activeEffect", StatCollector.translateToLocal(tileTotemSocket.getEffect().getUnlocalizedName())));
            }
        }
        return true;
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entityLiving, ItemStack itemStack)
    {
        super.onBlockPlacedBy(world, pos, state, entityLiving, itemStack);
        if(!world.isRemote)
            notifyTotemBase(world, pos);
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state)
    {
        super.breakBlock(world, pos, state);
        if(!world.isRemote)
            notifyTotemBase(world, pos);
    }

    private void notifyTotemBase(World world, BlockPos pos)
    {
        for(int i = 0; i < TileTotemBase.MAX_HEIGHT; i++)
        {
            Block block = world.getBlockState(pos.down(i + 1)).getBlock();
            if(block instanceof BlockTotemBase)
            {
                ((TileTotemBase) world.getTileEntity(pos.down(i + 1))).onPoleChange();
                break;
            }
            else if(!(block instanceof BlockTotemPole))
                break;
        }
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return getMetaFromState(state);
    }

    @Override
    public int quantityDropped(Random rand)
    {
        return 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list)
    {
        for(int i = 0; i < WoodVariant.values().length; i++)
            list.add(new ItemStack(item, 1, i));
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean isFullCube()
    {
        return false;
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, WOOD);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(WOOD).ordinal();
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return getDefaultState().withProperty(WOOD, WoodVariant.values()[meta]);
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2)
    {
        return new TileTotemPole();
    }
}
