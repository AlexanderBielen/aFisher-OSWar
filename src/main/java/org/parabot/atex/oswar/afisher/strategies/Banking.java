package org.parabot.atex.oswar.afisher.strategies;

import org.parabot.atex.oswar.afisher.core.Core;
import org.parabot.atex.oswar.afisher.data.Constants;
import org.parabot.atex.oswar.afisher.data.Methods;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Bank;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

public class Banking implements Strategy {
    @Override
    public boolean activate() {
        return (Inventory.isFull() || Bank.isOpen()) && !Core.getSettings().isPowerlevelEnabled();
    }

    @Override
    public void execute() {
        SceneObject bank = Methods.getBank();

        if(bank != null) {
            if (!Bank.isOpen()) {
                bank.interact(SceneObjects.Option.FIRST); // Bank.open(bank) doesn't work
                Time.sleep(new SleepCondition() {
                    @Override
                    public boolean isValid() {
                        return Bank.isOpen();
                    }
                }, 2000);

            }

            if (Bank.isOpen()) {

                if (Inventory.isFull()) {
                    Bank.depositAllExcept(Core.getSettings().getSelectedFish().getToolIds());
                    Time.sleep(new SleepCondition() {
                        @Override
                        public boolean isValid() {
                            return !Inventory.isFull();
                        }
                    }, 1000);
                }

                if (!Inventory.isFull()) {
                    Bank.close();
                    Time.sleep(new SleepCondition() {
                        @Override
                        public boolean isValid() {
                            return !Bank.isOpen();
                        }
                    }, 1000);
                }
            }
        }  else {
            Keyboard.getInstance().sendKeys("::skilling");
            Time.sleep(2000);

            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return SceneObjects.getClosest(Constants.BANK_ID) != null;
                }
            }, 4000);
        }
    }
}
