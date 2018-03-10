package com.scleroidtech.gatepass.MainActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.scleroidtech.gatepass.R;
import com.scleroidtech.gatepass.Utils.SnackBarUtils;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int RC_SIGN_IN = 123;
    @BindView(R.id.message)
    TextView mTextMessage;
    // Choose authentication providers
    List<AuthUI.IdpConfig> providers = Arrays.asList(
            new AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER).build());
    @Inject
    SnackBarUtils snackBarUtils;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser firebaseUser;
    private String username, photoUrl;
    private String userEmail;
    private String userPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize Firebase Auth
        mFirebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = mFirebaseAuth.getCurrentUser();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        updateUI();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void updateUI() {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
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
                updateUI();
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
