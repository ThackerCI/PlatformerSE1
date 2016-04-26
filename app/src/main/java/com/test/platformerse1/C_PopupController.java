package com.test.platformerse1;

import java.util.List;

// Author: Isaiah Thacker
// Last Modified: 4/25/16 by Isaiah Thacker
// Iteration 4
// C_PopupController handles the update methods associated with the popup triggers: specifically,
// causing the popups to appear if the player character is intersecting one.
public class C_PopupController {
    private static M_Character player = M_Character.getInstance();
    private static List<M_PopupTrigger> popups = M_Environment.getInstance().getPopups();

    public static void updatePopups(){
        // iterate through the popup triggers
        for (int i = 0; i < popups.size(); ++i){
            // if the player has hit the latest popup trigger
            M_PopupTrigger tempTrigger = popups.get(i);
            if (C_EnvironmentController.boxIntersect(tempTrigger.getLocation(), tempTrigger.getDimensions(), player.getLocation(), player.getDimensions())) {
                // then display its message and break
                tempTrigger.popup();
                // remove it from the list
                popups.remove(i);
                --i;
                break;
            }
        }
    }
}
