/*
 * Copyright (C) 2020 The Dirty Unicorns Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pixeldust.themes;

import static com.pixeldust.themes.utils.Utils.threeButtonNavbarEnabled;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.util.PathParser;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.RecyclerView;

import com.pixeldust.themes.utils.ThemesListItem;

import java.io.File;
import java.util.List;

public class ThemesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private boolean mIsNightMode;
    private int mThemeNightColor;
    private int mThemeFont;
    private String mThemeWpBackup;
    private Resources mResources;
    private List<ThemesListItem> mThemesList;
    private RecyclerView.ViewHolder mViewHolder;

    public ThemesAdapter(Context context, List<ThemesListItem> themesList) {
        mContext = context;
        mResources = context.getResources();
        mThemesList = themesList;
    }

    class ViewHolderMain extends RecyclerView.ViewHolder {

        ImageView mWpBgMain;
        ImageView mQsAccentMainWifi, mQSTileMainWifiActive;
        ImageView mQsTileMainBgBluetoothInactive, mQsTileMainIconBluetoothInactive;
        ImageView mQsAccentMainDnd, mQsTileMainDndActive;
        ImageView mQsTileMainBgFlashlightInactive, mQsTileMainIconFlashlightInactive;
        ImageView mQsAccentMainAutorotate, mQsTileMainAutorotateActive;
        ImageView mQsTileMainBgBatterySaverInactive, mQsTileMainIconBatterySaverInactive;
        ImageView mViewNavbarMain;
        LinearLayout mLlBgMain;
        TextView mThemeMainName;
        View mViewSpacerMain;

        ViewHolderMain(View view) {
            super(view);
            mWpBgMain = view.findViewById(R.id.wp_bg_main);
            mLlBgMain = view.findViewById(R.id.ll_qs_bg_main);
            mQsAccentMainWifi = view.findViewById(R.id.qs_accent_main_wifi);
            mQSTileMainWifiActive = view.findViewById(R.id.qs_tile_main_wifi_active);
            mQsTileMainBgBluetoothInactive = view.findViewById(R.id.qs_tile_main_bg_bluetooth_inactive);
            mQsTileMainIconBluetoothInactive = view.findViewById(R.id.qs_tile_main_icon_bluetooth_inactive);
            mQsAccentMainDnd = view.findViewById(R.id.qs_accent_main_dnd);
            mQsTileMainDndActive = view.findViewById(R.id.qs_tile_main_dnd_active);
            mQsTileMainBgFlashlightInactive = view.findViewById(R.id.qs_tile_main_bg_flashlight_inactive);
            mQsTileMainIconFlashlightInactive = view.findViewById(R.id.qs_tile_main_icon_flashlight_inactive);
            mQsAccentMainAutorotate = view.findViewById(R.id.qs_accent_main_autorotate);
            mQsTileMainAutorotateActive = view.findViewById(R.id.qs_tile_main_autorotate_active);
            mQsTileMainBgBatterySaverInactive = view.findViewById(R.id.qs_tile_main_bg_battery_saver_inactive);
            mQsTileMainIconBatterySaverInactive = view.findViewById(R.id.qs_tile_main_icon_battery_saver_inactive);
            mViewSpacerMain = view.findViewById(R.id.ic_themes_qs_spacer_main);
            mViewNavbarMain = view.findViewById(R.id.themes_navbar_style_main);
            mThemeMainName = view.findViewById(R.id.theme_main_name);
        }
    }

    class ViewHolderFilled extends RecyclerView.ViewHolder {

        ImageView mWpBgFilled;
        ImageView mQsAccentMainFilledWifi, mQSTileMainFilledWifiActive;
        ImageView mQsTileMainFilledBgBluetoothInactive, mQsTileMainFilledIconBluetoothInactive;
        ImageView mQsAccentMainFilledDnd, mQsTileMainFilledDndActive;
        ImageView mQsTileMainFilledBgFlashlightInactive, mQsTileMainFilledIconFlashlightInactive;
        ImageView mQsAccentMainFilledAutorotate, mQsTileMainFilledAutorotateActive;
        ImageView mQsTileMainFilledBgBatterySaverInactive, mQsTileMainFilledIconBatterySaverInactive;
        ImageView mViewNavbarMainFilled;
        LinearLayout mLlBgMainFilled;
        TextView mThemeMainFilledName;
        View mViewSpacerMainFilled;

        ViewHolderFilled(View view) {
            super(view);
            mWpBgFilled = view.findViewById(R.id.wp_bg_main_filled);
            mLlBgMainFilled = view.findViewById(R.id.ll_qs_bg_main_filled);
            mQsAccentMainFilledWifi = view.findViewById(R.id.qs_accent_main_filled_wifi);
            mQSTileMainFilledWifiActive = view.findViewById(R.id.qs_tile_main_filled_wifi_active);
            mQsTileMainFilledBgBluetoothInactive = view.findViewById(R.id.qs_tile_main_filled_bg_bluetooth_inactive);
            mQsTileMainFilledIconBluetoothInactive = view.findViewById(R.id.qs_tile_main_filled_icon_bluetooth_inactive);
            mQsAccentMainFilledDnd = view.findViewById(R.id.qs_accent_main_filled_dnd);
            mQsTileMainFilledDndActive = view.findViewById(R.id.qs_tile_main_filled_dnd_active);
            mQsTileMainFilledBgFlashlightInactive = view.findViewById(R.id.qs_tile_main_filled_bg_flashlight_inactive);
            mQsTileMainFilledIconFlashlightInactive = view.findViewById(R.id.qs_tile_main_filled_icon_flashlight_inactive);
            mQsAccentMainFilledAutorotate = view.findViewById(R.id.qs_accent_main_filled_autorotate);
            mQsTileMainFilledAutorotateActive = view.findViewById(R.id.qs_tile_main_filled_autorotate_active);
            mQsTileMainFilledBgBatterySaverInactive = view.findViewById(R.id.qs_tile_main_filled_bg_battery_saver_inactive);
            mQsTileMainFilledIconBatterySaverInactive = view.findViewById(R.id.qs_tile_main_filled_icon_battery_saver_inactive);
            mViewSpacerMainFilled = view.findViewById(R.id.ic_themes_qs_spacer_main_filled);
            mViewNavbarMainFilled = view.findViewById(R.id.themes_navbar_style_main_filled);
            mThemeMainFilledName = view.findViewById(R.id.theme_main_filled_name);
        }
    }

    class ViewHolderRounded extends RecyclerView.ViewHolder {

        ImageView mWpBgRounded;
        ImageView mQsAccentMainRoundedWifi, mQSTileMainRoundedWifiActive;
        ImageView mQsTileMainRoundedBgBluetoothInactive, mQsTileMainRoundedIconBluetoothInactive;
        ImageView mQsAccentMainRoundedDnd, mQsTileMainRoundedDndActive;
        ImageView mQsTileMainRoundedBgFlashlightInactive, mQsTileMainRoundedIconFlashlightInactive;
        ImageView mQsAccentMainRoundedAutorotate, mQsTileMainRoundedAutorotateActive;
        ImageView mQsTileMainRoundedBgBatterySaverInactive, mQsTileMainRoundedIconBatterySaverInactive;
        ImageView mViewNavbarMainRounded;
        LinearLayout mLlBgMainRounded;
        TextView mThemeMainRoundedName;
        View mViewSpacerMainRounded;

        ViewHolderRounded(View view) {
            super(view);
            mWpBgRounded = view.findViewById(R.id.wp_bg_main_rounded);
            mLlBgMainRounded = view.findViewById(R.id.ll_qs_bg_main_rounded);
            mQsAccentMainRoundedWifi = view.findViewById(R.id.qs_accent_main_rounded_wifi);
            mQSTileMainRoundedWifiActive = view.findViewById(R.id.qs_tile_main_rounded_wifi_active);
            mQsTileMainRoundedBgBluetoothInactive = view.findViewById(R.id.qs_tile_main_rounded_bg_bluetooth_inactive);
            mQsTileMainRoundedIconBluetoothInactive = view.findViewById(R.id.qs_tile_main_rounded_icon_bluetooth_inactive);
            mQsAccentMainRoundedDnd = view.findViewById(R.id.qs_accent_main_rounded_dnd);
            mQsTileMainRoundedDndActive = view.findViewById(R.id.qs_tile_main_rounded_dnd_active);
            mQsTileMainRoundedBgFlashlightInactive = view.findViewById(R.id.qs_tile_main_rounded_bg_flashlight_inactive);
            mQsTileMainRoundedIconFlashlightInactive = view.findViewById(R.id.qs_tile_main_rounded_icon_flashlight_inactive);
            mQsAccentMainRoundedAutorotate = view.findViewById(R.id.qs_accent_main_rounded_autorotate);
            mQsTileMainRoundedAutorotateActive = view.findViewById(R.id.qs_tile_main_rounded_autorotate_active);
            mQsTileMainRoundedBgBatterySaverInactive = view.findViewById(R.id.qs_tile_main_rounded_bg_battery_saver_inactive);
            mQsTileMainRoundedIconBatterySaverInactive = view.findViewById(R.id.qs_tile_main_rounded_icon_battery_saver_inactive);
            mViewSpacerMainRounded = view.findViewById(R.id.ic_themes_qs_spacer_main_rounded);
            mViewNavbarMainRounded = view.findViewById(R.id.themes_navbar_style_main_rounded);
            mThemeMainRoundedName = view.findViewById(R.id.theme_main_rounded_name);
        }
    }

    class ViewHolderCircular extends RecyclerView.ViewHolder {

        ImageView mWpBgCircular;
        ImageView mQsAccentMainCircularWifi, mQSTileMainCircularWifiActive;
        ImageView mQsTileMainCircularBgBluetoothInactive, mQsTileMainCircularIconBluetoothInactive;
        ImageView mQsAccentMainCircularDnd, mQsTileMainCircularDndActive;
        ImageView mQsTileMainCircularBgFlashlightInactive, mQsTileMainCircularIconFlashlightInactive;
        ImageView mQsAccentMainCircularAutorotate, mQsTileMainCircularAutorotateActive;
        ImageView mQsTileMainCircularBgBatterySaverInactive, mQsTileMainCircularIconBatterySaverInactive;
        ImageView mViewNavbarMainCircular;
        LinearLayout mLlBgMainCircular;
        TextView mThemeMainCircularName;
        View mViewSpacerMainCircular;

        ViewHolderCircular(View view) {
            super(view);
            mWpBgCircular = view.findViewById(R.id.wp_bg_main_circular);
            mLlBgMainCircular = view.findViewById(R.id.ll_qs_bg_main_circular);
            mQsAccentMainCircularWifi = view.findViewById(R.id.qs_accent_main_circular_wifi);
            mQSTileMainCircularWifiActive = view.findViewById(R.id.qs_tile_main_circular_wifi_active);
            mQsTileMainCircularBgBluetoothInactive = view.findViewById(R.id.qs_tile_main_circular_bg_bluetooth_inactive);
            mQsTileMainCircularIconBluetoothInactive = view.findViewById(R.id.qs_tile_main_circular_icon_bluetooth_inactive);
            mQsAccentMainCircularDnd = view.findViewById(R.id.qs_accent_main_circular_dnd);
            mQsTileMainCircularDndActive = view.findViewById(R.id.qs_tile_main_circular_dnd_active);
            mQsTileMainCircularBgFlashlightInactive = view.findViewById(R.id.qs_tile_main_circular_bg_flashlight_inactive);
            mQsTileMainCircularIconFlashlightInactive = view.findViewById(R.id.qs_tile_main_circular_icon_flashlight_inactive);
            mQsAccentMainCircularAutorotate = view.findViewById(R.id.qs_accent_main_circular_autorotate);
            mQsTileMainCircularAutorotateActive = view.findViewById(R.id.qs_tile_main_circular_autorotate_active);
            mQsTileMainCircularBgBatterySaverInactive = view.findViewById(R.id.qs_tile_main_circular_bg_battery_saver_inactive);
            mQsTileMainCircularIconBatterySaverInactive = view.findViewById(R.id.qs_tile_main_circular_icon_battery_saver_inactive);
            mViewSpacerMainCircular = view.findViewById(R.id.ic_themes_qs_spacer_main_circular);
            mViewNavbarMainCircular = view.findViewById(R.id.themes_navbar_style_main_circular);
            mThemeMainCircularName = view.findViewById(R.id.theme_main_circular_name);
        }
    }

    class ViewHolderCircletrim extends RecyclerView.ViewHolder {

        ImageView mWpBgCircletrim;
        ImageView mQsAccentMainCircletrimWifi, mQSTileMainCircletrimWifiActive;
        ImageView mQsTileMainCircletrimBgBluetoothInactive, mQsTileMainCircletrimIconBluetoothInactive;
        ImageView mQsAccentMainCircletrimDnd, mQsTileMainCircletrimDndActive;
        ImageView mQsTileMainCircletrimBgFlashlightInactive, mQsTileMainCircletrimIconFlashlightInactive;
        ImageView mQsAccentMainCircletrimAutorotate, mQsTileMainCircletrimAutorotateActive;
        ImageView mQsTileMainCircletrimBgBatterySaverInactive, mQsTileMainCircletrimIconBatterySaverInactive;
        ImageView mViewNavbarMainCircletrim;
        LinearLayout mLlBgMainCircletrim;
        TextView mThemeMainCircletrimName;
        View mViewSpacerMainCircletrim;

        ViewHolderCircletrim(View view) {
            super(view);
            mWpBgCircletrim = view.findViewById(R.id.wp_bg_main_circletrim);
            mLlBgMainCircletrim = view.findViewById(R.id.ll_qs_bg_main_circletrim);
            mQsAccentMainCircletrimWifi = view.findViewById(R.id.qs_accent_main_circletrim_wifi);
            mQSTileMainCircletrimWifiActive = view.findViewById(R.id.qs_tile_main_circletrim_wifi_active);
            mQsTileMainCircletrimBgBluetoothInactive = view.findViewById(R.id.qs_tile_main_circletrim_bg_bluetooth_inactive);
            mQsTileMainCircletrimIconBluetoothInactive = view.findViewById(R.id.qs_tile_main_circletrim_icon_bluetooth_inactive);
            mQsAccentMainCircletrimDnd = view.findViewById(R.id.qs_accent_main_circletrim_dnd);
            mQsTileMainCircletrimDndActive = view.findViewById(R.id.qs_tile_main_circletrim_dnd_active);
            mQsTileMainCircletrimBgFlashlightInactive = view.findViewById(R.id.qs_tile_main_circletrim_bg_flashlight_inactive);
            mQsTileMainCircletrimIconFlashlightInactive = view.findViewById(R.id.qs_tile_main_circletrim_icon_flashlight_inactive);
            mQsAccentMainCircletrimAutorotate = view.findViewById(R.id.qs_accent_main_circletrim_autorotate);
            mQsTileMainCircletrimAutorotateActive = view.findViewById(R.id.qs_tile_main_circletrim_autorotate_active);
            mQsTileMainCircletrimBgBatterySaverInactive = view.findViewById(R.id.qs_tile_main_circletrim_bg_battery_saver_inactive);
            mQsTileMainCircletrimIconBatterySaverInactive = view.findViewById(R.id.qs_tile_main_circletrim_icon_battery_saver_inactive);
            mViewSpacerMainCircletrim = view.findViewById(R.id.ic_themes_qs_spacer_main_circletrim);
            mViewNavbarMainCircletrim = view.findViewById(R.id.themes_navbar_style_main_circletrim);
            mThemeMainCircletrimName = view.findViewById(R.id.theme_main_circletrim_name);
        }
    }

    class ViewHolderDualtonecircletrim extends RecyclerView.ViewHolder {

        ImageView mWpBgDualtonecircletrim;
        ImageView mQsAccentMainDualtonecircletrimWifi, mQSTileMainDualtonecircletrimWifiActive;
        ImageView mQsTileMainDualtonecircletrimBgBluetoothInactive, mQsTileMainDualtonecircletrimIconBluetoothInactive;
        ImageView mQsAccentMainDualtonecircletrimDnd, mQsTileMainDualtonecircletrimDndActive;
        ImageView mQsTileMainDualtonecircletrimBgFlashlightInactive, mQsTileMainDualtonecircletrimIconFlashlightInactive;
        ImageView mQsAccentMainDualtonecircletrimAutorotate, mQsTileMainDualtonecircletrimAutorotateActive;
        ImageView mQsTileMainDualtonecircletrimBgBatterySaverInactive, mQsTileMainDualtonecircletrimIconBatterySaverInactive;
        ImageView mViewNavbarMainDualtonecircletrim;
        LinearLayout mLlBgMainDualtonecircletrim;
        TextView mThemeMainDualtonecircletrimName;
        View mViewSpacerMainDualtonecircletrim;

        ViewHolderDualtonecircletrim(View view) {
            super(view);
            mWpBgDualtonecircletrim = view.findViewById(R.id.wp_bg_main_dualtonecircletrim);
            mLlBgMainDualtonecircletrim = view.findViewById(R.id.ll_qs_bg_main_dualtonecircletrim);
            mQsAccentMainDualtonecircletrimWifi = view.findViewById(R.id.qs_accent_main_dualtonecircletrim_wifi);
            mQSTileMainDualtonecircletrimWifiActive = view.findViewById(R.id.qs_tile_main_dualtonecircletrim_wifi_active);
            mQsTileMainDualtonecircletrimBgBluetoothInactive = view.findViewById(R.id.qs_tile_main_dualtonecircletrim_bg_bluetooth_inactive);
            mQsTileMainDualtonecircletrimIconBluetoothInactive = view.findViewById(R.id.qs_tile_main_dualtonecircletrim_icon_bluetooth_inactive);
            mQsAccentMainDualtonecircletrimDnd = view.findViewById(R.id.qs_accent_main_dualtonecircletrim_dnd);
            mQsTileMainDualtonecircletrimDndActive = view.findViewById(R.id.qs_tile_main_dualtonecircletrim_dnd_active);
            mQsTileMainDualtonecircletrimBgFlashlightInactive = view.findViewById(R.id.qs_tile_main_dualtonecircletrim_bg_flashlight_inactive);
            mQsTileMainDualtonecircletrimIconFlashlightInactive = view.findViewById(R.id.qs_tile_main_dualtonecircletrim_icon_flashlight_inactive);
            mQsAccentMainDualtonecircletrimAutorotate = view.findViewById(R.id.qs_accent_main_dualtonecircletrim_autorotate);
            mQsTileMainDualtonecircletrimAutorotateActive = view.findViewById(R.id.qs_tile_main_dualtonecircletrim_autorotate_active);
            mQsTileMainDualtonecircletrimBgBatterySaverInactive = view.findViewById(R.id.qs_tile_main_dualtonecircletrim_bg_battery_saver_inactive);
            mQsTileMainDualtonecircletrimIconBatterySaverInactive = view.findViewById(R.id.qs_tile_main_dualtonecircletrim_icon_battery_saver_inactive);
            mViewSpacerMainDualtonecircletrim = view.findViewById(R.id.ic_themes_qs_spacer_main_dualtonecircletrim);
            mViewNavbarMainDualtonecircletrim = view.findViewById(R.id.themes_navbar_style_main_dualtonecircletrim);
            mThemeMainDualtonecircletrimName = view.findViewById(R.id.theme_main_dualtonecircletrim_name);
        }
    }

    class ViewHolderSquircletrim extends RecyclerView.ViewHolder {

        ImageView mWpBgSquircletrim;
        ImageView mQsAccentMainSquircletrimWifi, mQSTileMainSquircletrimWifiActive;
        ImageView mQsTileMainSquircletrimBgBluetoothInactive, mQsTileMainSquircletrimIconBluetoothInactive;
        ImageView mQsAccentMainSquircletrimDnd, mQsTileMainSquircletrimDndActive;
        ImageView mQsTileMainSquircletrimBgFlashlightInactive, mQsTileMainSquircletrimIconFlashlightInactive;
        ImageView mQsAccentMainSquircletrimAutorotate, mQsTileMainSquircletrimAutorotateActive;
        ImageView mQsTileMainSquircletrimBgBatterySaverInactive, mQsTileMainSquircletrimIconBatterySaverInactive;
        ImageView mViewNavbarMainSquircletrim;
        LinearLayout mLlBgMainSquircletrim;
        TextView mThemeMainSquircletrimName;
        View mViewSpacerMainSquircletrim;

        ViewHolderSquircletrim(View view) {
            super(view);
            mWpBgSquircletrim = view.findViewById(R.id.wp_bg_main_squircletrim);
            mLlBgMainSquircletrim = view.findViewById(R.id.ll_qs_bg_main_squircletrim);
            mQsAccentMainSquircletrimWifi = view.findViewById(R.id.qs_accent_main_squircletrim_wifi);
            mQSTileMainSquircletrimWifiActive = view.findViewById(R.id.qs_tile_main_squircletrim_wifi_active);
            mQsTileMainSquircletrimBgBluetoothInactive = view.findViewById(R.id.qs_tile_main_squircletrim_bg_bluetooth_inactive);
            mQsTileMainSquircletrimIconBluetoothInactive = view.findViewById(R.id.qs_tile_main_squircletrim_icon_bluetooth_inactive);
            mQsAccentMainSquircletrimDnd = view.findViewById(R.id.qs_accent_main_squircletrim_dnd);
            mQsTileMainSquircletrimDndActive = view.findViewById(R.id.qs_tile_main_squircletrim_dnd_active);
            mQsTileMainSquircletrimBgFlashlightInactive = view.findViewById(R.id.qs_tile_main_squircletrim_bg_flashlight_inactive);
            mQsTileMainSquircletrimIconFlashlightInactive = view.findViewById(R.id.qs_tile_main_squircletrim_icon_flashlight_inactive);
            mQsAccentMainSquircletrimAutorotate = view.findViewById(R.id.qs_accent_main_squircletrim_autorotate);
            mQsTileMainSquircletrimAutorotateActive = view.findViewById(R.id.qs_tile_main_squircletrim_autorotate_active);
            mQsTileMainSquircletrimBgBatterySaverInactive = view.findViewById(R.id.qs_tile_main_squircletrim_bg_battery_saver_inactive);
            mQsTileMainSquircletrimIconBatterySaverInactive = view.findViewById(R.id.qs_tile_main_squircletrim_icon_battery_saver_inactive);
            mViewSpacerMainSquircletrim = view.findViewById(R.id.ic_themes_qs_spacer_main_squircletrim);
            mViewNavbarMainSquircletrim = view.findViewById(R.id.themes_navbar_style_main_squircletrim);
            mThemeMainSquircletrimName = view.findViewById(R.id.theme_main_squircletrim_name);
        }
    }

    class ViewHolderAttemptmountain extends RecyclerView.ViewHolder {

        ImageView mWpBgAttemptmountain;
        ImageView mQsAccentMainAttemptmountainWifi, mQSTileMainAttemptmountainWifiActive;
        ImageView mQsTileMainAttemptmountainBgBluetoothInactive, mQsTileMainAttemptmountainIconBluetoothInactive;
        ImageView mQsAccentMainAttemptmountainDnd, mQsTileMainAttemptmountainDndActive;
        ImageView mQsTileMainAttemptmountainBgFlashlightInactive, mQsTileMainAttemptmountainIconFlashlightInactive;
        ImageView mQsAccentMainAttemptmountainAutorotate, mQsTileMainAttemptmountainAutorotateActive;
        ImageView mQsTileMainAttemptmountainBgBatterySaverInactive, mQsTileMainAttemptmountainIconBatterySaverInactive;
        ImageView mViewNavbarMainAttemptmountain;
        LinearLayout mLlBgMainAttemptmountain;
        TextView mThemeMainAttemptmountainName;
        View mViewSpacerMainAttemptmountain;

        ViewHolderAttemptmountain(View view) {
            super(view);
            mWpBgAttemptmountain = view.findViewById(R.id.wp_bg_main_attemptmountain);
            mLlBgMainAttemptmountain = view.findViewById(R.id.ll_qs_bg_main_attemptmountain);
            mQsAccentMainAttemptmountainWifi = view.findViewById(R.id.qs_accent_main_attemptmountain_wifi);
            mQSTileMainAttemptmountainWifiActive = view.findViewById(R.id.qs_tile_main_attemptmountain_wifi_active);
            mQsTileMainAttemptmountainBgBluetoothInactive = view.findViewById(R.id.qs_tile_main_attemptmountain_bg_bluetooth_inactive);
            mQsTileMainAttemptmountainIconBluetoothInactive = view.findViewById(R.id.qs_tile_main_attemptmountain_icon_bluetooth_inactive);
            mQsAccentMainAttemptmountainDnd = view.findViewById(R.id.qs_accent_main_attemptmountain_dnd);
            mQsTileMainAttemptmountainDndActive = view.findViewById(R.id.qs_tile_main_attemptmountain_dnd_active);
            mQsTileMainAttemptmountainBgFlashlightInactive = view.findViewById(R.id.qs_tile_main_attemptmountain_bg_flashlight_inactive);
            mQsTileMainAttemptmountainIconFlashlightInactive = view.findViewById(R.id.qs_tile_main_attemptmountain_icon_flashlight_inactive);
            mQsAccentMainAttemptmountainAutorotate = view.findViewById(R.id.qs_accent_main_attemptmountain_autorotate);
            mQsTileMainAttemptmountainAutorotateActive = view.findViewById(R.id.qs_tile_main_attemptmountain_autorotate_active);
            mQsTileMainAttemptmountainBgBatterySaverInactive = view.findViewById(R.id.qs_tile_main_attemptmountain_bg_battery_saver_inactive);
            mQsTileMainAttemptmountainIconBatterySaverInactive = view.findViewById(R.id.qs_tile_main_attemptmountain_icon_battery_saver_inactive);
            mViewSpacerMainAttemptmountain = view.findViewById(R.id.ic_themes_qs_spacer_main_attemptmountain);
            mViewNavbarMainAttemptmountain = view.findViewById(R.id.themes_navbar_style_main_attemptmountain);
            mThemeMainAttemptmountainName = view.findViewById(R.id.theme_main_attemptmountain_name);
        }
    }

    class ViewHolderCosmos extends RecyclerView.ViewHolder {

        ImageView mWpBgCosmos;
        ImageView mQsAccentMainCosmosWifi, mQSTileMainCosmosWifiActive;
        ImageView mQsTileMainCosmosBgBluetoothInactive, mQsTileMainCosmosIconBluetoothInactive;
        ImageView mQsAccentMainCosmosDnd, mQsTileMainCosmosDndActive;
        ImageView mQsTileMainCosmosBgFlashlightInactive, mQsTileMainCosmosIconFlashlightInactive;
        ImageView mQsAccentMainCosmosAutorotate, mQsTileMainCosmosAutorotateActive;
        ImageView mQsTileMainCosmosBgBatterySaverInactive, mQsTileMainCosmosIconBatterySaverInactive;
        ImageView mViewNavbarMainCosmos;
        LinearLayout mLlBgMainCosmos;
        TextView mThemeMainCosmosName;
        View mViewSpacerMainCosmos;

        ViewHolderCosmos(View view) {
            super(view);
            mWpBgCosmos = view.findViewById(R.id.wp_bg_main_cosmos);
            mLlBgMainCosmos = view.findViewById(R.id.ll_qs_bg_main_cosmos);
            mQsAccentMainCosmosWifi = view.findViewById(R.id.qs_accent_main_cosmos_wifi);
            mQSTileMainCosmosWifiActive = view.findViewById(R.id.qs_tile_main_cosmos_wifi_active);
            mQsTileMainCosmosBgBluetoothInactive = view.findViewById(R.id.qs_tile_main_cosmos_bg_bluetooth_inactive);
            mQsTileMainCosmosIconBluetoothInactive = view.findViewById(R.id.qs_tile_main_cosmos_icon_bluetooth_inactive);
            mQsAccentMainCosmosDnd = view.findViewById(R.id.qs_accent_main_cosmos_dnd);
            mQsTileMainCosmosDndActive = view.findViewById(R.id.qs_tile_main_cosmos_dnd_active);
            mQsTileMainCosmosBgFlashlightInactive = view.findViewById(R.id.qs_tile_main_cosmos_bg_flashlight_inactive);
            mQsTileMainCosmosIconFlashlightInactive = view.findViewById(R.id.qs_tile_main_cosmos_icon_flashlight_inactive);
            mQsAccentMainCosmosAutorotate = view.findViewById(R.id.qs_accent_main_cosmos_autorotate);
            mQsTileMainCosmosAutorotateActive = view.findViewById(R.id.qs_tile_main_cosmos_autorotate_active);
            mQsTileMainCosmosBgBatterySaverInactive = view.findViewById(R.id.qs_tile_main_cosmos_bg_battery_saver_inactive);
            mQsTileMainCosmosIconBatterySaverInactive = view.findViewById(R.id.qs_tile_main_cosmos_icon_battery_saver_inactive);
            mViewSpacerMainCosmos = view.findViewById(R.id.ic_themes_qs_spacer_main_cosmos);
            mViewNavbarMainCosmos = view.findViewById(R.id.themes_navbar_style_main_cosmos);
            mThemeMainCosmosName = view.findViewById(R.id.theme_main_cosmos_name);
        }
    }

    class ViewHolderDottedcircle extends RecyclerView.ViewHolder {

        ImageView mWpBgDottedcircle;
        ImageView mQsAccentMainDottedcircleWifi, mQSTileMainDottedcircleWifiActive;
        ImageView mQsTileMainDottedcircleBgBluetoothInactive, mQsTileMainDottedcircleIconBluetoothInactive;
        ImageView mQsAccentMainDottedcircleDnd, mQsTileMainDottedcircleDndActive;
        ImageView mQsTileMainDottedcircleBgFlashlightInactive, mQsTileMainDottedcircleIconFlashlightInactive;
        ImageView mQsAccentMainDottedcircleAutorotate, mQsTileMainDottedcircleAutorotateActive;
        ImageView mQsTileMainDottedcircleBgBatterySaverInactive, mQsTileMainDottedcircleIconBatterySaverInactive;
        ImageView mViewNavbarMainDottedcircle;
        LinearLayout mLlBgMainDottedcircle;
        TextView mThemeMainDottedcircleName;
        View mViewSpacerMainDottedcircle;

        ViewHolderDottedcircle(View view) {
            super(view);
            mWpBgDottedcircle = view.findViewById(R.id.wp_bg_main_dottedcircle);
            mLlBgMainDottedcircle = view.findViewById(R.id.ll_qs_bg_main_dottedcircle);
            mQsAccentMainDottedcircleWifi = view.findViewById(R.id.qs_accent_main_dottedcircle_wifi);
            mQSTileMainDottedcircleWifiActive = view.findViewById(R.id.qs_tile_main_dottedcircle_wifi_active);
            mQsTileMainDottedcircleBgBluetoothInactive = view.findViewById(R.id.qs_tile_main_dottedcircle_bg_bluetooth_inactive);
            mQsTileMainDottedcircleIconBluetoothInactive = view.findViewById(R.id.qs_tile_main_dottedcircle_icon_bluetooth_inactive);
            mQsAccentMainDottedcircleDnd = view.findViewById(R.id.qs_accent_main_dottedcircle_dnd);
            mQsTileMainDottedcircleDndActive = view.findViewById(R.id.qs_tile_main_dottedcircle_dnd_active);
            mQsTileMainDottedcircleBgFlashlightInactive = view.findViewById(R.id.qs_tile_main_dottedcircle_bg_flashlight_inactive);
            mQsTileMainDottedcircleIconFlashlightInactive = view.findViewById(R.id.qs_tile_main_dottedcircle_icon_flashlight_inactive);
            mQsAccentMainDottedcircleAutorotate = view.findViewById(R.id.qs_accent_main_dottedcircle_autorotate);
            mQsTileMainDottedcircleAutorotateActive = view.findViewById(R.id.qs_tile_main_dottedcircle_autorotate_active);
            mQsTileMainDottedcircleBgBatterySaverInactive = view.findViewById(R.id.qs_tile_main_dottedcircle_bg_battery_saver_inactive);
            mQsTileMainDottedcircleIconBatterySaverInactive = view.findViewById(R.id.qs_tile_main_dottedcircle_icon_battery_saver_inactive);
            mViewSpacerMainDottedcircle = view.findViewById(R.id.ic_themes_qs_spacer_main_dottedcircle);
            mViewNavbarMainDottedcircle = view.findViewById(R.id.themes_navbar_style_main_dottedcircle);
            mThemeMainDottedcircleName = view.findViewById(R.id.theme_main_dottedcircle_name);
        }
    }

    class ViewHolderNinja extends RecyclerView.ViewHolder {

        ImageView mWpBgNinja;
        ImageView mQsAccentMainNinjaWifi, mQSTileMainNinjaWifiActive;
        ImageView mQsTileMainNinjaBgBluetoothInactive, mQsTileMainNinjaIconBluetoothInactive;
        ImageView mQsAccentMainNinjaDnd, mQsTileMainNinjaDndActive;
        ImageView mQsTileMainNinjaBgFlashlightInactive, mQsTileMainNinjaIconFlashlightInactive;
        ImageView mQsAccentMainNinjaAutorotate, mQsTileMainNinjaAutorotateActive;
        ImageView mQsTileMainNinjaBgBatterySaverInactive, mQsTileMainNinjaIconBatterySaverInactive;
        ImageView mViewNavbarMainNinja;
        LinearLayout mLlBgMainNinja;
        TextView mThemeMainNinjaName;
        View mViewSpacerMainNinja;

        ViewHolderNinja(View view) {
            super(view);
            mWpBgNinja = view.findViewById(R.id.wp_bg_main_ninja);
            mLlBgMainNinja = view.findViewById(R.id.ll_qs_bg_main_ninja);
            mQsAccentMainNinjaWifi = view.findViewById(R.id.qs_accent_main_ninja_wifi);
            mQSTileMainNinjaWifiActive = view.findViewById(R.id.qs_tile_main_ninja_wifi_active);
            mQsTileMainNinjaBgBluetoothInactive = view.findViewById(R.id.qs_tile_main_ninja_bg_bluetooth_inactive);
            mQsTileMainNinjaIconBluetoothInactive = view.findViewById(R.id.qs_tile_main_ninja_icon_bluetooth_inactive);
            mQsAccentMainNinjaDnd = view.findViewById(R.id.qs_accent_main_ninja_dnd);
            mQsTileMainNinjaDndActive = view.findViewById(R.id.qs_tile_main_ninja_dnd_active);
            mQsTileMainNinjaBgFlashlightInactive = view.findViewById(R.id.qs_tile_main_ninja_bg_flashlight_inactive);
            mQsTileMainNinjaIconFlashlightInactive = view.findViewById(R.id.qs_tile_main_ninja_icon_flashlight_inactive);
            mQsAccentMainNinjaAutorotate = view.findViewById(R.id.qs_accent_main_ninja_autorotate);
            mQsTileMainNinjaAutorotateActive = view.findViewById(R.id.qs_tile_main_ninja_autorotate_active);
            mQsTileMainNinjaBgBatterySaverInactive = view.findViewById(R.id.qs_tile_main_ninja_bg_battery_saver_inactive);
            mQsTileMainNinjaIconBatterySaverInactive = view.findViewById(R.id.qs_tile_main_ninja_icon_battery_saver_inactive);
            mViewSpacerMainNinja = view.findViewById(R.id.ic_themes_qs_spacer_main_ninja);
            mViewNavbarMainNinja = view.findViewById(R.id.themes_navbar_style_main_ninja);
            mThemeMainNinjaName = view.findViewById(R.id.theme_main_ninja_name);
        }
    }

    class ViewHolderPokesign extends RecyclerView.ViewHolder {

        ImageView mWpBgPokesign;
        ImageView mQsAccentMainPokesignWifi, mQSTileMainPokesignWifiActive;
        ImageView mQsTileMainPokesignBgBluetoothInactive, mQsTileMainPokesignIconBluetoothInactive;
        ImageView mQsAccentMainPokesignDnd, mQsTileMainPokesignDndActive;
        ImageView mQsTileMainPokesignBgFlashlightInactive, mQsTileMainPokesignIconFlashlightInactive;
        ImageView mQsAccentMainPokesignAutorotate, mQsTileMainPokesignAutorotateActive;
        ImageView mQsTileMainPokesignBgBatterySaverInactive, mQsTileMainPokesignIconBatterySaverInactive;
        ImageView mViewNavbarMainPokesign;
        LinearLayout mLlBgMainPokesign;
        TextView mThemeMainPokesignName;
        View mViewSpacerMainPokesign;

        ViewHolderPokesign(View view) {
            super(view);
            mWpBgPokesign = view.findViewById(R.id.wp_bg_main_pokesign);
            mLlBgMainPokesign = view.findViewById(R.id.ll_qs_bg_main_pokesign);
            mQsAccentMainPokesignWifi = view.findViewById(R.id.qs_accent_main_pokesign_wifi);
            mQSTileMainPokesignWifiActive = view.findViewById(R.id.qs_tile_main_pokesign_wifi_active);
            mQsTileMainPokesignBgBluetoothInactive = view.findViewById(R.id.qs_tile_main_pokesign_bg_bluetooth_inactive);
            mQsTileMainPokesignIconBluetoothInactive = view.findViewById(R.id.qs_tile_main_pokesign_icon_bluetooth_inactive);
            mQsAccentMainPokesignDnd = view.findViewById(R.id.qs_accent_main_pokesign_dnd);
            mQsTileMainPokesignDndActive = view.findViewById(R.id.qs_tile_main_pokesign_dnd_active);
            mQsTileMainPokesignBgFlashlightInactive = view.findViewById(R.id.qs_tile_main_pokesign_bg_flashlight_inactive);
            mQsTileMainPokesignIconFlashlightInactive = view.findViewById(R.id.qs_tile_main_pokesign_icon_flashlight_inactive);
            mQsAccentMainPokesignAutorotate = view.findViewById(R.id.qs_accent_main_pokesign_autorotate);
            mQsTileMainPokesignAutorotateActive = view.findViewById(R.id.qs_tile_main_pokesign_autorotate_active);
            mQsTileMainPokesignBgBatterySaverInactive = view.findViewById(R.id.qs_tile_main_pokesign_bg_battery_saver_inactive);
            mQsTileMainPokesignIconBatterySaverInactive = view.findViewById(R.id.qs_tile_main_pokesign_icon_battery_saver_inactive);
            mViewSpacerMainPokesign = view.findViewById(R.id.ic_themes_qs_spacer_main_pokesign);
            mViewNavbarMainPokesign = view.findViewById(R.id.themes_navbar_style_main_pokesign);
            mThemeMainPokesignName = view.findViewById(R.id.theme_main_pokesign_name);
        }
    }

    class ViewHolderWavey extends RecyclerView.ViewHolder {

        ImageView mWpBgWavey;
        ImageView mQsAccentMainWaveyWifi, mQSTileMainWaveyWifiActive;
        ImageView mQsTileMainWaveyBgBluetoothInactive, mQsTileMainWaveyIconBluetoothInactive;
        ImageView mQsAccentMainWaveyDnd, mQsTileMainWaveyDndActive;
        ImageView mQsTileMainWaveyBgFlashlightInactive, mQsTileMainWaveyIconFlashlightInactive;
        ImageView mQsAccentMainWaveyAutorotate, mQsTileMainWaveyAutorotateActive;
        ImageView mQsTileMainWaveyBgBatterySaverInactive, mQsTileMainWaveyIconBatterySaverInactive;
        ImageView mViewNavbarMainWavey;
        LinearLayout mLlBgMainWavey;
        TextView mThemeMainWaveyName;
        View mViewSpacerMainWavey;

        ViewHolderWavey(View view) {
            super(view);
            mWpBgWavey = view.findViewById(R.id.wp_bg_main_wavey);
            mLlBgMainWavey = view.findViewById(R.id.ll_qs_bg_main_wavey);
            mQsAccentMainWaveyWifi = view.findViewById(R.id.qs_accent_main_wavey_wifi);
            mQSTileMainWaveyWifiActive = view.findViewById(R.id.qs_tile_main_wavey_wifi_active);
            mQsTileMainWaveyBgBluetoothInactive = view.findViewById(R.id.qs_tile_main_wavey_bg_bluetooth_inactive);
            mQsTileMainWaveyIconBluetoothInactive = view.findViewById(R.id.qs_tile_main_wavey_icon_bluetooth_inactive);
            mQsAccentMainWaveyDnd = view.findViewById(R.id.qs_accent_main_wavey_dnd);
            mQsTileMainWaveyDndActive = view.findViewById(R.id.qs_tile_main_wavey_dnd_active);
            mQsTileMainWaveyBgFlashlightInactive = view.findViewById(R.id.qs_tile_main_wavey_bg_flashlight_inactive);
            mQsTileMainWaveyIconFlashlightInactive = view.findViewById(R.id.qs_tile_main_wavey_icon_flashlight_inactive);
            mQsAccentMainWaveyAutorotate = view.findViewById(R.id.qs_accent_main_wavey_autorotate);
            mQsTileMainWaveyAutorotateActive = view.findViewById(R.id.qs_tile_main_wavey_autorotate_active);
            mQsTileMainWaveyBgBatterySaverInactive = view.findViewById(R.id.qs_tile_main_wavey_bg_battery_saver_inactive);
            mQsTileMainWaveyIconBatterySaverInactive = view.findViewById(R.id.qs_tile_main_wavey_icon_battery_saver_inactive);
            mViewSpacerMainWavey = view.findViewById(R.id.ic_themes_qs_spacer_main_wavey);
            mViewNavbarMainWavey = view.findViewById(R.id.themes_navbar_style_main_wavey);
            mThemeMainWaveyName = view.findViewById(R.id.theme_main_wavey_name);
        }
    }

    @Override
    public int getItemViewType(int position) {
        ThemesListItem themes = mThemesList.get(position);
        int themeViewType = 0;
        String themeQSTileStyle = themes.getThemeQSTileStyle();
        if (!themeQSTileStyle.equals("com.android.systemui.qstile.default")) {
            switch (themeQSTileStyle) {
                case "com.android.systemui.qstile.circletrim":
                    themeViewType = 5;
                    break;
                case "com.android.systemui.qstile.dualtonecircletrim":
                    themeViewType = 6;
                    break;
                case "com.android.systemui.qstile.squircletrim":
                    themeViewType = 7;
                    break;
                case "com.android.systemui.qstile.attemptmountain":
                    themeViewType = 8;
                    break;
                case "com.android.systemui.qstile.cosmos":
                    themeViewType = 9;
                    break;
                case "com.android.systemui.qstile.dottedcircle":
                    themeViewType = 10;
                    break;
                case "com.android.systemui.qstile.ninja":
                    themeViewType = 11;
                    break;
                case "com.android.systemui.qstile.pokesign":
                    themeViewType = 12;
                    break;
                case "com.android.systemui.qstile.wavey":
                    themeViewType = 13;
                    break;
            }
        } else {
            themeViewType = Integer.parseInt(themes.getThemeSbIcons());
        }
        return themeViewType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1:
                mViewHolder = new ViewHolderMain(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.restore_themes_main, parent, false));
                break;
            case 2:
                mViewHolder = new ViewHolderFilled(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.restore_themes_main_filled, parent, false));
                break;
            case 3:
                mViewHolder = new ViewHolderRounded(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.restore_themes_main_rounded, parent, false));
                break;
            case 4:
                mViewHolder = new ViewHolderCircular(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.restore_themes_main_circular, parent, false));
                break;
            case 5:
                mViewHolder = new ViewHolderCircletrim(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.restore_themes_main_circletrim, parent, false));
                break;
            case 6:
                mViewHolder = new ViewHolderDualtonecircletrim(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.restore_themes_main_dualtonecircletrim, parent, false));
                break;
            case 7:
                mViewHolder = new ViewHolderSquircletrim(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.restore_themes_main_squircletrim, parent, false));
                break;
            case 8:
                mViewHolder = new ViewHolderAttemptmountain(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.restore_themes_main_attemptmountain, parent, false));
                break;
            case 9:
                mViewHolder = new ViewHolderCosmos(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.restore_themes_main_cosmos, parent, false));
                break;
            case 10:
                mViewHolder = new ViewHolderDottedcircle(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.restore_themes_main_dottedcircle, parent, false));
                break;
            case 11:
                mViewHolder = new ViewHolderNinja(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.restore_themes_main_ninja, parent, false));
                break;
            case 12:
                mViewHolder = new ViewHolderPokesign(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.restore_themes_main_pokesign, parent, false));
                break;
            case 13:
                mViewHolder = new ViewHolderWavey(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.restore_themes_main_wavey, parent, false));
                break;
        }
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ThemesListItem themes = mThemesList.get(position);
        mIsNightMode = Boolean.parseBoolean(themes.getThemeDayOrNight());
        mThemeNightColor = Color.parseColor(themes.getThemeNightColor());
        mThemeFont = Integer.parseInt(themes.getThemeFont());
        mThemeWpBackup = themes.getThemeWp();
        int bgQsAccent = 0;
        String accentColor = themes.getAccentPicker();
        final String prefix = "hex";
        if (accentColor.startsWith(prefix)) {
            bgQsAccent = Color.parseColor("#" + accentColor.substring(prefix.length()));
        } else {
            bgQsAccent = Color.parseColor(themes.getThemeAccent());
        }
        int qsTileBgInactive, qsTileIconInactive, qsTileIconActive;
        String themeNavbarStyle = themes.getThemeNavbarStyle();
        String themeQSTileStyle = themes.getThemeQSTileStyle();
        String pathShape = themes.getThemeIconShape();
        String themeName = themes.getThemeName();
        if (!mIsNightMode) {
            qsTileBgInactive = mResources.getColor(R.color.qs_tile_background_inactive_day);
            qsTileIconInactive = mResources.getColor(R.color.qs_tile_icon_inactive_day);
            qsTileIconActive = mResources.getColor(R.color.qs_tile_icon_active_day);
        } else {
            qsTileBgInactive = mResources.getColor(R.color.qs_tile_background_inactive_night);
            qsTileIconInactive = mResources.getColor(R.color.qs_tile_icon_inactive_night);
            qsTileIconActive = mResources.getColor(R.color.qs_tile_icon_active_night);
        }
        switch (holder.getItemViewType()) {
            case 1:
                ViewHolderMain viewHolderMain = (ViewHolderMain) holder;
                new ThemeWallpaper(viewHolderMain.mWpBgMain).execute();
                viewHolderMain.mLlBgMain.setBackground(getThemeDayNightBg());
                viewHolderMain.mQsAccentMainWifi.setImageDrawable(
                    getShapeDrawable(pathShape, bgQsAccent));
                viewHolderMain.mQSTileMainWifiActive.setColorFilter(qsTileIconActive);
                viewHolderMain.mQsTileMainBgBluetoothInactive.setImageDrawable(
                    getShapeDrawable(pathShape, qsTileBgInactive));
                viewHolderMain.mQsTileMainIconBluetoothInactive.setColorFilter(qsTileIconInactive);
                viewHolderMain.mQsAccentMainDnd.setImageDrawable(
                    getShapeDrawable(pathShape, bgQsAccent));
                viewHolderMain.mQsTileMainDndActive.setColorFilter(qsTileIconActive);
                viewHolderMain.mQsTileMainBgFlashlightInactive.setImageDrawable(
                    getShapeDrawable(pathShape, qsTileBgInactive));
                viewHolderMain.mQsTileMainIconFlashlightInactive.setColorFilter(qsTileIconInactive);
                viewHolderMain.mQsAccentMainAutorotate.setImageDrawable(
                    getShapeDrawable(pathShape, bgQsAccent));
                viewHolderMain.mQsTileMainAutorotateActive.setColorFilter(qsTileIconActive);
                viewHolderMain.mQsTileMainBgBatterySaverInactive.setImageDrawable(
                    getShapeDrawable(pathShape, qsTileBgInactive));
                viewHolderMain.mQsTileMainIconBatterySaverInactive.setColorFilter(qsTileIconInactive);
                viewHolderMain.mViewSpacerMain.setBackground(getThemeDayNightSpacer());
                if (threeButtonNavbarEnabled(mContext)) {
                    viewHolderMain.mViewNavbarMain.setImageDrawable(getNavbarStyle(themeNavbarStyle));
                    viewHolderMain.mViewNavbarMain.setBackgroundColor(ColorUtils.setAlphaComponent(
                        mResources.getColor(R.color.themes_preview_navbar_bg), 100));
                } else {
                    viewHolderMain.mViewNavbarMain.setVisibility(View.GONE);
                }
                viewHolderMain.mThemeMainName.setText(themeName);
                viewHolderMain.mThemeMainName.setTypeface(getTypeface());
                break;
            case 2:
                ViewHolderFilled viewHolderFilled = (ViewHolderFilled) holder;
                new ThemeWallpaper(viewHolderFilled.mWpBgFilled).execute();
                viewHolderFilled.mLlBgMainFilled.setBackground(getThemeDayNightBg());
                viewHolderFilled.mQsAccentMainFilledWifi.setImageDrawable(
                    getShapeDrawable(pathShape, bgQsAccent));
                viewHolderFilled.mQSTileMainFilledWifiActive.setColorFilter(qsTileIconActive);
                viewHolderFilled.mQsTileMainFilledBgBluetoothInactive.setImageDrawable(
                    getShapeDrawable(pathShape, qsTileBgInactive));
                viewHolderFilled.mQsTileMainFilledIconBluetoothInactive.setColorFilter(qsTileIconInactive);
                viewHolderFilled.mQsAccentMainFilledDnd.setImageDrawable(
                    getShapeDrawable(pathShape, bgQsAccent));
                viewHolderFilled.mQsTileMainFilledDndActive.setColorFilter(qsTileIconActive);
                viewHolderFilled.mQsTileMainFilledBgFlashlightInactive.setImageDrawable(
                    getShapeDrawable(pathShape, qsTileBgInactive));
                viewHolderFilled.mQsTileMainFilledIconFlashlightInactive.setColorFilter(qsTileIconInactive);
                viewHolderFilled.mQsAccentMainFilledAutorotate.setImageDrawable(
                    getShapeDrawable(pathShape, bgQsAccent));
                viewHolderFilled.mQsTileMainFilledAutorotateActive.setColorFilter(qsTileIconActive);
                viewHolderFilled.mQsTileMainFilledBgBatterySaverInactive.setImageDrawable(
                    getShapeDrawable(pathShape, qsTileBgInactive));
                viewHolderFilled.mQsTileMainFilledIconBatterySaverInactive.setColorFilter(qsTileIconInactive);
                viewHolderFilled.mViewSpacerMainFilled.setBackground(getThemeDayNightSpacer());
                if (threeButtonNavbarEnabled(mContext)) {
                    viewHolderFilled.mViewNavbarMainFilled.setImageDrawable(getNavbarStyle(themeNavbarStyle));
                    viewHolderFilled.mViewNavbarMainFilled.setBackgroundColor(ColorUtils.setAlphaComponent(
                        mResources.getColor(R.color.themes_preview_navbar_bg), 100));
                } else {
                    viewHolderFilled.mViewNavbarMainFilled.setVisibility(View.GONE);
                }
                viewHolderFilled.mThemeMainFilledName.setText(themeName);
                viewHolderFilled.mThemeMainFilledName.setTypeface(getTypeface());
                break;
            case 3:
                ViewHolderRounded viewHolderRounded = (ViewHolderRounded) holder;
                new ThemeWallpaper(viewHolderRounded.mWpBgRounded).execute();
                viewHolderRounded.mLlBgMainRounded.setBackground(getThemeDayNightBg());
                viewHolderRounded.mQsAccentMainRoundedWifi.setImageDrawable(
                    getShapeDrawable(pathShape, bgQsAccent));
                viewHolderRounded.mQSTileMainRoundedWifiActive.setColorFilter(qsTileIconActive);
                viewHolderRounded.mQsTileMainRoundedBgBluetoothInactive.setImageDrawable(
                    getShapeDrawable(pathShape, qsTileBgInactive));
                viewHolderRounded.mQsTileMainRoundedIconBluetoothInactive.setColorFilter(qsTileIconInactive);
                viewHolderRounded.mQsAccentMainRoundedDnd.setImageDrawable(
                    getShapeDrawable(pathShape, bgQsAccent));
                viewHolderRounded.mQsTileMainRoundedDndActive.setColorFilter(qsTileIconActive);
                viewHolderRounded.mQsTileMainRoundedBgFlashlightInactive.setImageDrawable(
                    getShapeDrawable(pathShape, qsTileBgInactive));
                viewHolderRounded.mQsTileMainRoundedIconFlashlightInactive.setColorFilter(qsTileIconInactive);
                viewHolderRounded.mQsAccentMainRoundedAutorotate.setImageDrawable(
                    getShapeDrawable(pathShape, bgQsAccent));
                viewHolderRounded.mQsTileMainRoundedAutorotateActive.setColorFilter(qsTileIconActive);
                viewHolderRounded.mQsTileMainRoundedBgBatterySaverInactive.setImageDrawable(
                    getShapeDrawable(pathShape, qsTileBgInactive));
                viewHolderRounded.mQsTileMainRoundedIconBatterySaverInactive.setColorFilter(qsTileIconInactive);
                viewHolderRounded.mViewSpacerMainRounded.setBackground(getThemeDayNightSpacer());
                if (threeButtonNavbarEnabled(mContext)) {
                    viewHolderRounded.mViewNavbarMainRounded.setImageDrawable(getNavbarStyle(themeNavbarStyle));
                    viewHolderRounded.mViewNavbarMainRounded.setBackgroundColor(ColorUtils.setAlphaComponent(
                        mResources.getColor(R.color.themes_preview_navbar_bg), 100));
                } else {
                    viewHolderRounded.mViewNavbarMainRounded.setVisibility(View.GONE);
                }
                viewHolderRounded.mThemeMainRoundedName.setText(themeName);
                viewHolderRounded.mThemeMainRoundedName.setTypeface(getTypeface());
                break;
            case 4:
                ViewHolderCircular viewHolderCircular = (ViewHolderCircular) holder;
                new ThemeWallpaper(viewHolderCircular.mWpBgCircular).execute();
                viewHolderCircular.mLlBgMainCircular.setBackground(getThemeDayNightBg());
                viewHolderCircular.mQsAccentMainCircularWifi.setImageDrawable(
                    getShapeDrawable(pathShape, bgQsAccent));
                viewHolderCircular.mQSTileMainCircularWifiActive.setColorFilter(qsTileIconActive);
                viewHolderCircular.mQsTileMainCircularBgBluetoothInactive.setImageDrawable(
                    getShapeDrawable(pathShape, qsTileBgInactive));
                viewHolderCircular.mQsTileMainCircularIconBluetoothInactive.setColorFilter(qsTileIconInactive);
                viewHolderCircular.mQsAccentMainCircularDnd.setImageDrawable(
                    getShapeDrawable(pathShape, bgQsAccent));
                viewHolderCircular.mQsTileMainCircularDndActive.setColorFilter(qsTileIconActive);
                viewHolderCircular.mQsTileMainCircularBgFlashlightInactive.setImageDrawable(
                    getShapeDrawable(pathShape, qsTileBgInactive));
                viewHolderCircular.mQsTileMainCircularIconFlashlightInactive.setColorFilter(qsTileIconInactive);
                viewHolderCircular.mQsAccentMainCircularAutorotate.setImageDrawable(
                    getShapeDrawable(pathShape, bgQsAccent));
                viewHolderCircular.mQsTileMainCircularAutorotateActive.setColorFilter(qsTileIconActive);
                viewHolderCircular.mQsTileMainCircularBgBatterySaverInactive.setImageDrawable(
                    getShapeDrawable(pathShape, qsTileBgInactive));
                viewHolderCircular.mQsTileMainCircularIconBatterySaverInactive.setColorFilter(qsTileIconInactive);
                viewHolderCircular.mViewSpacerMainCircular.setBackground(getThemeDayNightSpacer());
                if (threeButtonNavbarEnabled(mContext)) {
                    viewHolderCircular.mViewNavbarMainCircular.setImageDrawable(getNavbarStyle(themeNavbarStyle));
                    viewHolderCircular.mViewNavbarMainCircular.setBackgroundColor(ColorUtils.setAlphaComponent(
                        mResources.getColor(R.color.themes_preview_navbar_bg), 100));
                } else {
                    viewHolderCircular.mViewNavbarMainCircular.setVisibility(View.GONE);
                }
                viewHolderCircular.mThemeMainCircularName.setText(themeName);
                viewHolderCircular.mThemeMainCircularName.setTypeface(getTypeface());
                break;
            case 5:
                ViewHolderCircletrim viewHolder1 = (ViewHolderCircletrim) holder;
                new ThemeWallpaper(viewHolder1.mWpBgCircletrim).execute();
                viewHolder1.mLlBgMainCircletrim.setBackground(getThemeDayNightBg());
                viewHolder1.mQsAccentMainCircletrimWifi.setImageDrawable(getQSTileStyle(themeQSTileStyle, bgQsAccent));
                viewHolder1.mQSTileMainCircletrimWifiActive.setColorFilter(qsTileIconActive);
                viewHolder1.mQsTileMainCircletrimBgBluetoothInactive.setImageDrawable(
                        getQSTileStyle(themeQSTileStyle, qsTileBgInactive));
                viewHolder1.mQsTileMainCircletrimIconBluetoothInactive.setColorFilter(qsTileIconInactive);
                viewHolder1.mQsAccentMainCircletrimDnd.setImageDrawable(getQSTileStyle(themeQSTileStyle, bgQsAccent));
                viewHolder1.mQsTileMainCircletrimDndActive.setColorFilter(qsTileIconActive);
                viewHolder1.mQsTileMainCircletrimBgFlashlightInactive.setImageDrawable(
                        getQSTileStyle(themeQSTileStyle, qsTileBgInactive));
                viewHolder1.mQsTileMainCircletrimIconFlashlightInactive.setColorFilter(qsTileIconInactive);
                viewHolder1.mQsAccentMainCircletrimAutorotate.setImageDrawable(getQSTileStyle(themeQSTileStyle, bgQsAccent));
                viewHolder1.mQsTileMainCircletrimAutorotateActive.setColorFilter(qsTileIconActive);
                viewHolder1.mQsTileMainCircletrimBgBatterySaverInactive.setImageDrawable(
                        getQSTileStyle(themeQSTileStyle, qsTileBgInactive));
                viewHolder1.mQsTileMainCircletrimIconBatterySaverInactive.setColorFilter(qsTileIconInactive);
                viewHolder1.mViewSpacerMainCircletrim.setBackground(getThemeDayNightSpacer());
                if (threeButtonNavbarEnabled(mContext)) {
                    viewHolder1.mViewNavbarMainCircletrim.setImageDrawable(getNavbarStyle(themeNavbarStyle));
                    viewHolder1.mViewNavbarMainCircletrim.setBackgroundColor(ColorUtils.setAlphaComponent(
                        mResources.getColor(R.color.themes_preview_navbar_bg), 100));
                } else {
                    viewHolder1.mViewNavbarMainCircletrim.setVisibility(View.GONE);
                }
                viewHolder1.mThemeMainCircletrimName.setText(themeName);
                viewHolder1.mThemeMainCircletrimName.setTypeface(getTypeface());
                break;
            case 6:
                ViewHolderDualtonecircletrim viewHolder2 = (ViewHolderDualtonecircletrim) holder;
                new ThemeWallpaper(viewHolder2.mWpBgDualtonecircletrim).execute();
                viewHolder2.mLlBgMainDualtonecircletrim.setBackground(getThemeDayNightBg());
                viewHolder2.mQsAccentMainDualtonecircletrimWifi.setImageDrawable(getQSTileStyle(themeQSTileStyle, bgQsAccent));
                viewHolder2.mQSTileMainDualtonecircletrimWifiActive.setColorFilter(qsTileIconActive);
                viewHolder2.mQsTileMainDualtonecircletrimBgBluetoothInactive.setImageDrawable(
                        getQSTileStyle(themeQSTileStyle, qsTileBgInactive));
                viewHolder2.mQsTileMainDualtonecircletrimIconBluetoothInactive.setColorFilter(qsTileIconInactive);
                viewHolder2.mQsAccentMainDualtonecircletrimDnd.setImageDrawable(getQSTileStyle(themeQSTileStyle, bgQsAccent));
                viewHolder2.mQsTileMainDualtonecircletrimDndActive.setColorFilter(qsTileIconActive);
                viewHolder2.mQsTileMainDualtonecircletrimBgFlashlightInactive.setImageDrawable(
                        getQSTileStyle(themeQSTileStyle, qsTileBgInactive));
                viewHolder2.mQsTileMainDualtonecircletrimIconFlashlightInactive.setColorFilter(qsTileIconInactive);
                viewHolder2.mQsAccentMainDualtonecircletrimAutorotate.setImageDrawable(getQSTileStyle(themeQSTileStyle, bgQsAccent));
                viewHolder2.mQsTileMainDualtonecircletrimAutorotateActive.setColorFilter(qsTileIconActive);
                viewHolder2.mQsTileMainDualtonecircletrimBgBatterySaverInactive.setImageDrawable(
                        getQSTileStyle(themeQSTileStyle, qsTileBgInactive));
                viewHolder2.mQsTileMainDualtonecircletrimIconBatterySaverInactive.setColorFilter(qsTileIconInactive);
                viewHolder2.mViewSpacerMainDualtonecircletrim.setBackground(getThemeDayNightSpacer());
                if (threeButtonNavbarEnabled(mContext)) {
                    viewHolder2.mViewNavbarMainDualtonecircletrim.setImageDrawable(getNavbarStyle(themeNavbarStyle));
                    viewHolder2.mViewNavbarMainDualtonecircletrim.setBackgroundColor(ColorUtils.setAlphaComponent(
                        mResources.getColor(R.color.themes_preview_navbar_bg), 100));
                } else {
                    viewHolder2.mViewNavbarMainDualtonecircletrim.setVisibility(View.GONE);
                }
                viewHolder2.mThemeMainDualtonecircletrimName.setText(themeName);
                viewHolder2.mThemeMainDualtonecircletrimName.setTypeface(getTypeface());
                break;
            case 7:
                ViewHolderSquircletrim viewHolder3 = (ViewHolderSquircletrim) holder;
                new ThemeWallpaper(viewHolder3.mWpBgSquircletrim).execute();
                viewHolder3.mLlBgMainSquircletrim.setBackground(getThemeDayNightBg());
                viewHolder3.mQsAccentMainSquircletrimWifi.setImageDrawable(getQSTileStyle(themeQSTileStyle, bgQsAccent));
                viewHolder3.mQSTileMainSquircletrimWifiActive.setColorFilter(qsTileIconActive);
                viewHolder3.mQsTileMainSquircletrimBgBluetoothInactive.setImageDrawable(
                        getQSTileStyle(themeQSTileStyle, qsTileBgInactive));
                viewHolder3.mQsTileMainSquircletrimIconBluetoothInactive.setColorFilter(qsTileIconInactive);
                viewHolder3.mQsAccentMainSquircletrimDnd.setImageDrawable(getQSTileStyle(themeQSTileStyle, bgQsAccent));
                viewHolder3.mQsTileMainSquircletrimDndActive.setColorFilter(qsTileIconActive);
                viewHolder3.mQsTileMainSquircletrimBgFlashlightInactive.setImageDrawable(
                        getQSTileStyle(themeQSTileStyle, qsTileBgInactive));
                viewHolder3.mQsTileMainSquircletrimIconFlashlightInactive.setColorFilter(qsTileIconInactive);
                viewHolder3.mQsAccentMainSquircletrimAutorotate.setImageDrawable(getQSTileStyle(themeQSTileStyle, bgQsAccent));
                viewHolder3.mQsTileMainSquircletrimAutorotateActive.setColorFilter(qsTileIconActive);
                viewHolder3.mQsTileMainSquircletrimBgBatterySaverInactive.setImageDrawable(
                        getQSTileStyle(themeQSTileStyle, qsTileBgInactive));
                viewHolder3.mQsTileMainSquircletrimIconBatterySaverInactive.setColorFilter(qsTileIconInactive);
                viewHolder3.mViewSpacerMainSquircletrim.setBackground(getThemeDayNightSpacer());
                if (threeButtonNavbarEnabled(mContext)) {
                    viewHolder3.mViewNavbarMainSquircletrim.setImageDrawable(getNavbarStyle(themeNavbarStyle));
                    viewHolder3.mViewNavbarMainSquircletrim.setBackgroundColor(ColorUtils.setAlphaComponent(
                        mResources.getColor(R.color.themes_preview_navbar_bg), 100));
                } else {
                    viewHolder3.mViewNavbarMainSquircletrim.setVisibility(View.GONE);
                }
                viewHolder3.mThemeMainSquircletrimName.setText(themeName);
                viewHolder3.mThemeMainSquircletrimName.setTypeface(getTypeface());
                break;
            case 8:
                ViewHolderAttemptmountain viewHolder4 = (ViewHolderAttemptmountain) holder;
                new ThemeWallpaper(viewHolder4.mWpBgAttemptmountain).execute();
                viewHolder4.mLlBgMainAttemptmountain.setBackground(getThemeDayNightBg());
                viewHolder4.mQsAccentMainAttemptmountainWifi.setImageDrawable(getQSTileStyle(themeQSTileStyle, bgQsAccent));
                viewHolder4.mQSTileMainAttemptmountainWifiActive.setColorFilter(qsTileIconInactive);
                viewHolder4.mQsTileMainAttemptmountainBgBluetoothInactive.setImageDrawable(
                        getQSTileStyle(themeQSTileStyle, qsTileBgInactive));
                viewHolder4.mQsTileMainAttemptmountainIconBluetoothInactive.setColorFilter(qsTileIconInactive);
                viewHolder4.mQsAccentMainAttemptmountainDnd.setImageDrawable(getQSTileStyle(themeQSTileStyle, bgQsAccent));
                viewHolder4.mQsTileMainAttemptmountainDndActive.setColorFilter(qsTileIconInactive);
                viewHolder4.mQsTileMainAttemptmountainBgFlashlightInactive.setImageDrawable(
                        getQSTileStyle(themeQSTileStyle, qsTileBgInactive));
                viewHolder4.mQsTileMainAttemptmountainIconFlashlightInactive.setColorFilter(qsTileIconInactive);
                viewHolder4.mQsAccentMainAttemptmountainAutorotate.setImageDrawable(getQSTileStyle(themeQSTileStyle, bgQsAccent));
                viewHolder4.mQsTileMainAttemptmountainAutorotateActive.setColorFilter(qsTileIconInactive);
                viewHolder4.mQsTileMainAttemptmountainBgBatterySaverInactive.setImageDrawable(
                        getQSTileStyle(themeQSTileStyle, qsTileBgInactive));
                viewHolder4.mQsTileMainAttemptmountainIconBatterySaverInactive.setColorFilter(qsTileIconInactive);
                viewHolder4.mViewSpacerMainAttemptmountain.setBackground(getThemeDayNightSpacer());
                if (threeButtonNavbarEnabled(mContext)) {
                    viewHolder4.mViewNavbarMainAttemptmountain.setImageDrawable(getNavbarStyle(themeNavbarStyle));
                    viewHolder4.mViewNavbarMainAttemptmountain.setBackgroundColor(ColorUtils.setAlphaComponent(
                        mResources.getColor(R.color.themes_preview_navbar_bg), 100));
                } else {
                    viewHolder4.mViewNavbarMainAttemptmountain.setVisibility(View.GONE);
                }
                viewHolder4.mThemeMainAttemptmountainName.setText(themeName);
                viewHolder4.mThemeMainAttemptmountainName.setTypeface(getTypeface());
                break;
            case 9:
                ViewHolderCosmos viewHolder5 = (ViewHolderCosmos) holder;
                new ThemeWallpaper(viewHolder5.mWpBgCosmos).execute();
                viewHolder5.mLlBgMainCosmos.setBackground(getThemeDayNightBg());
                viewHolder5.mQsAccentMainCosmosWifi.setImageDrawable(getQSTileStyle(themeQSTileStyle, bgQsAccent));
                viewHolder5.mQSTileMainCosmosWifiActive.setColorFilter(qsTileIconInactive);
                viewHolder5.mQsTileMainCosmosBgBluetoothInactive.setImageDrawable(
                        getQSTileStyle(themeQSTileStyle, qsTileBgInactive));
                viewHolder5.mQsTileMainCosmosIconBluetoothInactive.setColorFilter(qsTileIconInactive);
                viewHolder5.mQsAccentMainCosmosDnd.setImageDrawable(getQSTileStyle(themeQSTileStyle, bgQsAccent));
                viewHolder5.mQsTileMainCosmosDndActive.setColorFilter(qsTileIconInactive);
                viewHolder5.mQsTileMainCosmosBgFlashlightInactive.setImageDrawable(
                        getQSTileStyle(themeQSTileStyle, qsTileBgInactive));
                viewHolder5.mQsTileMainCosmosIconFlashlightInactive.setColorFilter(qsTileIconInactive);
                viewHolder5.mQsAccentMainCosmosAutorotate.setImageDrawable(getQSTileStyle(themeQSTileStyle, bgQsAccent));
                viewHolder5.mQsTileMainCosmosAutorotateActive.setColorFilter(qsTileIconInactive);
                viewHolder5.mQsTileMainCosmosBgBatterySaverInactive.setImageDrawable(
                        getQSTileStyle(themeQSTileStyle, qsTileBgInactive));
                viewHolder5.mQsTileMainCosmosIconBatterySaverInactive.setColorFilter(qsTileIconInactive);
                viewHolder5.mViewSpacerMainCosmos.setBackground(getThemeDayNightSpacer());
                if (threeButtonNavbarEnabled(mContext)) {
                    viewHolder5.mViewNavbarMainCosmos.setImageDrawable(getNavbarStyle(themeNavbarStyle));
                    viewHolder5.mViewNavbarMainCosmos.setBackgroundColor(ColorUtils.setAlphaComponent(
                        mResources.getColor(R.color.themes_preview_navbar_bg), 100));
                } else {
                    viewHolder5.mViewNavbarMainCosmos.setVisibility(View.GONE);
                }
                viewHolder5.mThemeMainCosmosName.setText(themeName);
                viewHolder5.mThemeMainCosmosName.setTypeface(getTypeface());
                break;
            case 10:
                ViewHolderDottedcircle viewHolder6 = (ViewHolderDottedcircle) holder;
                new ThemeWallpaper(viewHolder6.mWpBgDottedcircle).execute();
                viewHolder6.mLlBgMainDottedcircle.setBackground(getThemeDayNightBg());
                viewHolder6.mQsAccentMainDottedcircleWifi.setImageDrawable(getQSTileStyle(themeQSTileStyle, bgQsAccent));
                viewHolder6.mQSTileMainDottedcircleWifiActive.setColorFilter(qsTileIconInactive);
                viewHolder6.mQsTileMainDottedcircleBgBluetoothInactive.setImageDrawable(
                        getQSTileStyle(themeQSTileStyle, qsTileBgInactive));
                viewHolder6.mQsTileMainDottedcircleIconBluetoothInactive.setColorFilter(qsTileIconInactive);
                viewHolder6.mQsAccentMainDottedcircleDnd.setImageDrawable(getQSTileStyle(themeQSTileStyle, bgQsAccent));
                viewHolder6.mQsTileMainDottedcircleDndActive.setColorFilter(qsTileIconInactive);
                viewHolder6.mQsTileMainDottedcircleBgFlashlightInactive.setImageDrawable(
                        getQSTileStyle(themeQSTileStyle, qsTileBgInactive));
                viewHolder6.mQsTileMainDottedcircleIconFlashlightInactive.setColorFilter(qsTileIconInactive);
                viewHolder6.mQsAccentMainDottedcircleAutorotate.setImageDrawable(getQSTileStyle(themeQSTileStyle, bgQsAccent));
                viewHolder6.mQsTileMainDottedcircleAutorotateActive.setColorFilter(qsTileIconInactive);
                viewHolder6.mQsTileMainDottedcircleBgBatterySaverInactive.setImageDrawable(
                        getQSTileStyle(themeQSTileStyle, qsTileBgInactive));
                viewHolder6.mQsTileMainDottedcircleIconBatterySaverInactive.setColorFilter(qsTileIconInactive);
                viewHolder6.mViewSpacerMainDottedcircle.setBackground(getThemeDayNightSpacer());
                if (threeButtonNavbarEnabled(mContext)) {
                    viewHolder6.mViewNavbarMainDottedcircle.setImageDrawable(getNavbarStyle(themeNavbarStyle));
                    viewHolder6.mViewNavbarMainDottedcircle.setBackgroundColor(ColorUtils.setAlphaComponent(
                        mResources.getColor(R.color.themes_preview_navbar_bg), 100));
                } else {
                    viewHolder6.mViewNavbarMainDottedcircle.setVisibility(View.GONE);
                }
                viewHolder6.mThemeMainDottedcircleName.setText(themeName);
                viewHolder6.mThemeMainDottedcircleName.setTypeface(getTypeface());
                break;
            case 11:
                ViewHolderNinja viewHolder7 = (ViewHolderNinja) holder;
                new ThemeWallpaper(viewHolder7.mWpBgNinja).execute();
                viewHolder7.mLlBgMainNinja.setBackground(getThemeDayNightBg());
                viewHolder7.mQsAccentMainNinjaWifi.setImageDrawable(getQSTileStyle(themeQSTileStyle, bgQsAccent));
                viewHolder7.mQSTileMainNinjaWifiActive.setColorFilter(qsTileIconActive);
                viewHolder7.mQsTileMainNinjaBgBluetoothInactive.setImageDrawable(
                        getQSTileStyle(themeQSTileStyle, qsTileBgInactive));
                viewHolder7.mQsTileMainNinjaIconBluetoothInactive.setColorFilter(qsTileIconInactive);
                viewHolder7.mQsAccentMainNinjaDnd.setImageDrawable(getQSTileStyle(themeQSTileStyle, bgQsAccent));
                viewHolder7.mQsTileMainNinjaDndActive.setColorFilter(qsTileIconActive);
                viewHolder7.mQsTileMainNinjaBgFlashlightInactive.setImageDrawable(
                        getQSTileStyle(themeQSTileStyle, qsTileBgInactive));
                viewHolder7.mQsTileMainNinjaIconFlashlightInactive.setColorFilter(qsTileIconInactive);
                viewHolder7.mQsAccentMainNinjaAutorotate.setImageDrawable(getQSTileStyle(themeQSTileStyle, bgQsAccent));
                viewHolder7.mQsTileMainNinjaAutorotateActive.setColorFilter(qsTileIconActive);
                viewHolder7.mQsTileMainNinjaBgBatterySaverInactive.setImageDrawable(
                        getQSTileStyle(themeQSTileStyle, qsTileBgInactive));
                viewHolder7.mQsTileMainNinjaIconBatterySaverInactive.setColorFilter(qsTileIconInactive);
                viewHolder7.mViewSpacerMainNinja.setBackground(getThemeDayNightSpacer());
                if (threeButtonNavbarEnabled(mContext)) {
                    viewHolder7.mViewNavbarMainNinja.setImageDrawable(getNavbarStyle(themeNavbarStyle));
                    viewHolder7.mViewNavbarMainNinja.setBackgroundColor(ColorUtils.setAlphaComponent(
                        mResources.getColor(R.color.themes_preview_navbar_bg), 100));
                } else {
                    viewHolder7.mViewNavbarMainNinja.setVisibility(View.GONE);
                }
                viewHolder7.mThemeMainNinjaName.setText(themeName);
                viewHolder7.mThemeMainNinjaName.setTypeface(getTypeface());
                break;
            case 12:
                ViewHolderPokesign viewHolder8 = (ViewHolderPokesign) holder;
                new ThemeWallpaper(viewHolder8.mWpBgPokesign).execute();
                viewHolder8.mLlBgMainPokesign.setBackground(getThemeDayNightBg());
                viewHolder8.mQsAccentMainPokesignWifi.setImageDrawable(getQSTileStyle(themeQSTileStyle, bgQsAccent));
                viewHolder8.mQSTileMainPokesignWifiActive.setColorFilter(qsTileIconActive);
                viewHolder8.mQsTileMainPokesignBgBluetoothInactive.setImageDrawable(
                        getQSTileStyle(themeQSTileStyle, qsTileBgInactive));
                viewHolder8.mQsTileMainPokesignIconBluetoothInactive.setColorFilter(qsTileIconInactive);
                viewHolder8.mQsAccentMainPokesignDnd.setImageDrawable(getQSTileStyle(themeQSTileStyle, bgQsAccent));
                viewHolder8.mQsTileMainPokesignDndActive.setColorFilter(qsTileIconActive);
                viewHolder8.mQsTileMainPokesignBgFlashlightInactive.setImageDrawable(
                        getQSTileStyle(themeQSTileStyle, qsTileBgInactive));
                viewHolder8.mQsTileMainPokesignIconFlashlightInactive.setColorFilter(qsTileIconInactive);
                viewHolder8.mQsAccentMainPokesignAutorotate.setImageDrawable(getQSTileStyle(themeQSTileStyle, bgQsAccent));
                viewHolder8.mQsTileMainPokesignAutorotateActive.setColorFilter(qsTileIconActive);
                viewHolder8.mQsTileMainPokesignBgBatterySaverInactive.setImageDrawable(
                        getQSTileStyle(themeQSTileStyle, qsTileBgInactive));
                viewHolder8.mQsTileMainPokesignIconBatterySaverInactive.setColorFilter(qsTileIconInactive);
                viewHolder8.mViewSpacerMainPokesign.setBackground(getThemeDayNightSpacer());
                if (threeButtonNavbarEnabled(mContext)) {
                    viewHolder8.mViewNavbarMainPokesign.setImageDrawable(getNavbarStyle(themeNavbarStyle));
                    viewHolder8.mViewNavbarMainPokesign.setBackgroundColor(ColorUtils.setAlphaComponent(
                        mResources.getColor(R.color.themes_preview_navbar_bg), 100));
                } else {
                    viewHolder8.mViewNavbarMainPokesign.setVisibility(View.GONE);
                }
                viewHolder8.mThemeMainPokesignName.setText(themeName);
                viewHolder8.mThemeMainPokesignName.setTypeface(getTypeface());
                break;
            case 13:
                ViewHolderWavey viewHolder9 = (ViewHolderWavey) holder;
                new ThemeWallpaper(viewHolder9.mWpBgWavey).execute();
                viewHolder9.mLlBgMainWavey.setBackground(getThemeDayNightBg());
                viewHolder9.mQsAccentMainWaveyWifi.setImageDrawable(getQSTileStyle(themeQSTileStyle, bgQsAccent));
                viewHolder9.mQSTileMainWaveyWifiActive.setColorFilter(qsTileIconActive);
                viewHolder9.mQsTileMainWaveyBgBluetoothInactive.setImageDrawable(
                        getQSTileStyle(themeQSTileStyle, qsTileBgInactive));
                viewHolder9.mQsTileMainWaveyIconBluetoothInactive.setColorFilter(qsTileIconInactive);
                viewHolder9.mQsAccentMainWaveyDnd.setImageDrawable(getQSTileStyle(themeQSTileStyle, bgQsAccent));
                viewHolder9.mQsTileMainWaveyDndActive.setColorFilter(qsTileIconActive);
                viewHolder9.mQsTileMainWaveyBgFlashlightInactive.setImageDrawable(
                        getQSTileStyle(themeQSTileStyle, qsTileBgInactive));
                viewHolder9.mQsTileMainWaveyIconFlashlightInactive.setColorFilter(qsTileIconInactive);
                viewHolder9.mQsAccentMainWaveyAutorotate.setImageDrawable(getQSTileStyle(themeQSTileStyle, bgQsAccent));
                viewHolder9.mQsTileMainWaveyAutorotateActive.setColorFilter(qsTileIconActive);
                viewHolder9.mQsTileMainWaveyBgBatterySaverInactive.setImageDrawable(
                        getQSTileStyle(themeQSTileStyle, qsTileBgInactive));
                viewHolder9.mQsTileMainWaveyIconBatterySaverInactive.setColorFilter(qsTileIconInactive);
                viewHolder9.mViewSpacerMainWavey.setBackground(getThemeDayNightSpacer());
                if (threeButtonNavbarEnabled(mContext)) {
                    viewHolder9.mViewNavbarMainWavey.setImageDrawable(getNavbarStyle(themeNavbarStyle));
                    viewHolder9.mViewNavbarMainWavey.setBackgroundColor(ColorUtils.setAlphaComponent(
                        mResources.getColor(R.color.themes_preview_navbar_bg), 100));
                } else {
                    viewHolder9.mViewNavbarMainWavey.setVisibility(View.GONE);
                }
                viewHolder9.mThemeMainWaveyName.setText(themeName);
                viewHolder9.mThemeMainWaveyName.setTypeface(getTypeface());
                break;
        }
    }

    public class ThemeWallpaper extends AsyncTask<Void, Void, Bitmap> {
        ImageView imageView;

        public ThemeWallpaper(ImageView imgView) {
            imageView = imgView;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected Bitmap doInBackground(Void... params) {
            File file = new File(mThemeWpBackup);
            Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
            return bitmap;
        }

        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
        }
    }

    private Drawable getThemeDayNightBg() {
        Drawable themeDayNightBg = mResources.getDrawable(R.drawable.ic_themes_qs_background_restore);
        themeDayNightBg.setColorFilter(mThemeNightColor, Mode.SRC_IN);
        return themeDayNightBg;
    }

    private Drawable getThemeDayNightSpacer() {
        Drawable themeDayNightSpacer = mResources.getDrawable(R.drawable.ic_themes_qs_spacer_restore);
        int themeDayNightSpacerColor;
        if (!mIsNightMode) {
            themeDayNightSpacerColor = mResources.getColor(R.color.qs_tile_panel_spacer_day);
        } else {
            themeDayNightSpacerColor = mResources.getColor(R.color.qs_tile_panel_spacer_night);
        }
        themeDayNightSpacer.setColorFilter(themeDayNightSpacerColor, Mode.SRC_IN);
        return themeDayNightSpacer;
    }

    private Typeface getTypeface() {
        Typeface fontType = null;
        switch (mThemeFont) {
            case 1:
                fontType = Typeface.create(Typeface.DEFAULT, 400, false);
                break;
            case 2:
                fontType = Typeface.createFromAsset(mResources.getAssets(), "fonts/Aclonica.ttf");
                break;
            case 3:
                fontType = Typeface.createFromAsset(mResources.getAssets(), "fonts/Amarante.ttf");
                break;
            case 4:
                fontType = Typeface.createFromAsset(mResources.getAssets(), "fonts/ArbutusSlab-Regular.ttf");
                break;
            case 5:
                fontType = Typeface.createFromAsset(mResources.getAssets(), "fonts/Bariol.ttf");
                break;
            case 6:
                fontType = Typeface.createFromAsset(mResources.getAssets(), "fonts/Cagliostro.ttf");
                break;
            case 7:
                fontType = Typeface.createFromAsset(mResources.getAssets(), "fonts/ComicSans.ttf");
                break;
            case 8:
                fontType = Typeface.createFromAsset(mResources.getAssets(), "fonts/CoolStory.ttf");
                break;
            case 9:
                fontType = Typeface.createFromAsset(mResources.getAssets(), "fonts/FiraSans.ttf");
                break;
            case 10:
                fontType = Typeface.createFromAsset(mResources.getAssets(), "fonts/GoogleSans.ttf");
                break;
            case 11:
                fontType = Typeface.createFromAsset(mResources.getAssets(), "fonts/GoogleSans-Medium.ttf");
                break;
            case 12:
                fontType = Typeface.createFromAsset(mResources.getAssets(), "fonts/LGSmartGothic.ttf");
                break;
            case 13:
                fontType = Typeface.createFromAsset(mResources.getAssets(), "fonts/Linotte.ttf");
                break;
            case 14:
                fontType = Typeface.createFromAsset(mResources.getAssets(), "fonts/SlateFromOP.ttf");
                break;
            case 15:
                fontType = Typeface.createFromAsset(mResources.getAssets(), "fonts/Rosemary.ttf");
                break;
            case 16:
                fontType = Typeface.createFromAsset(mResources.getAssets(), "fonts/SamsungOne.ttf");
                break;
            case 17:
                fontType = Typeface.createFromAsset(mResources.getAssets(), "fonts/SanFrancisco.ttf");
                break;
            case 18:
                fontType = Typeface.createFromAsset(mResources.getAssets(), "fonts/SonySketch.ttf");
                break;
            case 19:
                fontType = Typeface.createFromAsset(mResources.getAssets(), "fonts/SummerDream.ttf");
                break;
            case 20:
                fontType = Typeface.createFromAsset(mResources.getAssets(), "fonts/Surfer.ttf");
                break;
        }
        return fontType;
    }

    private ShapeDrawable getShapeDrawable(String path, int color) {
        ShapeDrawable shapeDrawable = new ShapeDrawable();
        final float pathSize = AdaptiveIconDrawable.MASK_SIZE;
        final Path shapePath = new Path(PathParser.createPathFromPathData(path));
        final int shapeSize = mResources.getDimensionPixelSize(R.dimen.dashboard_tile_image_size);
        shapeDrawable.setIntrinsicWidth(shapeSize);
        shapeDrawable.setIntrinsicHeight(shapeSize);
        shapeDrawable.getPaint().setColor(color);
        shapeDrawable.getPaint().setStyle(Paint.Style.FILL);
        shapeDrawable.getPaint().setAntiAlias(true);
        shapeDrawable.getPaint().setFlags(Paint.ANTI_ALIAS_FLAG);
        shapeDrawable.setShape(new PathShape(shapePath, pathSize, pathSize));
        return shapeDrawable;
    }

    private Drawable getNavbarStyle(String navStyle) {
        Drawable navbarStyle = null;
        switch (navStyle) {
            case "com.android.theme.navbar.android":
                navbarStyle = mResources.getDrawable(R.drawable.navbar_android_layer);
                break;
            case "com.android.theme.navbar.asus":
                navbarStyle = mResources.getDrawable(R.drawable.navbar_asus_layer);
                break;
            case "com.android.theme.navbar.moto":
                navbarStyle = mResources.getDrawable(R.drawable.navbar_moto_layer);
                break;
            case "com.android.theme.navbar.nexus":
                navbarStyle = mResources.getDrawable(R.drawable.navbar_nexus_layer);
                break;
            case "com.android.theme.navbar.old":
                navbarStyle = mResources.getDrawable(R.drawable.navbar_old_layer);
                break;
            case "com.android.theme.navbar.oneplus":
                navbarStyle = mResources.getDrawable(R.drawable.navbar_oneplus_layer);
                break;
            case "com.android.theme.navbar.oneui":
                navbarStyle = mResources.getDrawable(R.drawable.navbar_oneui_layer);
                break;
            case "com.android.theme.navbar.pd":
                navbarStyle = mResources.getDrawable(R.drawable.navbar_pd_layer);
                break;
            case "com.android.theme.navbar.sammy":
                navbarStyle = mResources.getDrawable(R.drawable.navbar_sammy_layer);
                break;
            case "com.android.theme.navbar.tecno":
                navbarStyle = mResources.getDrawable(R.drawable.navbar_tecno_layer);
                break;
            case "default":
                navbarStyle = mResources.getDrawable(R.drawable.navbar_stock_layer);
                break;
        }
        navbarStyle.setColorFilter(mResources.getColor(android.R.color.white), Mode.SRC_IN);

        return navbarStyle;
    }

    private Drawable getQSTileStyle(String tileStyle, int color) {
        Drawable qsTileStyle = null;
        switch (tileStyle) {
            case "com.android.systemui.qstile.circletrim":
                qsTileStyle = mResources.getDrawable(R.drawable.qstile_circle_trim);
                break;
            case "com.android.systemui.qstile.dualtonecircletrim":
                qsTileStyle = mResources.getDrawable(R.drawable.qstile_dual_tone_circle);
                break;
            case "com.android.systemui.qstile.squircletrim":
                qsTileStyle = mResources.getDrawable(R.drawable.qstile_squircle_trim);
                break;
            case "com.android.systemui.qstile.attemptmountain":
                qsTileStyle = mResources.getDrawable(R.drawable.qstile_attempt_mountain);
                break;
            case "com.android.systemui.qstile.cosmos":
                qsTileStyle = mResources.getDrawable(R.drawable.qstile_cosmos);
                break;
            case "com.android.systemui.qstile.dottedcircle":
                qsTileStyle = mResources.getDrawable(R.drawable.qstile_dotted_circle);
                break;
            case "com.android.systemui.qstile.ninja":
                qsTileStyle = mResources.getDrawable(R.drawable.qstile_ninja);
                break;
            case "com.android.systemui.qstile.pokesign":
                qsTileStyle = mResources.getDrawable(R.drawable.qstile_poke_sign);
                break;
            case "com.android.systemui.qstile.wavey":
                qsTileStyle = mResources.getDrawable(R.drawable.qstile_wavey);
                break;
            case "com.android.systemui.qstile.default":
                qsTileStyle = mResources.getDrawable(R.drawable.qs_styles_shape_square);
                break;
        }
        qsTileStyle.setColorFilter(color, Mode.SRC_IN);

        return qsTileStyle;
    }

    @Override
    public int getItemCount() {
        return mThemesList.size();
    }
}
