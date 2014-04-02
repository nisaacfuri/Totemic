package totemic_commons.pokefenn;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import totemic_commons.pokefenn.lib.Strings;
import totemic_commons.pokefenn.tileentity.*;

public class CommonProxy implements IGuiHandler
{

    public static int totempediaGuiID = -1;

    public void registerTileEntities()
    {

        GameRegistry.registerTileEntity(TileChlorophyllSolidifier.class, Strings.TILE_CHLOROPHYLL_SOLIDIFIER);
        //GameRegistry.registerTileEntity(TileTotemTable.class, Strings.TILE_TOTEM_TABLE);
        GameRegistry.registerTileEntity(TileTotemIntelligence.class, Strings.TILE_TOTEM_INTELLIGENCE);
        GameRegistry.registerTileEntity(TileTotemSocket.class, Strings.TILE_TOTEM_SOCKET);
        GameRegistry.registerTileEntity(TileTotemCauldron.class, Strings.TOTEM_CAULDRON_NAME);
        GameRegistry.registerTileEntity(TileCeremonyIntelligence.class, Strings.CEREMONY_INTELLIGENCE_NAME);
        GameRegistry.registerTileEntity(TileTotemTorch.class, Strings.TOTEM_TORCH_NAME);

        if (Totemic.botaniaLoaded)
            GameRegistry.registerTileEntity(TileTotemMana.class, Strings.TILE_TOTEM_MANA);

    }


    public void handleTileEntityPacket(int x, int y, int z, ForgeDirection orientation, byte state, String customName)
    {

    }


    public void handleTileWithItemPacket(int x, int y, int z, ForgeDirection orientation, byte state, String customName, String itemName, int metaData, int stackSize)
    {

    }

    public void handleChlorophyllSolidifierPacket(int x, int y, int z, ForgeDirection orientation, byte state, String customName, int itemID, int metaData, int stackSize)
    {
        //System.out.println("packet");

    }

    public void handleTileWithItemAndFluidPacket(int x, int y, int z, ForgeDirection orientation, byte state, String customName, String itemName, int metaData, int stackSize, int fluidAmount, byte fluidID)
    {

    }


    public void initRendering()
    {

    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        return null;
    }

    public void readManuals()
    {

    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        return null;
    }
}
