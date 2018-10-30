package org.parabot.atex.oswar.afisher.ui;

import org.parabot.atex.oswar.afisher.data.Constants;
import org.parabot.atex.oswar.afisher.data.Methods;
import org.rev317.min.api.methods.Skill;

import java.awt.*;

public class Overlay {
    private static int totalFish = 0;
    private static int startXp = 0;

    //START: Code generated using Enfilade's Easel
    private final Color color1 = new Color(0, 0, 39);
    private final Color color3 = new Color(216, 255, 102);

    private final BasicStroke stroke1 = new BasicStroke(1);

    private final Font font1 = new Font("Arial", 0, 12);
    private final Font font2 = new Font("Arial", 1, 13);

    public void onRepaint(Graphics g1) {
        Graphics2D g = (Graphics2D)g1;
        g.setColor(color1);
        g.setStroke(stroke1);
        g.fillRect(2, 2, 176, 78);
        g.setFont(font1);
        g.setColor(color3);
        g.drawString("Runtime: ", 9, 39);
        g.setFont(font2);
        g.drawString("aFisher - OSWar", 9, 18);
        g.setFont(font1);
        g.drawString("Total fish (/h):", 9, 57);
        g.drawString("Xp gained (/h):", 9, 73);
        g.drawString(Methods.getRunTime(Constants.START_TIME), 64, 39);
        g.drawString(totalFish+"("+Methods.getPerHour(totalFish, 0, Constants.START_TIME)+")", 89, 57);
        g.drawString((Skill.FISHING.getExperience() - startXp) / 1000 + "k ("+Methods.getPerHour(Skill.FISHING.getExperience(),startXp, Constants.START_TIME)/1000+"k)", 95, 73);
    }
    //END: Code generated using Enfilade's Easel

    public void setStartXp(int xp) {
        startXp = xp;
    }

    public static void incrementFish(int i) {
        totalFish += i;
    }
}
