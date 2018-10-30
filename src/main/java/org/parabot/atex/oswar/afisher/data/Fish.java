package org.parabot.atex.oswar.afisher.data;

import org.rev317.min.api.methods.Npcs;

public enum Fish {
    SHRIMP("Shrimp + anchovi", 1, new int[]{304}, FishingSpot.SMALL_NET_BAIT, Npcs.Option.NET),
    KARAMBWANJI("karambwanji", 5, new int[]{304}, FishingSpot.NET, Npcs.Option.NET),
    SARDINE("Sardine + herring", 5, new int[]{308, 314}, FishingSpot.SMALL_NET_BAIT, Npcs.Option.THIRD),
    TROUT("Trout + salmon", 20, new int[]{310, 315}, FishingSpot.LURE_BAIT, Npcs.Option.BAIT),
    PIKE("Pike", 25, new int[]{310, 315}, FishingSpot.LURE_BAIT, Npcs.Option.THIRD),
    TUNA("Tuna + swordfish", 35, new int[]{312}, FishingSpot.CAGE_HARPOON, Npcs.Option.THIRD),
    LOBSTER("Lobster", 40, new int[]{302}, FishingSpot.CAGE_HARPOON, Npcs.Option.BAIT),
    MONKFISH("Monkfish", 62, new int[]{304}, FishingSpot.BIG_NET_HARPOON, Npcs.Option.NET),
    SHARK("Shark", 76, new int[]{312}, FishingSpot.BIG_NET_HARPOON, Npcs.Option.THIRD),
    MANTA_RAY("Manta ray + sea turtle", 79, new int[]{306}, FishingSpot.BIG_NET_BAIT, Npcs.Option.BAIT);

    private String name;
    private int level;
    private int[] toolIds;
    private FishingSpot fishingSpot;
    private Npcs.Option option;

    Fish(String name, int level, int[] toolIds, FishingSpot fishingSpot, Npcs.Option option) {
        this.name = name;
        this.level = level;
        this.toolIds = toolIds;
        this.fishingSpot = fishingSpot;
        this.option = option;
    }

    /**
     * Gets name
     *
     * @return value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets level
     *
     * @return value of level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Gets toolId
     *
     * @return value of toolId
     */
    public int[] getToolIds() {
        return toolIds;
    }

    /**
     * Gets fishingSpot
     *
     * @return value of fishingSpot
     */
    public FishingSpot getFishingSpot() {
        return fishingSpot;
    }

    /**
     * Gets option
     *
     * @return value of option
     */
    public Npcs.Option getOption() {
        return option;
    }

    @Override
    public String toString() {
        return name+" (level "+level+")";
    }
}
