package com.teaman.accessstillwater.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.teaman.accessstillwater.AccessStillwaterApp;
import com.teaman.accessstillwater.R;
import com.teaman.accessstillwater.ui.establishment.EstablishmentListFragment;
import com.teaman.accessstillwater.ui.navigation.Navigator;
import com.teaman.data.authorization.LoginAdapter;
import com.teaman.data.authorization.parse.ParseUserAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * <h1> [Insert class name here] </h1>
 * <p>
 * [Insert class description here]
 * </p>
 * <p>
 * [Insert additional information here (links, code snippets, etc.)]
 * </p>
 *
 * @author Aaron Weaver
 *         Team Andronerds
 *         waaronl@okstate.edu
 * @version 1.0
 * @since 2/25/16
 */
public abstract class BaseDrawerActivity extends BaseActivity implements View.OnClickListener{

    @Bind(R.id.nav_drawer_layout)
    protected DrawerLayout mDrawerLayout;

    @Bind(R.id.nav_view)
    protected NavigationView mNavMenu;

    @Nullable
    @Bind(R.id.header_profile_image)
    protected CircleImageView mProfileImage;

    @Nullable
    @Bind(R.id.header_profile_name)
    protected TextView mProfileName;

    @Nullable
    @Bind(R.id.friends_count)
    protected TextView mFriendsCount;

    @Nullable
    @Bind(R.id.reviews_count)
    protected TextView mReviewsCount;

    @Nullable
    @Bind(R.id.favorites_count)
    protected TextView mFavoritesCount;

    @Nullable
    @Bind(R.id.nav_header_layout)
    protected RelativeLayout mNavHeaderLayout;

    private ParseUserAdapter mCurrentUser;
    private LoginAdapter mLoginAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;

        mLoginAdapter = AccessStillwaterApp.getmInstance().getLoginAdapter();

        if(getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if(mLoginAdapter.isLoggedIn()) {
            mCurrentUser = mLoginAdapter.getUser();
        }

        if(mDrawerLayout != null && mNavMenu != null) {
            drawerSetup();
        }

        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

//                boolean opening = slideOffset > last;
//                boolean closing = slideOffset < last;
//
//                if (opening) {
//
//                } else if (closing) {
//
//                }

            }

            @Override
            public void onDrawerOpened(View drawerView) {
            }

            @Override
            public void onDrawerClosed(View drawerView) {
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    private void drawerSetup() {
        mNavMenu.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        item.setChecked(false);
                        mDrawerLayout.closeDrawers();

                        switch (item.getItemId()) {
                            case R.id.nav_sign_out:
                                mLoginAdapter.logOut();
                                Navigator.getInstance().navigateToLoginActivity(mContext);
                                break;
                            case R.id.nav_home:
                                Navigator.getInstance().navigateToMainActivity(mContext);
                        }
                        return true;
                    }
                });

        mNavHeaderLayout = (RelativeLayout) getLayoutInflater().inflate(R.layout.nav_header, null);

        LinearLayout favoritesHeader = (LinearLayout)
                mNavHeaderLayout.findViewById(R.id.favorites_linear_layout);

        favoritesHeader.setOnClickListener(this);

        if(mNavHeaderLayout != null) {
            mNavMenu.addHeaderView(mNavHeaderLayout);
            mProfileImage = ButterKnife.findById(mNavHeaderLayout, R.id.header_profile_image);
            mProfileName = ButterKnife.findById(mNavHeaderLayout, R.id.header_profile_name);
            mFriendsCount = ButterKnife.findById(mNavHeaderLayout, R.id.friends_count);
            mFavoritesCount = ButterKnife.findById(mNavHeaderLayout, R.id.favorites_count);
            mReviewsCount = ButterKnife.findById(mNavHeaderLayout, R.id.reviews_count);
            drawerUpdate();
        }
    }

    private void drawerUpdate() {
        if(mCurrentUser != null) {
            if(mProfileImage != null) {
                Picasso.with(this)
                        .load(mCurrentUser.getUserAvatar())
                        .fit()
                        .placeholder(R.drawable.ic_account_circle_white_48dp)
                        .into(mProfileImage);
            }

            if(mProfileName != null) {
                Log.d("BASE DRAWER ACTIVITY", mCurrentUser.getDisplayName(false));
                mProfileName.setText(mCurrentUser.getDisplayName(false));
            }

            if(mFavoritesCount != null) {
                mFavoritesCount.setText("0");
            }

            if(mReviewsCount != null) {
                mReviewsCount.setText("0");
            }

            if(mFriendsCount != null) {
                mFriendsCount.setText("0");
            }
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.base_activity_drawer;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (mDrawerLayout != null) {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.favorites_linear_layout:
                Navigator.getInstance().navigateToEstablishmentActivity(mContext,
                        EstablishmentListFragment.FRAGMENT_FAVORITE);
                break;
        }
    }
}