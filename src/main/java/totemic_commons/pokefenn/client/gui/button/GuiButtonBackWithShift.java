/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [Mar 6, 2014, 6:35:32 PM (GMT)]
 */
package totemic_commons.pokefenn.client.gui.button;

import java.util.Arrays;
import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextFormatting;

public class GuiButtonBackWithShift extends GuiButtonBack
{
    public GuiButtonBackWithShift(int par1, int par2, int par3)
    {
        super(par1, par2, par3);
    }

    @Override
    public List<String> getTooltip()
    {
        return Arrays.asList(I18n.format("totemicmisc.back"), TextFormatting.GRAY + I18n.format("totemicmisc.clickToIndex"));
    }

}
