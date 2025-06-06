package com.flxholle.quicktiles.abstract_tiles;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.service.quicksettings.Tile;

import com.flxholle.quicktiles.R;
import com.flxholle.quicktiles.utils.SelectApp;
import com.flxholle.quicktiles.utils.SharedPreferencesUtil;

public abstract class OpenCustomAppTileService extends IntentTileService {

    @Override
    public void onTileAdded() {
        updateState();
    }

    @Override
    public void onStartListening() {
        updateState();
    }

    @Override
    public Intent createIntent() {
        String packageName = SharedPreferencesUtil.getCustomPackage(this, getPreferencesKey());
        if (packageName != null)
            return getPackageManager().getLaunchIntentForPackage(packageName);
        else {
            showDialog(SelectApp.selectApps(this, getPreferencesKey()));
            return getPackageManager().getLaunchIntentForPackage(getApplicationInfo().packageName);
        }
    }

    private void updateState() {
        Tile tile = getQsTile();
        String packageName = SharedPreferencesUtil.getCustomPackage(this, getPreferencesKey());
        if (packageName != null) {
            ApplicationInfo selectedApp = SelectApp.getApplicationInfo(this, packageName);
            tile.setLabel(selectedApp.loadLabel(getPackageManager()));
        } else {
            tile.setLabel(getString(R.string.custom_app));
        }
        tile.updateTile();
    }

    public abstract String getPreferencesKey();
}
