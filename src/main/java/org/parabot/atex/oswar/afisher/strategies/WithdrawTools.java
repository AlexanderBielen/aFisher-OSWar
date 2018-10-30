package org.parabot.atex.oswar.afisher.strategies;

import org.parabot.atex.oswar.afisher.core.Core;
import org.parabot.atex.oswar.afisher.data.Constants;
import org.parabot.atex.oswar.afisher.data.Methods;
import org.parabot.core.ui.Logger;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Bank;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

public class WithdrawTools implements Strategy {
    @Override
    public boolean activate() {
        for(int i : Core.getSettings().getSelectedFish().getToolIds()) {
            if(Inventory.getItem(i) == null) {
                return true;
            }
        }
        return false;
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
                Bank.depositAllExcept(0);

                Time.sleep(new SleepCondition() {
                    @Override
                    public boolean isValid() {
                        return Inventory.isEmpty();
                    }
                }, 2000);

                if (Inventory.isEmpty()) {
                    for(int i : Core.getSettings().getSelectedFish().getToolIds()) {
                        if(Bank.getCount(i) > 0) {
                            if (Constants.BAITS.contains(i)) {
                                Bank.withdraw(i, Bank.getCount(i), 1000);
                            } else {
                                Bank.withdraw(i, 1, 1000);
                            }
                            Time.sleep(100);
                        } else {
                            Logger.addMessage("Couldn't find tool with id " + i + " in bank.");
                            Core.stopScript();
                        }
                    }

                    Time.sleep(1000);
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
