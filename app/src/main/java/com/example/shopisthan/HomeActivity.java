package com.example.shopisthan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.shopisthan.Home.ExploreFragment;
import com.example.shopisthan.Home.FavouriteFragment;
import com.example.shopisthan.Home.HomeFragment;
import com.example.shopisthan.Home.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity
{
//    private BottomNavigationView bottomNavigationView121;
//    private NavController navController121;

 MeowBottomNavigation bottomNavigation;
 private final static int HOME_ID=1;
 private final static int EXPLORE_ID=2;
 private final static int FAVORITE_ID=3;
 private final static int MENU_ID=4;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        bottomNavigationView121 = findViewById(R.id.bottomNavigationView);
//        navController121 = Navigation.findNavController(this,R.id.fragment_container);
//
//        NavigationUI.setupWithNavController(bottomNavigationView121, navController121);


        bottomNavigation = findViewById(R.id.bottom_navigation);

        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_baseline_home_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_baseline_explore_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_baseline_favorite_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(4,R.drawable.ic_baseline_menu_24));

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new HomeFragment()).commit();

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item)
            {

            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {

            }
        });

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {

                Fragment fragment = null;

                switch (item.getId())
                {
                    case HOME_ID:

                        fragment = new HomeFragment();
                        break;
                    case EXPLORE_ID:

                        fragment = new ExploreFragment();
                        break;
                    case FAVORITE_ID:

                        fragment = new FavouriteFragment();
                        break;
                    case MENU_ID:

                        fragment = new ProfileFragment();
                        break;
                }

//                loadFragment(fragment);
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();
            }
        });



    }

    private void loadFragment(Fragment fragment)
    {
             getSupportFragmentManager()
                     .beginTransaction()
                     .replace(R.id.frame_layout,fragment)
                     .commit();
    }
}