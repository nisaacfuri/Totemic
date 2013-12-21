package totemic_commons.pokefenn.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import hinalib_commons.pokefenn.item.ItemNormal;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import totemic_commons.pokefenn.Totemic;
import totemic_commons.pokefenn.lib.Strings;
import totemic_commons.pokefenn.lib.Textures;

import java.util.List;

public class ItemChlorophyllCrystal extends ItemNormal {
	
	
public ItemChlorophyllCrystal(int id){


	super(id);
    this.setUnlocalizedName(Strings.RESOURCE_PREFIX + Strings.CHLOROPHYLL_CRYSTAL_NAME);
    this.setMaxStackSize(1);
    this.setMaxDamage(500);
    this.setCreativeTab(Totemic.tabsTotem);
        
		
		
	}
	
	

	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister register) {
    	
    	itemIcon = register.registerIcon(Textures.TEXTURE_LOCATION + ":" + Textures.CHLOROPHYLL_CRYSTAL_ICON);       
    	
    	
    }
	
	@Override
    @SideOnly(Side.CLIENT)
    public void addInformation (ItemStack stack, EntityPlayer player, List list, boolean par4)
    {
        list.add("A crystal holding plant essence");
    }

}

