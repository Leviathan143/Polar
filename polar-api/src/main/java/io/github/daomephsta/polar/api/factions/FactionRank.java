package io.github.daomephsta.polar.api.factions;

import java.util.Comparator;

import io.github.daomephsta.polar.api.PolarApi;

/**
 * Represents the rank of a player or NPC within a faction. Unaligned players always have the rank NONE. 
 * Each faction has unique names for each rank, though the ranks are the same internally.
**/
public enum FactionRank
{
    NONE(0),
    INITIATE(1),
    APPRENTICE(2),
    JOURNEYMAN(3),
    MASTER(4);
    
    public static final Comparator<FactionRank> COMPARATOR = Comparator.comparingInt(rank -> rank.rankIndex);
    //Used to compare ranks, can change
    private final int rankIndex;
    
    private FactionRank(int rankIndex)
    {
        this.rankIndex = rankIndex;
    }
    
    /**
     * @param alignment The faction to localise the rank for
     * @return A string key to be used to localise the name of the rank for a particular faction
     **/
    public String getLangKey(FactionAlignment alignment)
    {
        if(alignment == FactionAlignment.UNALIGNED) return "";
        return PolarApi.PROVIDER_MOD_ID + ".rank." + alignment.name() + "_" + this.name();
    }
}
