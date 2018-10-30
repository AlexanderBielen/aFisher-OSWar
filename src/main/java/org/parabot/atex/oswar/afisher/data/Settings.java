package org.parabot.atex.oswar.afisher.data;

public class Settings {
    private Fish selectedFish;
    private boolean powerlevel;
    private boolean autoprogress;

    public Settings(Fish selectedFish, boolean powerlevel, boolean autoprogress) {
        this.selectedFish = selectedFish;
        this.powerlevel = powerlevel;
        this.autoprogress = autoprogress;
    }

    /**
     * Gets selectedFish
     *
     * @return value of selectedFish
     */
    public Fish getSelectedFish() {
        return selectedFish;
    }

    /**
     * Gets powerlevel
     *
     * @return value of powerlevel
     */
    public boolean isPowerlevelEnabled() {
        return powerlevel;
    }

    /**
     * Gets autoprogress
     *
     * @return value of autoprogress
     */
    public boolean isAutoprogressEnabled() {
        return autoprogress;
    }

    /**
     * Sets selectedFish
     *
     * @param selectedFish the selectedFish to set
     */
    public void setSelectedFish(Fish selectedFish) {
        this.selectedFish = selectedFish;
    }
}