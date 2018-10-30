package org.parabot.atex.oswar.afisher.data;

public enum FishingSpot {
    SMALL_NET_BAIT(3913),
    LURE_BAIT(3417),
    CAGE_HARPOON(3657),
    NET(3317),
    BIG_NET_BAIT(1524),
    BIG_NET_HARPOON(1520);

    private int id;
    FishingSpot(int id) {
        this.id = id;
    }

    /**
     * Gets id
     *
     * @return value of id
     */
    public int getId() {
        return id;
    }
}
