package com.example.myrealestate.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class UserPreferences {

    private static final String USER_PREFERENCES_KEY = "userPreference";

    private UserPreferences() { }

    public static void saveUserAgentProfile(@NonNull Context context, @NonNull String login) {
        final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = defaultSharedPreferences.edit();
        editor.putString(UserPreferences.USER_PREFERENCES_KEY, login);
        editor.apply();
    }

    @Nullable
    public static String getUserAgentProfile(@NonNull Context context) {
        final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return defaultSharedPreferences.getString(UserPreferences.USER_PREFERENCES_KEY, null);
    }
}
