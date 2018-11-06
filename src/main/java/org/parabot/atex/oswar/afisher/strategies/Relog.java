package org.parabot.atex.oswar.afisher.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Game;

import java.awt.event.KeyEvent;

public class Relog implements Strategy {
    @Override
    public boolean activate() {
        return !Game.isLoggedIn();
    }

    @Override
    public void execute() {
        Time.sleep(3000);
        Keyboard.getInstance().clickKey(KeyEvent.VK_ENTER);
        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Game.isLoggedIn();
            }
        }, 6000);
        Time.sleep(5000);
    }
}
