package org.parabot.atex.oswar.afisher.strategies;

import org.parabot.atex.oswar.afisher.core.Core;
import org.parabot.atex.oswar.afisher.ui.Overlay;
import org.parabot.environment.api.utils.Filter;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.wrappers.Item;

public class PowerDrop implements Strategy {
    @Override
    public boolean activate() {
        return Core.getSettings().isPowerlevelEnabled()
                && Inventory.getCount() > Core.getSettings().getSelectedFish().getToolIds().length;
    }

    @Override
    public void execute() {
        Item[] items = Inventory.getItems(new Filter<Item>() {
            @Override
            public boolean accept(Item item) {
                for(int i : Core.getSettings().getSelectedFish().getToolIds()) {
                    if(item.getId() == i) {
                        return false;
                    }
                }
                return true;
            }
        });

        for(Item i : items) {
            Overlay.incrementFish(1);
            i.drop();
            Time.sleep(500);
        }
    }
}
