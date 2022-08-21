package com.example.sampleapp53.basicLayout;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.sampleapp53.R;
import com.example.sampleapp53.adapterForCardView.adapter;
import com.example.sampleapp53.dashboardFragments.Favourites;
import com.example.sampleapp53.dashboardFragments.GlobalLeaderboard;
import com.example.sampleapp53.dashboardFragments.addBooksFragment;
import com.example.sampleapp53.dashboardFragments.dashboardFragment;
import com.example.sampleapp53.dashboardFragments.userProfile;
import com.example.sampleapp53.databinding.ActivityDashboardBinding;
import com.example.sampleapp53.myDBHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class dashboard extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityDashboardBinding binding;
    public static ArrayList<String> bookNames;
    FirebaseFirestore firedb;
    myDBHelper helper;
    public static Cursor cursor;
    SQLiteDatabase db;

    Map<String, Object> docData;
    public static Cursor cursor2;
    public static Cursor cursor4;
    public static String book;
    public static String XP;
    public static Cursor cursor3;
    public static String personName;
    public static String personEmail;

    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        docData= new HashMap<>();
        personName = getIntent().getStringExtra("userName");
        personEmail = getIntent().getStringExtra("userEmail");
        helper = new myDBHelper(this);
        db = helper.getReadableDatabase();
        firedb  = FirebaseFirestore.getInstance();
        cursor4 = db.rawQuery("SELECT XP FROM COINS_AND_XP", new String[]{});
        if(cursor4.getCount()!=0){
            cursor4.moveToFirst();
            XP = cursor4.getString(0);
            docData.put("XP", XP);
            docData.put("Email", personEmail);
        }
        else{
            helper.insertCoinsAndXP(0,0);
            docData.put("XP", 0+"");
            docData.put("Email", personEmail);
        }

//        setSupportActionBar(binding.appBarDashboard.toolbar);
//        DrawerLayout drawer = binding.drawerLayout;
//        NavigationView navigationView = binding.navView;
//         Passing each menu ID as a set of Ids because each
//         menu should be considered as top level destinations.
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
//                .setDrawerLayout(drawer)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_dashboard);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);
//
//        View headerView = navigationView.getHeaderView(0);
//        TextView navUsername = (TextView) headerView.findViewById(R.id.UserName);
//        TextView navUserEmail = (TextView) headerView.findViewById(R.id.UserEmail);


//        navUsername.setText(personName);
//        navUserEmail.setText(personEmail);

        cursor = db.rawQuery("SELECT BOOK, SCORE FROM BOOK_SCORES WHERE BOOK = " + "'"+adapter.book_selected_byUser+"'", new String[]{});
        cursor.moveToFirst();
        cursor2 = db.rawQuery("SELECT BOOK_NAME FROM DASHBOARD_BOOKS", new String[]{});
        cursor3 = db.rawQuery("SELECT BOOK, CHAPTER_NAME, SCORE FROM CHAPTER_SCORES", new String[]{});
        if(cursor3.getCount() != 0){
            cursor3.moveToFirst();
        }
        bookNames = new ArrayList<>();
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()){
                    case R.id.profilePage:  selectedFragment = new userProfile();
                        break;
                    case R.id.homePage:  selectedFragment = new dashboardFragment();
                        break;
                    case R.id.markedQuestionsPage:  selectedFragment = new Favourites();
                        break;
                    case R.id.globalLeaderboardPage:  selectedFragment = new GlobalLeaderboard();
                        break;

                    case R.id.searchPage:  selectedFragment = new addBooksFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, selectedFragment).commit();
                return true;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected( MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        gestureDetectorCompat.onTouchEvent(event);

        return true;
    }
//    @Override
//    public boolean onSupportNavigateUp() {
//       NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_dashboard);
//       return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//               || super.onSupportNavigateUp();
//   }
}