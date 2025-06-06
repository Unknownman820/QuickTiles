/*
 * Copyright (C) 2017 Adrián García
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.flxholle.quicktiles.tiles

import android.graphics.drawable.Icon
import com.flxholle.quicktiles.R
import com.flxholle.quicktiles.abstract_tiles.WriteSecureSettingsTileService
import com.flxholle.quicktiles.utils.WriteSystemSettingsUtils

class HeadsUpTileService : WriteSecureSettingsTileService<Int>() {
    companion object {
        const val SETTING = "heads_up_notifications_enabled"
    }

    override fun isActive(value: Int): Boolean {
        return value != 0
    }

    override fun queryValue(): Int {
        return WriteSystemSettingsUtils.getIntFromGlobalSettings(contentResolver, SETTING)
    }

    override fun reset() {
        saveValue(1)
    }

    override fun saveValue(value: Int): Boolean {
        return WriteSystemSettingsUtils.setIntToGlobalSettings(contentResolver, SETTING, value)
    }

    override fun getValueList(): List<Int> {
        return listOf(0, 1)
    }

    override fun getIcon(value: Int): Icon {
        val iconResource =
            if (value == 1)
                R.drawable.ic_heads_up
            else
                R.drawable.ic_heads_up_off

        return Icon.createWithResource(applicationContext, iconResource)
    }

    override fun updateLabel(): Boolean {
        return false
    }

    override fun getLabel(value: Int): CharSequence {
        return getString(R.string.heads_up)
    }
}