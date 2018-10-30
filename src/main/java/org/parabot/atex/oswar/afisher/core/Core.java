package org.parabot.atex.oswar.afisher.core;

import org.parabot.atex.oswar.afisher.data.Methods;
import org.parabot.atex.oswar.afisher.data.Settings;
import org.parabot.atex.oswar.afisher.randoms.PickItem;
import org.parabot.atex.oswar.afisher.strategies.TeleportToSkilling;
import org.parabot.atex.oswar.afisher.strategies.*;
import org.parabot.atex.oswar.afisher.ui.Gui;
import org.parabot.atex.oswar.afisher.ui.Overlay;
import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Skill;

import java.awt.*;
import java.util.ArrayList;

@ScriptManifest(author = "Atex",
        category = Category.FISHING,
        description = "An AIO ::skilling fishing bot",
        name = "aFisher", servers = { "OSWar" },
        version = 0.1)
public class Core extends Script implements Paintable {
    private ArrayList<Strategy> strategies = new ArrayList<>();
    private Overlay overlay = new Overlay();
    private static Settings settings;
    private static Script core;

    @Override
    public boolean onExecute() {
        strategies.add(new PickItem());
        strategies.add(new WithdrawTools());
        strategies.add(new PowerDrop());
        strategies.add(new Banking());
        strategies.add(new CatchFish());

        strategies.add(new TeleportToSkilling());
        strategies.add(new CountFish());

        provide(strategies);

        Gui gui = new Gui();
        while(gui.isVisible()) {
            Time.sleep(100);
        }

        if(gui.getSettings() == null) {
            return false;
        }

        settings = gui.getSettings();

        if(settings.isAutoprogressEnabled()) {
            Methods.selectAutoProgressFish();
        }

        overlay.setStartXp(Skill.FISHING.getExperience());
        core = this;

        return true;
    }

    @Override
    public void paint(Graphics graphics) {
        overlay.onRepaint(graphics);
    }

    public static Settings getSettings() {
        return settings;
    }

    public static void stopScript() {
        core.setState(STATE_STOPPED);
    }
}
