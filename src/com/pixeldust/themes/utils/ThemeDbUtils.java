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

package com.pixeldust.themes.utils;

public class ThemeDbUtils {

    private int mId;
    private String mThemeName;
    private String mThemeDayOrNight;
    private String mThemeAccent;
    private String mThemeNightColor;
    private String mAccentPicker;
    private String mThemeSwitch;
    private String mAdaptativeIconShape;
    private String mThemeFont;
    private String mThemeIconShape;
    private String mThemeSbIcons;
    private String mThemeWp;
    private String mThemeNavbarStyle;
    private String mThemeNavbarColor;
    private String mThemeQSTileStyle;
    private String mThemeSBHeight;
    private String mThemeUIRadius;
    private String mThemeSliderStyle;
    private String mThemeSwitchStyle;

    public ThemeDbUtils() {
        //
    }

    public ThemeDbUtils(int id, String themeName, String themeDayOrNight, String themeAccent, String themeNightColor,
                String accentPicker, String themeSwitch, String adaptativeIconShape, String themeFont,
                String themeIconShape, String themeSbIcons, String themeWp, String themeNavbarStyle,
                String themeNavbarColor, String themeQSTileStyle, String themeSBHeight, String themeUIRadius,
                String themeSliderStyle, String themeSwitchStyle) {
        mId = id;
        mThemeName = themeName;
        mThemeDayOrNight = themeDayOrNight;
        mThemeAccent = themeAccent;
        mThemeNightColor = themeNightColor;
        mAccentPicker = accentPicker;
        mThemeSwitch = themeSwitch;
        mAdaptativeIconShape = adaptativeIconShape;
        mThemeFont = themeFont;
        mThemeIconShape = themeIconShape;
        mThemeSbIcons = themeSbIcons;
        mThemeWp = themeWp;
        mThemeNavbarStyle = themeNavbarStyle;
        mThemeNavbarColor = themeNavbarColor;
        mThemeQSTileStyle = themeQSTileStyle;
        mThemeSBHeight = themeSBHeight;
        mThemeUIRadius = themeUIRadius;
        mThemeSliderStyle = themeSliderStyle;
        mThemeSwitchStyle = themeSwitchStyle;
    }

    public ThemeDbUtils(String themeName, String themeDayOrNight, String themeAccent, String themeNightColor,
                String accentPicker, String themeSwitch, String adaptativeIconShape, String themeFont,
                String themeIconShape, String themeSbIcons, String themeWp, String themeNavbarStyle,
                String themeNavbarColor, String themeQSTileStyle, String themeSBHeight, String themeUIRadius,
                String themeSliderStyle, String themeSwitchStyle) {
        mThemeName = themeName;
        mThemeDayOrNight = themeDayOrNight;
        mThemeAccent = themeAccent;
        mThemeNightColor = themeNightColor;
        mAccentPicker = accentPicker;
        mThemeSwitch = themeSwitch;
        mAdaptativeIconShape = adaptativeIconShape;
        mThemeFont = themeFont;
        mThemeIconShape = themeIconShape;
        mThemeSbIcons = themeSbIcons;
        mThemeWp = themeWp;
        mThemeNavbarStyle = themeNavbarStyle;
        mThemeNavbarColor = themeNavbarColor;
        mThemeQSTileStyle = themeQSTileStyle;
        mThemeSBHeight = themeSBHeight;
        mThemeUIRadius = themeUIRadius;
        mThemeSliderStyle = themeSliderStyle;
        mThemeSwitchStyle = themeSwitchStyle;
    }

    public int getID() {
        return mId;
    }

    public void setID(int id) {
        mId = id;
    }

    public String getThemeName() {
        return mThemeName;
    }

    public void setThemeName(String themeName) {
        mThemeName = themeName;
    }

    public String getThemeDayOrNight() {
        return mThemeDayOrNight;
    }

    public void setThemeDayOrNight(String themeDayOrNight) {
        mThemeDayOrNight = themeDayOrNight;
    }

    public String getThemeAccent() {
        return mThemeAccent;
    }

    public void setThemeAccent(String themeAccent) {
        mThemeAccent = themeAccent;
    }

    public String getThemeNightColor() {
        return mThemeNightColor;
    }

    public void setThemeNightColor(String themeNightColor) {
        mThemeNightColor = themeNightColor;
    }

    public String getAccentPicker() {
        return mAccentPicker;
    }

    public void setAccentPicker(String accentPicker) {
        mAccentPicker = accentPicker;
    }

    public String getThemeSwitch() {
        return mThemeSwitch;
    }

    public void setThemeSwitch(String themeSwitch) {
        mThemeSwitch = themeSwitch;
    }

    public String getAdaptiveIconShape() {
        return mAdaptativeIconShape;
    }

    public void setAdaptiveIconShape(String adaptativeIconShape) {
        mAdaptativeIconShape = adaptativeIconShape;
    }

    public String getThemeFont() {
        return mThemeFont;
    }

    public void setThemeFont(String themeFont) {
        mThemeFont = themeFont;
    }

    public String getThemeIconShape() {
        return mThemeIconShape;
    }

    public void setThemeIconShape(String themeIconShape) {
        mThemeIconShape = themeIconShape;
    }

    public String getThemeSbIcons() {
        return mThemeSbIcons;
    }

    public void setThemeSbIcons(String themeSbIcons) {
        mThemeSbIcons = themeSbIcons;
    }

    public String getThemeWp() {
        return mThemeWp;
    }

    public void setThemeWp(String themeWp) {
        mThemeWp = themeWp;
    }

    public String getThemeNavbarStyle() {
        return mThemeNavbarStyle;
    }

    public void setThemeNavbarStyle(String themeNavbarStyle) {
        mThemeNavbarStyle = themeNavbarStyle;
    }

    public String getThemeQSTileStyle() {
        return mThemeQSTileStyle;
    }

    public void setThemeQSTileStyle(String themeQSTileStyle) {
        mThemeQSTileStyle = themeQSTileStyle;
    }

    public String getThemeNavbarColor() {
        return mThemeNavbarColor;
    }

    public void setThemeNavbarColor(String themeNavbarColor) {
        mThemeNavbarColor = themeNavbarColor;
    }

    public String getThemeSBHeight() {
        return mThemeSBHeight;
    }

    public void setThemeSBHeight(String themeSBHeight) {
        mThemeSBHeight = themeSBHeight;
    }

    public String getThemeUIRadius() {
        return mThemeUIRadius;
    }

    public void setThemeUIRadius(String themeUIRadius) {
        mThemeUIRadius = themeUIRadius;
    }

    public String getThemeSliderStyle() {
        return mThemeSliderStyle;
    }

    public void setThemeSliderStyle(String themeSliderStyle) {
        mThemeSliderStyle = themeSliderStyle;
    }

    public String getThemeSwitchStyle() {
        return mThemeSwitchStyle;
    }

    public void setThemeSwitchStyle(String themeSwitchStyle) {
        mThemeSwitchStyle = themeSwitchStyle;
    }
}
