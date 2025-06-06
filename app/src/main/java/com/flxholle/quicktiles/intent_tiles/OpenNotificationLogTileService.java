package com.flxholle.quicktiles.intent_tiles;

import android.content.ComponentName;
import android.content.Intent;

import com.flxholle.quicktiles.abstract_tiles.IntentTileService;

public class OpenNotificationLogTileService extends IntentTileService {

    @Override
    public Intent createIntent() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings$NotificationStationActivity"));
        return intent;
    }
}
