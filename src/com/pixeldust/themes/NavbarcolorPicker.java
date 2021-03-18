/*
 * Copyright (C) 2018-2020 The Dirty Unicorns Project
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

import static com.pixeldust.themes.utils.Utils.setForegroundDrawable;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.preference.PreferenceManager;

import com.android.internal.util.pixeldust.ThemesUtils;

public class NavbarcolorPicker extends DialogFragment {

    public static final String TAG_NAVBARCOLOR_PICKER = "navbarcolor_picker";

    private Context mContext;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mSharedPreferencesEditor;
    private String[] mColorButtons;
    private View mView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        mSharedPreferencesEditor = mSharedPreferences.edit();
        mColorButtons = getResources().getStringArray(R.array.navbarcolor_picker_buttons);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(
                getActivity(), R.style.AccentDialogTheme);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        mView = inflater.inflate(R.layout.navbarcolor_picker, null);

        if (mView != null) {
            initView();
        }

        builder.setNegativeButton(mContext.getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.setNeutralButton(mContext.getString(R.string.theme_accent_picker_default), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                mSharedPreferencesEditor.remove("theme_navbar_color").apply();
                dialog.dismiss();
            }
        });

        builder.setView(mView);

        return builder.create();
    }

    private void initView() {
        for (int i = 0; i < mColorButtons.length; i++) {
            int buttonId = getResources().getIdentifier(mColorButtons[i], "id", mContext.getPackageName());
            Button button = (Button) mView.findViewById(buttonId);
            String color = ThemesUtils.NAVBAR_COLORS[i];
            setColor(color, button);
            setForegroundDrawable(color, button, getActivity());
        }
    }

    private void setColor(final String color, Button buttonColor) {
        if (buttonColor != null) {
            buttonColor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSharedPreferencesEditor.putString("theme_navbar_color", color);
                    mSharedPreferencesEditor.apply();
                    dismiss();
                }
            });
        }
    }
}
