package com.example.raviarchi.daberny.Activity.Fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.raviarchi.daberny.Activity.Adapter.SearchPager;
import com.example.raviarchi.daberny.R;


/**
 * Created by Ravi archi on 1/10/2017.
 */

public class Search extends Fragment implements TabLayout.OnTabSelectedListener {
    public static EditText edSearch;
    public TabLayout tabLayout;
    public ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        findById(view);
        tabLayout.addTab(tabLayout.newTab().setText("Latest"));
        tabLayout.addTab(tabLayout.newTab().setText("People"));
        tabLayout.addTab(tabLayout.newTab().setText("Tags"));
        tabLayout.addTab(tabLayout.newTab().setText("Location"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // TODO: 3/22/2017 set view pager
        viewPager = (ViewPager) view.findViewById(R.id.pager);
        SearchPager adapter = new SearchPager(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());

        // TODO: 3/22/2017  Adding adapter to pager
        viewPager.setAdapter(adapter);
        // TODO: 3/22/2017  Adding onTabSelectedListener to swipe views
        tabLayout.setOnTabSelectedListener(this);
        return view;
    }

    private void findById(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        edSearch = (EditText) view.findViewById(R.id.fragment_searchdata_edsearch);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
