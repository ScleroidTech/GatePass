package com.scleroidtech.gatepass.mainActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.scleroidtech.gatepass.AppExecutors;
import com.scleroidtech.gatepass.R;
import com.scleroidtech.gatepass.utils.ImageUtils;
import com.scleroidtech.gatepass.utils.InstantAppExecutors;
import com.scleroidtech.gatepass.utils.SnackBarUtils;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int RC_SIGN_IN = 123;

    private static final String TAG = MainActivity.class.getSimpleName();

    // tags used to attach the fragments
    private static final String TAG_DASHBOARD = "dashboard";
    private static final String TAG_PARTNERS = "partners";
    private static final String TAG_ADDRESS = "address";
    private static final String TAG_PROFILE = "profile";
    private static final String TAG_SETTINGS = "settings";
    private static final String TAG_ORDERS = "orders";
    private static final int THREAD_COUNT = 3;
    // index to identify current nav menu item
    public static int navItemIndex = 0;
    public static String CURRENT_TAG = TAG_DASHBOARD;



    // Choose authentication providers
    List<AuthUI.IdpConfig> providers = Arrays.asList(
            new AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER).build());

    @Inject
    SnackBarUtils snackBarUtils;
    @Inject
    ImageUtils imageUtils;


    //  @BindView(R.id.toolbar)
    Toolbar toolbar;
    //Binding Views
    //  @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    //   @BindView(R.id.fab)
    FloatingActionButton fab;
    //   @BindView(R.id.nav_view)
    NavigationView navigationView;
    // @Nullable
    // @BindView(R.id.name_text_view)
    TextView nameTextView;
    /**
     * Subtitle text for the header of the navigation drawer
     * Can be either mobile number or email id
     */
    TextView subTextTextView;
    // @Nullable
    // @BindView(R.id.img_profile)
    ImageView profileImageView;
    @Inject
    AppExecutors appExecutors;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser firebaseUser;
    //  @Nullable
    //  @BindView(R.id.text_view_email)
    private String username, photoUrl;
    private String userEmail;
    private String userPhone;
    private String[] activityTitles;

    @NonNull
    public static Intent newIntent(Context activity) {
        return new Intent(activity, MainActivity.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize Firebase Auth
        mFirebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = mFirebaseAuth.getCurrentUser();
        setContentView(R.layout.activity_main);
        // ButterKnife.bind(this);
        nameTextView = findViewById(R.id.name_text_view);
        subTextTextView = findViewById(R.id.text_view_subText);
        profileImageView = findViewById(R.id.img_profile);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);
        //Check login, & if not, prompt the user to login
        validateLogin();

        // load nav menu header data
        // loadNavHeader();

        // initializing navigation menu
        setUpNavigationView();


        appExecutors = new InstantAppExecutors();
        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_DASHBOARD;
            loadFragment();
        }

    }

    /**
     * Check if user logged in or not,
     * If not, Call the FireBaseUI to issue the login to user
     */
    private void validateLogin() {
        if (firebaseUser == null) {
            // Not signed in, launch the Sign In activity
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .setIsSmartLockEnabled(true, true)
                            .build(),
                    RC_SIGN_IN);
            //startActivity(new Intent(this, LoginActivity.class));
            //  finish();

        } else {
            username = firebaseUser.getDisplayName();

            if (firebaseUser.getEmail() != null) {
                userEmail = firebaseUser.getEmail();

            }
            if (firebaseUser.getPhoneNumber() != null) {
                userPhone = firebaseUser.getPhoneNumber();
            }
            if (firebaseUser.getPhotoUrl() != null) {
                photoUrl = firebaseUser.getPhotoUrl().toString();
            }

        }

    }

    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        // This method will trigger on item Click of navigation menu
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawer.addDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    /***
     * Returns respected fragment that user
     * selected from navigation menu
     */
    private void loadFragment() {
        // selecting appropriate nav menu item
        selectNavMenu();

        // set toolbar title
        setToolbarTitle();

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();

            return;
        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app

        Runnable pendingRunnable = () -> {
            // update the main content by replacing fragments

            Fragment fragment = getCurrentFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                    android.R.anim.fade_out);
            fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
            fragmentTransaction.commitAllowingStateLoss();
        };

        // If pendingRunnable is not null, then add to the message queue
        // boolean post = handler.post(pendingRunnable);
        appExecutors.diskIO().execute(pendingRunnable);

        // show or hide the fab button


        //Closing drawer on item click
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private Fragment getCurrentFragment() {
        switch (navItemIndex) {
            //TODO
           /* case 0:
                // dashboard
               // return new HomeFragment();
            case 1:
                // orders fragment
                return new OrdersFragment();
            case 2:
                // address fragment
                return new AddressListFragment();
            case 3:
                // partners fragment
                return new PartnersFragment();

            case 4:
                // profile fragment
                return new ProfileFragment();

            case 5:
                //setting fragment
                return new SettingsFragment();
*/
            default:
                return new HomeFragment();// HomeFragment.newInstance(HomeFragment.parcelCount);
        }

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    /***
     * Load navigation menu header information
     * like background image, profile image
     * name, website, notifications action view (dot)
     */
    private void loadNavHeader() {

        //sets the name of The Header of navigation drawer
        nameTextView.setText(username);
        //sets email if exits or phone as alternate in sub header of nav drawer
        subTextTextView.setText(userEmail != null ? userEmail : userPhone);

        //Loads profile image
        loadProfileImage();


    }

    /**
     * Loads profile image if exists, or puts a placeholder if doesn't
     */
    private void loadProfileImage() {
        imageUtils.loadImageIntoImageView(profileImageView, photoUrl, R.drawable.ic_person);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        // when fragment is notifications, load the menu created for notifications
        /*if (navItemIndex == 3) {
            getMenuInflater().inflate(R.menu.notifications, menu);
        }*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } /*TODO Add logout logic
        else if (id == R.id.action_logout) {
        }
*/
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        // Handle navigation view item clicks here.
        int id = menuItem.getItemId();


        //Check to see which item was being clicked and perform appropriate action
        switch (menuItem.getItemId()) {
            //Replacing the main content with ContentFragment Which is our Inbox View;
            case R.id.nav_Entry:
                navItemIndex = 0;
                CURRENT_TAG = TAG_DASHBOARD;
                break;
            case R.id.nav_gallery:
                navItemIndex = 1;
                CURRENT_TAG = TAG_ORDERS;
                break;
            case R.id.nav_slideshow:
                navItemIndex = 2;
                CURRENT_TAG = TAG_ADDRESS;
                break;
            case R.id.nav_manage:
                navItemIndex = 3;
                CURRENT_TAG = TAG_PARTNERS;
                break;

            case R.id.nav_profile:
                navItemIndex = 4;
                CURRENT_TAG = TAG_PROFILE;
                break;
            case R.id.nav_settings:
                navItemIndex = 5;
                CURRENT_TAG = TAG_SETTINGS;
                break;
            case R.id.nav_share:
                //TODO launch new intent instead of loading fragment
                //   startActivity(new Intent(MainActivity.this, AboutUsActivity.class));
                drawer.closeDrawers();
                return true;
            case R.id.nav_send:
                // launch new intent instead of loading fragment
                //TODO  startActivity(new Intent(MainActivity.this, PrivacyPolicyActivity.class));
                drawer.closeDrawers();
                return true;
            default:
                navItemIndex = 0;
        }

        //Checking if the item is in checked state or not, if not make it in checked state
        if (menuItem.isChecked()) {
            menuItem.setChecked(false);
        } else {
            menuItem.setChecked(true);
        }
        // menuItem.setChecked(true);

        loadFragment();
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @SuppressLint("TimberArgCount")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        handleSignInResponse(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        // DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //TODO Keep if Needed
           /* // This code loads home fragment when back key is pressed
            // when user is in other fragment than home
            if (shouldLoadHomeFragOnBackPress) {
                // checking if user is on other navigation menu
                // rather than home
                if (navItemIndex != 0) {
                    navItemIndex = 0;
                    CURRENT_TAG = TAG_DASHBOARD;
                    loadFragment();
                    return;
                }
            }
*/
            super.onBackPressed();
        }
    }

    @SuppressLint("TimberArgCount")
    private void handleSignInResponse(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                validateLogin();
                // ...
            } else {
                // Sign in failed, check response for error code
                // Sign in failed
                if (response == null) {
                    // User pressed back button
                    showSnackbar(R.string.sign_in_cancelled);
                    return;
                }

                if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                    showSnackbar(R.string.no_internet_connection);
                    return;
                }

                showSnackbar(R.string.unknown_error);
                Timber.e("Sign-in error: ", response.getError());
            }
            Timber.d(resultCode + " " + response.toString());
            // ...
        }
    }

    /**
     * Calls the {@link SnackBarUtils} method showSnackBar
     * Which is used to display
     * {@link Snackbar}
     *
     * @param msg the message string which needs to be shown
     */
    private void showSnackbar(int msg) {
        View parentLayout = getWindow().getDecorView().findViewById(android.R.id.content);
        snackBarUtils.showSnackbar(parentLayout, msg);
    }


}
