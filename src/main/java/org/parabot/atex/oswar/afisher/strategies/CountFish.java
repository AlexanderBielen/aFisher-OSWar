package org.parabot.atex.oswar.afisher.strategies;

import org.parabot.atex.oswar.afisher.core.Core;
import org.parabot.atex.oswar.afisher.data.Fish;
import org.parabot.atex.oswar.afisher.ui.Overlay;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;

public class CountFish implements Strategy {
    private int previousCount = -1;
    @Override
    public boolean activate() {
        return !Core.getSettings().isPowerlevelEnabled();
    }

    @Override
    public void execute() {
        int count = Inventory.getCount();
        if(Core.getSettings().getSelectedFish() == Fish.KARAMBWANJI) {
            count = Inventory.getCount(true);
        }
        for(int i : Core.getSettings().getSelectedFish().getToolIds()) {
            count -= Inventory.getCount(i);
        }
        if(count == 0) {
            previousCount = 0;
        }
        if(previousCount == -1) {
            previousCount = count;
        }
        if(previousCount < count) {
            Overlay.incrementFish(count - previousCount);
            previousCount = count;
        }
        Time.sleep(100);
    }
}
