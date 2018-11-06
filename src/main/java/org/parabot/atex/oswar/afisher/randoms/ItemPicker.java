package org.parabot.atex.oswar.afisher.randoms;

import org.parabot.core.Core;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Interfaces;
import org.rev317.min.api.methods.Items;
import org.rev317.min.api.methods.Menu;

public class ItemPicker implements Strategy {
    private final int INTERFACE_ID = 33300;

    @Override
    public boolean activate() {
        return Game.isLoggedIn()
                && Interfaces.getOpenInterfaceId() == INTERFACE_ID
                && Interfaces.getInterface(INTERFACE_ID + 3).getMessage() != null
                && !Interfaces.getInterface(INTERFACE_ID + 3).getMessage().contains("0:00");
    }

    @Override
    public void execute() {
        Core.verbose("Item picker random event activated");
        Time.sleep(2000);

        String message = Interfaces.getInterface(INTERFACE_ID + 2).getMessage();
        String itemName = message.substring(message.indexOf("'") + 1, message.indexOf("'", message.indexOf("'") + 1));

        Core.verbose("Have to select item: "+itemName);

        for(int i = INTERFACE_ID + 11; i <= INTERFACE_ID + 17; i += 3) {
            try {
                int id = Interfaces.getInterface(i).getItems()[0];
                String name = Items.getName(id);

                if(name != null && name.toLowerCase().startsWith(itemName.toLowerCase())) {
                    Core.verbose("Attempting solution: "+name);
                    Menu.sendAction(315,0,0,i+1,2);
                    break;
                }
            } catch(Exception ignore) {}
        }
        Time.sleep(1000);
    }
}
