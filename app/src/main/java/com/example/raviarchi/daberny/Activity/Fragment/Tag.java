package com.example.raviarchi.daberny.Activity.Fragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.raviarchi.daberny.Activity.Adapter.TagAdapter;
import com.example.raviarchi.daberny.Activity.Model.UserProfileDetails;
import com.example.raviarchi.daberny.Activity.Utils.Constant;
import com.example.raviarchi.daberny.Activity.Utils.Utils;
import com.example.raviarchi.daberny.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * Created by Ravi archi on 1/10/2017.
 */

public class Tag extends Fragment {
    public RecyclerView recyclerViewhome;
    public Utils utils;
    public UserProfileDetails details;
    public String ID;
    private ArrayList<String> arrayInterestList;
    private ArrayList<String> arrayStartNameList;
    private ArrayList<String> arrayEndNameList;
    private ArrayList<String> arrayFollowingNameList;
    private ArrayList<String> arrayFollowingIdList;
    private ArrayList<UserProfileDetails> arrayUserList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_search_tag, container, false);
        init();
        findViewId(view);
        new GetQuetionList().execute();
        click();
        return view;
    }

    // TODO: 2/22/2017 click perform 
    private void click() {
    }

    // TODO: 2/22/2017 bind data with field
    private void findViewId(View view) {
        recyclerViewhome = (RecyclerView) view.findViewById(R.id.fragment_search_recyclertaglist);
    }

    // TODO: 2/21/2017 initilization
    private void init() {
        utils = new Utils(getActivity());
        arrayUserList = new ArrayList<>();
        if (getArguments() != null) {
            Gson gson = new Gson();
            String strObj = getArguments().getString("userprofiledetails");
            details = gson.fromJson(strObj, UserProfileDetails.class);
            ID = details.getQueId();
        }
    }

    private void openQuetionList() {
        // TODO: 2/21/2017 bind list and show in adapter

        Collections.reverse(arrayUserList);
        TagAdapter adapter = new TagAdapter(getActivity(), arrayUserList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewhome.setLayoutManager(mLayoutManager);
        recyclerViewhome.setItemAnimator(new DefaultItemAnimator());
        recyclerViewhome.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    // TODO: 2/21/2017 get list of Question from URL
    private class GetQuetionList extends AsyncTask<String, String, String> {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            arrayFollowingIdList = new ArrayList<>();
            arrayFollowingNameList = new ArrayList<>();
            pd = new ProgressDialog(getActivity());
            pd.setMessage("Loading");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            // http://181.224.157.105/~hirepeop/host2/surveys/api/question_tag_data/181
            return utils.getResponseofGet(Constant.QUESTION_BASE_URL + "question_tag_data/" + ID);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("RESPONSE", "Searched Question..." + s);
            pd.dismiss();
            try {
                JSONObject jsonObject = new JSONObject(s);
                if (jsonObject.getString("status").equalsIgnoreCase("true")) {
                    JSONObject questionObject = jsonObject.getJSONObject("data");
                    details = new UserProfileDetails();
                    details.setQueId(questionObject.getString("id"));
                    details.setQueTitle(questionObject.getString("title"));
                    details.setQuePostDate(questionObject.getString("post_date"));
                    details.setQueOptionFirst(questionObject.getString("option1"));
                    details.setQueOptionSecond(questionObject.getString("option2"));
                    details.setQueOptionThird(questionObject.getString("option3"));
                    details.setQueOptionFourth(questionObject.getString("option4"));
                    details.setUserId(questionObject.getString("user_id"));
                    details.setUserInterestId(questionObject.getString("in_id"));
                    details.setQueVoteStatus(questionObject.getString("vote_status"));
                    details.setUserCanVote(questionObject.getString("can_vote"));
                   // details.setQueCreatedTime(questionObject.getString("created_time"));
                    details.setQueCategory(questionObject.getString("name"));
                    details.setQueType(questionObject.getString("type"));
                    details.setQueImageName(questionObject.getString("picture"));
                    if (!questionObject.getString("picture").equalsIgnoreCase("")) {
                        if (questionObject.getString("type").equalsIgnoreCase("0")) {
                            details.setQueImage(questionObject.getString("picture_url"));
                        } else if (questionObject.getString("type").equalsIgnoreCase("1")) {
                            details.setQueImage(questionObject.getString("picture_url"));
                        } else if (questionObject.getString("type").equalsIgnoreCase("2")) {
                            details.setQueImage(questionObject.getString("picture_url"));
                        }
                    }
                    // TODO: 3/25/2017 get like details
                    JSONObject answerObj = questionObject.getJSONObject("answers");
                    details.setQueVoteTotalCount(answerObj.getInt("total_count"));
                    details.setQueLikeStatus(answerObj.getString("liked"));
                    details.setQueLikeTotalCount(answerObj.getInt("likes_count"));

                    // TODO: 3/27/2017 get the list of following
                    JSONArray followingArray = questionObject.getJSONArray("following");
                    for (int i = 0; i < followingArray.length(); i++) {
                        JSONObject followingObject = followingArray.getJSONObject(i);
                        //details.setUserId(followingObject.getString("user_id"));
                        arrayFollowingIdList.add(followingObject.getString("follow_user_id"));
                        arrayFollowingNameList.add(followingObject.getString("fullname"));
                        details.setUserFollowingId(arrayFollowingIdList);
                        details.setUserFollowingName(arrayFollowingNameList);
                    }
                    // TODO: 3/22/2017 get user details
                    JSONObject userObject = questionObject.getJSONObject("user");
                    details.setUserImage(userObject.getString("user_image"));
                    details.setUserUserName(userObject.getString("username"));
//                    // TODO: 3/22/2017 get comment details
                    /*JSONArray commentArray = questionObject.getJSONArray("comments");
                    if (commentArray.length() > 0) {
                        for (int c = 0; c < commentArray.length(); c++) {
                            JSONObject countcommentObject = commentArray.getJSONObject(c);
                            details.setQueComment(countcommentObject.getString("comment_text"));
                            details.setQueCommentId(countcommentObject.getString("id"));
                            details.setQueId(countcommentObject.getString("qid"));
                            details.setQueCommentUser(countcommentObject.getString("username"));
                                                            details.setQueCommentUserId(countcommentObject.getString("uid"));

                        }
                    }*/
                    // TODO: 3/22/2017 get data of rank_interest
                    JSONArray rankArray = questionObject.getJSONArray("rank_interest");
                    arrayInterestList = new ArrayList<>();
                    arrayStartNameList = new ArrayList<>();
                    arrayEndNameList = new ArrayList<>();
                    for (int r = 0; r < rankArray.length(); r++) {
                        JSONObject rankdetailObj = rankArray.getJSONObject(r);
                        arrayInterestList.add(rankdetailObj.getString("int_name"));
                        arrayStartNameList.add(rankdetailObj.getString("start_name"));
                        arrayEndNameList.add(rankdetailObj.getString("end_name"));
                    }
                    details.setIntName(arrayInterestList);
                    details.setStartRankName(arrayStartNameList);
                    details.setEndRankName(arrayEndNameList);
                    //String timing = questionObject.getString("timing");
                    // details.setQueTiming(Long.valueOf(questionObject.getString("timing")));
                            /*try {
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss", Locale.ENGLISH);
                                Date eventDate = (Date) dateFormat.parseObject(timing);
                                //Date event = dateFormat.parseObject(timing);
                                //Date currentDate = new Date();
                               // Log.d("get_date", "" + eventDate);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }*/
                    arrayUserList.add(details);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (arrayUserList.size() > 0) {
                openQuetionList();

            } else {
                Toast.makeText(getActivity(), "No Question Found", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
