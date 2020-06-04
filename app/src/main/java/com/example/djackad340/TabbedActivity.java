package com.example.djackad340;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.djackad340.tabs.MatchesFragment;
import com.example.djackad340.tabs.ProfileFragment;
import com.example.djackad340.tabs.SettingsFragment;
import com.example.djackad340.viewmodel.MatchViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


import java.util.ArrayList;
import java.util.List;

public class TabbedActivity extends AppCompatActivity implements OnListFragmentInteractionListener {
    private MatchViewModel viewModel;
    private static final String TAG = TabbedActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);

        viewModel = new MatchViewModel();

        Intent mainIntent = getIntent();
        Bundle bundleIntent = mainIntent.getExtras();

        Bundle matchesBundle = new Bundle();
        viewModel.getMatchItems(
                (ArrayList<MatchItem> matchItems) -> {
                    Log.i(TAG, matchItems.toString());
                    matchesBundle.putParcelableArrayList(Constants.MATCHES, matchItems);
                }
        );

        MatchesFragment matchesFragment = new MatchesFragment();

        matchesFragment.setArguments(matchesBundle);

        ProfileFragment profileFragment = new ProfileFragment();
        SettingsFragment settingsFragment = new SettingsFragment();

        profileFragment.setArguments(bundleIntent);
        ViewPager2 viewPager = findViewById(R.id.view_pager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);

        viewPagerAdapter.addFragment(profileFragment);
        viewPagerAdapter.addFragment(matchesFragment);
        viewPagerAdapter.addFragment(settingsFragment);

        viewPager.setAdapter(viewPagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    if (position == 0) {
                        tab.setText(Constants.PROFILE_TAB);
                    } else if (position == 1) {
                        tab.setText(Constants.MATCHES_TAB);
                    } else {
                        tab.setText(Constants.SETTINGS_TAB);
                    }
                }
        ).attach();
    }


    @Override
    public void onListFragmentInteraction(MatchItem item) {
        item.liked = !item.liked;
        viewModel.updateMatchItem(item);
    }

    public static class ViewPagerAdapter extends FragmentStateAdapter {

        private List<Fragment> fragments = new ArrayList<>();

        ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragments.get(position);
        }

        void addFragment(Fragment fragment) {
            fragments.add(fragment);
        }

        @Override
        public int getItemCount() {
            return fragments.size();
        }
    }
}
