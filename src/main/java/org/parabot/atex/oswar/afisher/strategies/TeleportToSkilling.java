package org.parabot.atex.oswar.afisher.strategies;

import org.parabot.atex.oswar.afisher.core.Core;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Npcs;

public class TeleportToSkilling implements Strategy {
    @Override
    public boolean activate() {
        try {
            return Npcs.getClosest(Core.getSettings().getSelectedFish().getFishingSpot().getId()) == null;
        } catch (Exception ignore) { }
        return false;
    }

    @Override
    public void execute() {
        Keyboard.getInstance().sendKeys("::skilling");
        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Npcs.getClosest(Core.getSettings().getSelectedFish().getFishingSpot().getId()) != null;
            }
        }, 4000);
    }
}
