package com.xea.whatsappxea.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.xea.whatsappxea.fragments.AddFragment;
import com.xea.whatsappxea.fragments.DetailsFragment;
import com.xea.whatsappxea.fragments.ShowFragment;

import java.util.ArrayList;

public class MyViewPagerAdapter extends FragmentStatePagerAdapter {

    ArrayList<Fragment> fragmentArray;
    private int numberOfTabs;

    public MyViewPagerAdapter(@NonNull FragmentManager fm, int behavior,String userLogged) {
        super(fm, behavior);
        this.numberOfTabs = behavior;
        fragmentArray = new ArrayList<>();
        fragmentArray.add(new ShowFragment(userLogged));
        fragmentArray.add(new DetailsFragment());
        fragmentArray.add(new AddFragment());
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        return fragmentArray.get(position);
    }

    @Override
    public int getCount() {
        return this.numberOfTabs;
    }
}
