package totemic_commons.pokefenn.ceremony;

import totemic_commons.pokefenn.api.ceremony.ICeremonyEffect;
import totemic_commons.pokefenn.tileentity.TileCeremonyIntelligence;

/**
 * Created by Pokefenn.
 * Licensed under MIT (If this is one of my Mods)
 */
public class CeremonyTest implements ICeremonyEffect
{
    @Override
    public void effect(TileCeremonyIntelligence tileCeremonyIntelligence)
    {
        System.out.println("foobar");
    }
}
