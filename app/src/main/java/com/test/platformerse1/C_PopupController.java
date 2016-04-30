package com.test.platformerse1;

import java.util.List;

// Author: Isaiah Thacker
// Last Modified: 4/30/16 by Isaiah Thacker
// Iteration 4
// C_PopupController handles the update methods associated with the popup triggers: specifically,
// causing the popups to appear if the player character is intersecting one.
public class C_PopupController {
    private static M_Character player = M_Character.getInstance();
    private static List<M_PopupTrigger> popups = M_Environment.getInstance().getPopups();

    public static void updatePopups() {
        // iterate through the popup triggers
        for (int i = 0; i < popups.size(); ++i) {
            // if the player has hit the latest popup trigger
            M_PopupTrigger tempTrigger = popups.get(i);
            if (C_EnvironmentController.boxIntersect(tempTrigger.getLocation(), tempTrigger.getDimensions(), player.getLocation(), player.getDimensions())) {
                // then display its message and break
                popup(tempTrigger);
                // remove it from the list
                popups.remove(i);
                --i;
                break;
            }
        }
    }

    // popup(trigger) pauses the game environment and sets the popup associated with trigger
    // to appear on the screen.
    public static void popup(M_PopupTrigger trigger) {
        M_Environment environment = M_Environment.getInstance();
        // pause the environment
        C_EnvironmentController.pauseGame();
        // set the popup title
        environment.setPopupTitle(trigger.getTitle());
        // set the popup message
        environment.setPopupText(trigger.getMessage());
        // set the popup to display
        environment.setShowingPopup(true);
    }
}
