package com.example.raviarchi.daberny.Activity.Fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
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

import com.daimajia.numberprogressbar.NumberProgressBar;
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

public class OtherUserProfile extends Fragment implements View.OnClickListener {
    public Utils utils;
    public UserProfileDetails details;
    public Dialog dialog;
    public Button btnAskQue, btnUserProfile, btnLogout, btnSend, btnClose, btnSetting;
    public String ID, userId, isInFollowList, isInBlockList, task, blocktask;
    public int flag;
    public ArrayList<String> arrayInterestList;
    public ArrayList<String> interestList, arrayFollowUnFollowList;
    public RelativeLayout headerView;
    @BindView(R.id.fragment_other_user_profile_imgBrowse)
    ImageView imgBrowse;
    @BindView(R.id.fragment_other_user_profile_imgphoto)
    ImageView imgProfilePic;
    @BindView(R.id.fragment_other_user_profile_txtposts)
    TextView txtPosts;
    @BindView(R.id.fragment_other_user_profile_txtfollowers)
    TextView txtFollowers;
    @BindView(R.id.fragment_other_user_profile_txtfollowing)
    TextView txtFollowing;
    @BindView(R.id.fragment_other_user_profile_txtfollow)
    TextView txtFollow;
    @BindView(R.id.fragment_other_user_profile_txtreport)
    TextView txtReport;
    @BindView(R.id.fragment_other_user_profile_txtblock)
    TextView txtBlock;
    @BindView(R.id.fragment_other_user_profile_txtinterest1)
    TextView txtInterest1;
    @BindView(R.id.fragment_other_user_profile_txtinterestchart1)
    TextView txtInterestChart1;
    @BindView(R.id.fragment_other_user_profile_txtinterestchart2)
    TextView txtInterestChart2;
    @BindView(R.id.fragment_other_user_profile_txtinterestchart3)
    TextView txtInterestChart3;
    @BindView(R.id.fragment_other_user_profile_txtusername)
    TextView txtUserName;
    @BindView(R.id.fragment_other_user_profile_layoutposts)
    LinearLayout layoutPosts;
    @BindView(R.id.fragment_other_user_profile_layoutfollowers)
    LinearLayout layoutFollowers;
    @BindView(R.id.fragment_other_user_profile_layoutfollowing)
    LinearLayout layoutFollowing;
    @BindView(R.id.fragment_other_user_profile_progressbar1)
    NumberProgressBar progressBar1;
    @BindView(R.id.fragment_other_user_profile_progressbar2)
    NumberProgressBar progressBar2;
    @BindView(R.id.fragment_other_user_profile_progressbar3)
    NumberProgressBar progressBar3;
    private ArrayList<UserProfileDetails> arrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        utils = new Utils(getActivity());
        userId = Utils.ReadSharePrefrence(getActivity(), Constant.USERID);
        if (getArguments() != null) {
            ID = getArguments().getString("id");
        }
        if (ID.equalsIgnoreCase(userId)) {
            Fragment fragment = new UserProfile();
            if (fragment != null) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.frame_contain_layout, fragment);
                transaction.commit();
            }
        } else {
            View view = inflater.inflate(R.layout.fragment_other_user_profile, container, false);
            ButterKnife.bind(this, view);
            headerView = (RelativeLayout) getActivity().findViewById(R.id.headerview);
            headerView.setVisibility(View.GONE);
            progressBar1.setMinimumHeight(50);
            progressBar1.setProgress(9);
            progressBar2.setProgress(9);
            progressBar3.setProgress(7);
            init();
            click();
            return view;
        }
        return null;
    }

    // TODO: 2/21/2017 initilization
    private void init() {
        arrayList = new ArrayList<>();
        // TODO: 4/5/2017 get user details
        new GetUserProfileDetails().execute();
    }

    private void click() {
        txtFollow.setOnClickListener(this);
        txtReport.setOnClickListener(this);
        txtBlock.setOnClickListener(this);
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
            case R.id.fragment_other_user_profile_txtfollow:
                // TODO: 2/24/2017 follow and unfollow user
                task = "";
                Log.d("follow_status", details.getUserFollowStatus());
                isInFollowList = details.getUserFollowStatus();
                if (isInFollowList.equalsIgnoreCase("Unfollow")) {
                    details.setUserFollowStatus("follow");
                } else {
                    details.setUserFollowStatus("Unfollow");
                }
                task = isInFollowList.equalsIgnoreCase("Unfollow") ? "add" : "remove";
                new GetFollowUnFollow(Utils.ReadSharePrefrence(getActivity(), Constant.USERID), ID, task).execute();
                //new GetFollowUnFollow().execute();
                break;

            case R.id.fragment_other_user_profile_imgBrowse:
                openDialog();
                break;
            case R.id.fragment_other_user_profile_txtreport:
                openDialogForReport();
                break;

            case R.id.fragment_other_user_profile_txtblock:
                blocktask = "";
                Log.d("block_status", details.getUserBlockStatus());
                isInBlockList = details.getUserBlockStatus();
                if (isInBlockList.equalsIgnoreCase("Unblock")) {
                    details.setUserFollowStatus("block");
                } else {
                    details.setUserFollowStatus("Unblock");
                }
                blocktask = isInBlockList.equalsIgnoreCase("Unblock") ? "add" : "remove";
                new UserBlockUnblock(userId, ID, blocktask).execute();
                break;

            case R.id.fragment_other_user_profile_layoutfollowers:
                // TODO: 2/24/2017 goes to follower people screen
                fragment = new Follower();
                break;

            case R.id.fragment_other_user_profile_layoutfollowing:
                // TODO: 2/24/2017 goes to following people screen
                fragment = new Following();
                break;

            case R.id.fragment_other_user_profile_layoutposts:
                // TODO: 2/24/2017 goes to following people screen
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


    private void openDialogForReport() {
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_report);
        btnSend = (Button) dialog.findViewById(R.id.dialog_report_btnsend);
        btnClose = (Button) dialog.findViewById(R.id.dialog_report_btnclose);
        dialog.show();
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent ilogout = new Intent(getActivity(), LoginActivity.class);
                startActivity(ilogout);*/
                dialog.dismiss();
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    // TODO: 2/24/2017 open dialog
    private void openDialog() {
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_option);
        dialog.setTitle("");
        btnAskQue = (Button) dialog.findViewById(R.id.dialog_askque);
        btnUserProfile = (Button) dialog.findViewById(R.id.dialog_userprofile);
        btnLogout = (Button) dialog.findViewById(R.id.dialog_logout);
        btnSetting = (Button) dialog.findViewById(R.id.dialog_setting);
        dialog.show();
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent isetting = new Intent(getActivity(), LoginActivity.class);
                startActivity(isetting);
                dialog.dismiss();*/
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ilogout = new Intent(getActivity(), LoginActivity.class);
                startActivity(ilogout);
                dialog.dismiss();
            }
        });

        btnAskQue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent iask = new Intent(getActivity(), AskQuestionActivity.class);
                iask.putExtra("uId", ID);
                startActivity(iask);
                dialog.dismiss();
            }
        });

        btnUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent iuser = new Intent(getActivity(), AskQuestion.class);
                startfragment(iuser);*/
                dialog.dismiss();
            }
        });
    }

    private void openUserProfileDetailsList() {
        // TODO: 4/5/2017 show follow & unfollow status 
        if (details.getUserFollowStatus().equalsIgnoreCase("follow")) {
            txtFollow.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.gray));
            txtFollow.setTextColor(ContextCompat.getColor(getActivity(), R.color.blue));
            txtFollow.setText(R.string.unfollow);
        } else {
            txtFollow.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.blue));
            txtFollow.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
            txtFollow.setText(R.string.follow);
        }
        // TODO: 4/5/2017 to show block & unblock
        if (details.getUserBlockStatus().equalsIgnoreCase("block")) {
            txtBlock.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.gray));
            txtBlock.setTextColor(ContextCompat.getColor(getActivity(), R.color.blue));
            txtBlock.setText(R.string.unblock);
        } else {
            txtBlock.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.blue));
            txtBlock.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
            txtBlock.setText(R.string.block);
        }
        // TODO: 2/27/2017 user details
        txtUserName.setText(details.getUserUserName());
        txtPosts.setText(details.getUserPosts());
        txtFollowers.setText(details.getUserFollowers());
        txtFollowing.setText(details.getUserFollowing());
        // TODO: 3/9/2017 set interest in one text
        interestList = new ArrayList<>();
        if (arrayInterestList.size() > 0) {
            for (int i = 0; i < arrayInterestList.size(); i++) {
                interestList.add(arrayInterestList.get(i));
            }
        }

        String interest = "";
        if (interestList.size() > 0) {
            for (int i = 0; i < interestList.size(); i++) {
                if (interest.length() > 0) {
                    interest = interest + "," + interestList.get(i);
                } else {
                    interest = interestList.get(i);
                }
            }
        }
        txtInterest1.setText(interest);

        // TODO: 3/9/2017 get interest in different text
        // TODO: 3/15/2017 for first interest
        String firstInterest = interest.split(",")[0];
        txtInterestChart1.setText(firstInterest + ":");

        // TODO: 3/15/2017 for second interest
        if (interest.split(",").length > 1) {
            String secondInterest = interest.split(",")[1];
            txtInterestChart2.setText(secondInterest + ":");
        } else {
            txtInterestChart2.setText(" ");
        }

        // TODO: 3/15/2017 for third interest
        if (interest.split(",").length > 2) {
            String thirdInterest = interest.split(",")[2];
            txtInterestChart3.setText(thirdInterest + ":");
        } else {
            txtInterestChart3.setText(" ");
        }


        if (details.getUserImage().length() > 0) {
            Picasso.with(getActivity()).load(details.getUserImage()).placeholder(R.drawable.ic_placeholder).into(imgProfilePic);
        } else {
            Picasso.with(getActivity()).load(R.mipmap.ic_launcher).placeholder(R.drawable.ic_placeholder).into(imgProfilePic);
        }
    }

    private void showUserFollowUnfollow() {
        // TODO: 4/5/2017 for show follow & unfollow status
        if (details.getUserFollowStatus().equalsIgnoreCase("follow")) {
            txtFollow.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.gray));
            txtFollow.setTextColor(ContextCompat.getColor(getActivity(), R.color.blue));
            txtFollow.setText(R.string.unfollow);
        } else {
            txtFollow.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.blue));
            txtFollow.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
            txtFollow.setText(R.string.follow);
        }
    }

    // TODO: 4/5/2017 set status of user block and unblock
    private void showUserBlockUnblock() {
        if (details.getUserBlockStatus().equalsIgnoreCase("block")) {
            txtBlock.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.gray));
            txtBlock.setTextColor(ContextCompat.getColor(getActivity(), R.color.blue));
            txtBlock.setText(R.string.unblock);
        } else {
            txtBlock.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.blue));
            txtBlock.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
            txtBlock.setText(R.string.block);
        }
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
            //http://181.224.157.105/~hirepeop/host2/surveys/api/profile/669
            return utils.getResponseofGet(Constant.QUESTION_BASE_URL + "profile/" + userId + "/" + ID);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("RESPONSE", "Other User Details..." + s);
            pd.dismiss();
            try {
                JSONObject jsonObject = new JSONObject(s);
                if (jsonObject.getString("status").equalsIgnoreCase("true")) {
                    JSONObject userObject = jsonObject.getJSONObject("data");
                    details = new UserProfileDetails();
                    arrayInterestList = new ArrayList<>();
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
                    // TODO: 4/5/2017 user follow status
                    JSONObject followObj = userObject.getJSONObject("type");
                    details.setUserFollowStatus(followObj.getString("type"));

                    // TODO: 4/5/2017 user block status
                    JSONObject blockObj = userObject.getJSONObject("type1");
                    details.setUserBlockStatus(blockObj.getString("type1"));
                    // TODO: 4/5/2017 user interest details
                    JSONArray userArray = userObject.getJSONArray("user_interests");
                    for (int i = 0; i < userArray.length(); i++) {
                        JSONObject interestObject = userArray.getJSONObject(i);
                        arrayInterestList.add(interestObject.getString("name"));
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

    private class GetFollowUnFollow extends AsyncTask<String, String, String> {
        ProgressDialog pd;
        String userId, task, otherUserId;

        public GetFollowUnFollow(String userId, String id, String task) {
            this.userId = userId;
            this.otherUserId = id;
            this.task = task;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            arrayList = new ArrayList<>();
            pd = new ProgressDialog(getActivity());
            pd.setMessage("Loading");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            //http://181.224.157.105/~hirepeop/host2/surveys/api/follow_un_follow/805/708
            return utils.getResponseofGet(Constant.QUESTION_BASE_URL + "follow_un_follow/" + userId + "/" + otherUserId);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("RESPONSE", "Follow and Unfollow..." + s);
            pd.dismiss();
            try {
                JSONObject jsonObject = new JSONObject(s);
                if (jsonObject.getString("status").equalsIgnoreCase("true")) {
                    JSONObject userObject = jsonObject.getJSONObject("inserted_data");
                    details = new UserProfileDetails();
                    details.setUserFollowStatus(userObject.getString("type"));
                    Log.d("user_type", userObject.getString("type"));
                    boolean isSucess = task.equalsIgnoreCase("add") ? false : true;
                    if (isSucess) {
                        details.setUserFollowStatus("Unfollow");
                    } else {
                        details.setUserFollowStatus("follow");
                    }
                    arrayList.add(details);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (arrayList.size() > 0) {
                showUserFollowUnfollow();
            } else {
                Toast.makeText(getActivity(), "Please try again", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // TODO: 4/5/2017 user block and unblock
    private class UserBlockUnblock extends AsyncTask<String, String, String> {
        ProgressDialog pd;
        String userId, task, otherUserId;

        public UserBlockUnblock(String userId, String id, String task) {
            this.userId = userId;
            this.otherUserId = id;
            this.task = task;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            arrayList = new ArrayList<>();
            pd = new ProgressDialog(getActivity());
            pd.setMessage("Loading");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            //http://181.224.157.105/~hirepeop/host2/surveys/api/profile_block/677/669
            return utils.getResponseofGet(Constant.QUESTION_BASE_URL + "profile_block/" + userId + "/" + ID);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("RESPONSE", "Block and UnBlock..." + s);
            pd.dismiss();
            try {
                JSONObject jsonObject = new JSONObject(s);
                if (jsonObject.getString("status").equalsIgnoreCase("true")) {
                    JSONObject userObject = jsonObject.getJSONObject("data");
                    details = new UserProfileDetails();
                    details.setUserBlockStatus(userObject.getString("type1"));
                    Log.d("user_type1", userObject.getString("type1"));
                    boolean isSucess = task.equalsIgnoreCase("add") ? false : true;
                    if (isSucess) {
                        details.setUserBlockStatus("Unblock");
                    } else {
                        details.setUserBlockStatus("block");
                    }
                    arrayList.add(details);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (arrayList.size() > 0) {
                showUserBlockUnblock();
            } else {
                Toast.makeText(getActivity(), "Please select interest", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

