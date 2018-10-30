package org.parabot.atex.oswar.afisher.data;

import org.parabot.atex.oswar.afisher.core.Core;
import org.parabot.environment.api.utils.Filter;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.methods.Skill;
import org.rev317.min.api.wrappers.SceneObject;
import org.rev317.min.api.wrappers.Tile;

import java.text.DecimalFormat;

public class Methods {
    public static void selectAutoProgressFish() {
        int fishingLevel = Skill.FISHING.getRealLevel();

        for(int i = Fish.values().length - 1; i >= 0;i--) {
            Fish f;
            if((f = Fish.values()[i]).getLevel() < fishingLevel) {
                if(Core.getSettings().getSelectedFish() != f) {
                    Core.getSettings().setSelectedFish(f);
                }
                break;
            }
        }
    }

    public static SceneObject getBank() {
        SceneObject[] objects = SceneObjects.getNearest(new Filter<SceneObject>() {
            @Override
            public boolean accept(SceneObject sceneObject) {
                return sceneObject.getLocation().equals(new Tile(3029, 3379));
            }
        });

        if(objects.length > 0) {
            return objects[0];
        } else {
            return null;
        }
    }

    public static long getPerHour(int currentAmount, int startAmount, long start) {
        return (int)(((double)(currentAmount - startAmount) * 3600000D) / (double)(System.currentTimeMillis() - start));
    }

    public static String getRunTime(long start) {
        DecimalFormat df = new DecimalFormat("00");
        long currentTime = System.currentTimeMillis() - start;
        long hours = currentTime / (3600000);
        currentTime -= hours * (3600000);
        long minutes = currentTime / (60000);
        currentTime -= minutes * (60000);
        long seconds = currentTime / (1000);
        return df.format(hours) + ":" + df.format(minutes) + ":" + df.format(seconds);
    }
}
