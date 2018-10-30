package org.parabot.atex.oswar.afisher.strategies;

import org.parabot.atex.oswar.afisher.core.Core;
import org.parabot.atex.oswar.afisher.data.Fish;
import org.parabot.atex.oswar.afisher.data.Methods;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.methods.Skill;
import org.rev317.min.api.wrappers.Npc;

import static org.rev317.min.api.methods.Players.getMyPlayer;

public class CatchFish implements Strategy {

    @Override
    public boolean activate() {
        return getMyPlayer().getAnimation() == -1
                && Npcs.getClosest(Core.getSettings().getSelectedFish().getFishingSpot().getId()) != null;
    }

    @Override
    public void execute() {
        if(Core.getSettings().isAutoprogressEnabled()) {
            Methods.selectAutoProgressFish();
        }

        Npc spot = Npcs.getClosest(Core.getSettings().getSelectedFish().getFishingSpot().getId());
        if(spot != null) {
            spot.interact(Core.getSettings().getSelectedFish().getOption());
        }

        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return getMyPlayer().getAnimation() != -1;
            }
        }, 2000);
    }
}
