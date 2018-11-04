package org.parabot.atex.oswar.afisher.randoms;

import org.json.simple.JSONObject;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.api.utils.WebUtil;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Interfaces;
import org.rev317.min.api.methods.Menu;

public class PickItem implements Strategy {
    private final int INTERFACE_ID = 33300;
    private final String URL = "http://bdn.parabot.org/api/v2/data/items/";

    @Override
    public boolean activate() {
        return Interfaces.getOpenInterfaceId() == INTERFACE_ID;
    }

    @Override
    public void execute() {
        Time.sleep(2000);

        String itemName = Interfaces.getInterface(INTERFACE_ID + 2).getMessage();
        itemName = itemName.substring(itemName.indexOf("'") + 1);
        itemName = itemName.substring(0, itemName.indexOf("'"));

        for(int i = INTERFACE_ID + 11; i <= INTERFACE_ID + 17; i += 3) {
            try {
                int id = Interfaces.getInterface(i).getItems()[0];

                String response = WebUtil.getContents(URL.concat("" + id));
                JSONObject json = (JSONObject) WebUtil.getJsonParser().parse(response);
                JSONObject result = (JSONObject) WebUtil.getJsonParser().parse(json.get("result").toString());

                if(result.get("name").equals(itemName)) {
                    Menu.sendAction(315,0,0,i+1,2);
                    break;
                }
            } catch(Exception ignore) {}
        }
        Time.sleep(2000);
    }
}