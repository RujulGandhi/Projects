package com.example.raviarchi.daberny.Activity.Fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.raviarchi.daberny.Activity.Activity.AskQuestionActivity;
import com.example.raviarchi.daberny.Activity.Activity.LoginActivity;
import com.example.raviarchi.daberny.Activity.Model.UserProfileDetails;
import com.example.raviarchi.daberny.Activity.Utils.Constant;
import com.example.raviarchi.daberny.Activity.Utils.Utils;
import com.example.raviarchi.daberny.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ravi archi on 1/10/2017.
 */

public class UserProfile extends Fragment implements View.OnClickListener {
    public Utils utils;
    public UserProfileDetails details;
    public Dialog dialog;
    public Button btnAskQue, btnUserProfile, btnLogout;
    public String ID, Interest;
    public ArrayList<String> arrayInterestList, arrayInterestIdList, interestList;
    public ArrayList<UserProfileDetails> arrayList;
    public RelativeLayout headerView;
    @BindView(R.id.fragment_user_profile_imgBrowse)
    ImageView imgBrowse;
    @BindView(R.id.fragment_user_profile_imgphoto)
    ImageView imgProfilePic;
    @BindView(R.id.fragment_user_profile_txtposts)
    TextView txtPosts;
    @BindView(R.id.fragment_user_profile_txtfollowers)
    TextView txtFollowers;
    @BindView(R.id.fragment_user_profile_txtfollowing)
    TextView txtFollowing;
    @BindView(R.id.fragment_user_profile_txteditprofile)
    TextView txtEditProfile;
    @BindView(R.id.fragment_user_profile_txtinterest1)
    TextView txtInterest1;
    @BindView(R.id.fragment_user_profile_txtinterestchart1)
    TextView txtInterestChart1;
    @BindView(R.id.fragment_user_profile_txtinterestchart2)
    TextView txtInterestChart2;
    @BindView(R.id.fragment_user_profile_txtinterestchart3)
    TextView txtInterestChart3;
    @BindView(R.id.fragment_user_profile_txtusername)
    TextView txtUserName;
    @BindView(R.id.fragment_user_profile_layoutposts)
    LinearLayout layoutPosts;
    @BindView(R.id.fragment_user_profile_layoutfollowers)
    LinearLayout layoutFollowers;
    @BindView(R.id.fragment_user_profile_layoutfollowing)
    LinearLayout layoutFollowing;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        ButterKnife.bind(this, view);
        headerView = (RelativeLayout) getActivity().findViewById(R.id.headerview);
        headerView.setVisibility(View.GONE);
        init();
        new GetUserProfileDetails().execute();
        click();
        return view;
    }

    // TODO: 2/21/2017 initilization
    private void init() {
        ID = Utils.ReadSharePrefrence(getActivity(), Constant.USERID);
        utils = new Utils(getActivity());
        arrayList = new ArrayList<>();
    }

    private void click() {
        txtEditProfile.setOnClickListener(this);
        imgBrowse.setOnClickListener(this);
        layoutFollowers.setOnClickListener(this);
        layoutFollowing.setOnClickListener(this);
        layoutPosts.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        Gson gson = new Gson();
        Bundle bundle = new Bundle();
        bundle.putString("userprofiledetails", gson.toJson(details));

        switch (v.getId()) {
            case R.id.fragment_user_profile_txteditprofile:
                // TODO: 2/24/2017 goes to editprofile screen
                Utils.WriteSharePrefrence(getActivity(), Constant.USER_INTERESTID, Interest);
                fragment = new EditUserProfile();
                break;

            case R.id.fragment_user_profile_imgBrowse:
                openDialog();
                break;

            case R.id.fragment_user_profile_layoutfollowers:
                // TODO: 2/24/2017 goes to follower people screen
                fragment = new Follower();
                break;

            case R.id.fragment_user_profile_layoutfollowing:
                // TODO: 2/24/2017 goes to following people screen
                fragment = new Following();
                break;

            case R.id.fragment_user_profile_layoutposts:
                // TODO: 2/24/2017 goes to posts of particular person screen
                fragment = new Posts();
                break;
        }

        if (fragment != null) {
            fragment.setArguments(bundle);
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.frame_contain_layout, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    // TODO: 2/24/2017 open dialog
    private void openDialog() {
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_option);
        dialog.setTitle("");
        btnAskQue = (Button) dialog.findViewById(R.id.dialog_askque);
        btnUserProfile = (Button) dialog.findViewById(R.id.dialog_userprofile);
        btnLogout = (Button) dialog.findViewById(R.id.dialog_logout);
        dialog.show();
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ilogout = new Intent(getActivity(), LoginActivity.class);
                ilogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                Utils.ClearaSharePrefrence(getActivity());
                startActivity(ilogout);
                dialog.dismiss();
            }
        });

        btnAskQue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent iask = new Intent(getActivity(), AskQuestionActivity.class);
                iask.putExtra("id", Utils.ReadSharePrefrence(getActivity(), Constant.USERID));
                startActivity(iask);
                dialog.dismiss();
            }
        });

        btnUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private void openUserProfileDetailsList() {
        // TODO: 2/27/2017 user details
        txtUserName.setText(details.getUserUserName());
        txtPosts.setText(details.getUserPosts());
        txtFollowers.setText(details.getUserFollowers());
        txtFollowing.setText(details.getUserFollowing());

// TODO: 3/15/2017 get interest at this screen 
        interestList = new ArrayList<>();
        if (arrayInterestList.size() > 0) {
            for (int i = 0; i < arrayInterestList.size(); i++) {
                interestList.add(arrayInterestList.get(i));
            }
        }
        Interest = "";
        if (interestList.size() > 0) {
            for (int i = 0; i < interestList.size(); i++) {
                if (Interest.length() > 0) {
                    Interest = Interest + "," + interestList.get(i);
                } else {
                    Interest = interestList.get(i);
                }
            }

        }

// TODO: 3/9/2017 set interest in one text
        txtInterest1.setText(Interest);

        // TODO: 3/9/2017 get interest in different text

        // TODO: 3/15/2017 for first interest
        String firstInterest = Interest.split(",")[0];
        txtInterestChart1.setText(firstInterest + ":");

        // TODO: 3/15/2017 for second interest
        if (Interest.split(",").length > 1) {
            String secondInterest = Interest.split(",")[1];
            txtInterestChart2.setText(secondInterest + ":");
        } else {
            txtInterestChart2.setText(" ");
        }

        // TODO: 3/15/2017 for third interest
        if (Interest.split(",").length > 2) {
            String thirdInterest = Interest.split(",")[2];
            txtInterestChart3.setText(thirdInterest + ":");
        } else {
            txtInterestChart3.setText(" ");
        }

        // TODO: 3/9/2017 set user profile pic
        if (details.getUserImage().length() > 0) {
            Picasso.with(getActivity()).load(details.getUserImage()).placeholder(R.drawable.ic_placeholder).into(imgProfilePic);
        } else {
            Picasso.with(getActivity()).load(R.drawable.ic_placeholder).into(imgProfilePic);
            // Picasso.with(getActivity()).load(R.mipmap.ic_launcher).placeholder(R.drawable.ic_placeholder).into(imgProfilePic);
        }
        // TODO: 2/27/2017 interest details
    }

    private class GetUserProfileDetails extends AsyncTask<String, String, String> {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(getActivity());
            pd.setMessage("Loading");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            //http://181.224.157.105/~hirepeop/host2/surveys/api/profile/677/669
            return utils.getResponseofGet(Constant.QUESTION_BASE_URL + "profile/" + Utils.ReadSharePrefrence(getActivity(), Constant.USERID) + "/" + ID);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("RESPONSE", "User Details..." + s);
            pd.dismiss();
            try {
                JSONObject jsonObject = new JSONObject(s);
                if (jsonObject.getString("status").equalsIgnoreCase("true")) {
                    JSONObject userObject = jsonObject.getJSONObject("data");
                    details = new UserProfileDetails();
                    arrayInterestList = new ArrayList<>();
                    arrayInterestIdList = new ArrayList<>();
                    details.setUserId(userObject.getString("user_id"));
                    details.setUserFullName(userObject.getString("fullname"));
                    details.setUserUserName(userObject.getString("username"));
                    details.setUserEmail(userObject.getString("email"));
                    details.setUserPosts(userObject.getString("post"));
                    details.setUserFollowers(userObject.getString("followers"));
                    details.setUserFollowing(userObject.getString("following"));
                    details.setUserImage(userObject.getString("image"));
                    details.setUserCountryId(userObject.getString("country_id"));
                    details.setUserCountryName(userObject.getString("country_name"));

                    JSONArray userArray = userObject.getJSONArray("user_interests");
                    for (int i = 0; i < userArray.length(); i++) {
                        JSONObject interestObject = userArray.getJSONObject(i);
                        arrayInterestList.add(interestObject.getString("name"));
                        arrayInterestIdList.add(interestObject.getString("id"));
                    }
                    arrayList.add(details);
                }
                if (arrayList.size() > 0) {
                    openUserProfileDetailsList();

                } else {
                    Toast.makeText(getActivity(), "No User Details Found, Please Try Again.", Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }
}

