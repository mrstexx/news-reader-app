package at.technikum_wien.miljevic.newsreader.settings;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import at.technikum_wien.miljevic.newsreader.R;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_general);
    }
}
