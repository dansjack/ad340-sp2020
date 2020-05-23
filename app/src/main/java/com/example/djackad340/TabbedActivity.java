package com.example.djackad340;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class TabbedActivity extends AppCompatActivity implements MatchesFragment.OnListFragmentInteractionListener {
    private MatchViewModel viewModel;
    private static final String TAG = TabbedActivity.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);

        Intent mainIntent = getIntent();
        Bundle bundleIntent = mainIntent.getExtras();

        MatchesFragment matchesFragment = new MatchesFragment();
        ProfileFragment profileFragment = new ProfileFragment();
        SettingsFragment settingsFragment = new SettingsFragment();
        profileFragment.setArguments(bundleIntent);
        ViewPager2 viewPager = findViewById(R.id.view_pager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPagerAdapter.addFragment(profileFragment);
        viewPagerAdapter.addFragment(matchesFragment);
        viewPagerAdapter.addFragment(settingsFragment);

        viewModel = new MatchViewModel();

        viewModel.getMatchItems(
            (ArrayList<MatchItem> matchItems) -> {
//                FragmentManager manager = getSupportFragmentManager();
//                MatchesFragment fragment = (MatchesFragment) manager.findFragmentByTag("MatchesFragment");

//                if (fragment != null) {
//                    // Remove fragment to re-add it
//                    FragmentTransaction transaction = manager.beginTransaction();
//                    transaction.remove(fragment);
//                    transaction.commit();
//                }

                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(Constants.MATCHES, matchItems);

                matchesFragment.setArguments(bundle);

//                FragmentTransaction transaction = manager.beginTransaction();
//                transaction.add(R.id.recycler_view, matchesFragment, "MatchesFragment");
//                transaction.commit();
            }
        );

        viewPager.setAdapter(viewPagerAdapter);




        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    if (position == 0) {
                        tab.setText("Profile");
                    } else if (position == 1) {
                        tab.setText("Matches");
                    } else if (position == 2) {
                        tab.setText("Settings");
                    }
                }
        ).attach();
    }


    @Override
    public void onListFragmentInteraction(MatchItem item) {
        item.liked = !item.liked;
        viewModel.updateMatchItem(item);
    }

    @Override
    protected void onPause() {
        viewModel.clear();
        super.onPause();
    }

    public MatchViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(MatchViewModel vm) {
        viewModel = vm;
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
