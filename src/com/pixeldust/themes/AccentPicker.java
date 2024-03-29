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
import android.os.UserHandle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.preference.PreferenceManager;

import com.android.internal.util.pixeldust.ThemesUtils;
import com.android.internal.util.pixeldust.PixeldustUtils;

public class AccentPicker extends DialogFragment {

    public static final String TAG_ACCENT_PICKER = "accent_picker";

    private Context mContext;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mSharedPreferencesEditor;
    private String[] mAccentButtons;
    private View mView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        mSharedPreferencesEditor = mSharedPreferences.edit();
        mAccentButtons = getResources().getStringArray(R.array.accent_picker_buttons);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(
                getActivity(), R.style.AccentDialogTheme);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        mView = inflater.inflate(R.layout.accent_picker, null);

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
                mSharedPreferencesEditor.remove("theme_accent_color").apply();
                PixeldustUtils.reloadIcons(mContext);
                dialog.dismiss();
            }
        });

        builder.setView(mView);

        return builder.create();
    }

    private void initView() {
        String colorVal = mSharedPreferences.getString("theme_accent_color", "hex");
        final String prefix = "hex";
        if (colorVal.startsWith(prefix) ) {
            Settings.Secure.putStringForUser(mContext.getContentResolver(), Settings.Secure.ACCENT_DARK,
                    null, UserHandle.USER_CURRENT);
            mSharedPreferencesEditor.remove("theme_accent_color").apply();
        }
        for (int i = 0; i < mAccentButtons.length; i++) {
            int buttonId = getResources().getIdentifier(mAccentButtons[i], "id", mContext.getPackageName());
            Button button = (Button) mView.findViewById(buttonId);
            String accent = ThemesUtils.ACCENTS[i];
            setAccent(accent, button);
            setForegroundDrawable(accent, button, getActivity());
        }
    }

    private void setAccent(final String accent, Button buttonAccent) {
        if (buttonAccent != null) {
            buttonAccent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSharedPreferencesEditor.putString("theme_accent_color", accent);
                    mSharedPreferencesEditor.apply();
                    PixeldustUtils.reloadIcons(mContext);
                    dismiss();
                }
            });
        }
    }
}
