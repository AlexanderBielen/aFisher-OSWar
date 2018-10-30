package org.parabot.atex.oswar.afisher.randoms;

import org.parabot.core.ui.Logger;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Interfaces;

public class PickItem implements Strategy {
    @Override
    public boolean activate() {
        return Interfaces.getOpenInterfaceId() == 33300;
    }

    @Override
    public void execute() {
        //TODO solve random event
        Logger.addMessage("Randoms window is open!");
        Logger.addMessage(Interfaces.getInterface(33300).getMessage());
        Time.sleep(5000);
    }
}