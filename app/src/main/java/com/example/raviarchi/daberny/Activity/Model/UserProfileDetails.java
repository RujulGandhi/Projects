package com.example.raviarchi.daberny.Activity.Model;

import java.util.ArrayList;

/**
 * Created by Ravi archi on 2/23/2017.
 */
public class UserProfileDetails {
    private ArrayList<String> intName;
    private ArrayList<String> startRankName;
    private ArrayList<String> endRankName;
    private ArrayList<String> intId;
    private ArrayList<String> userFollowingId;
    private ArrayList<String> userFollowingName;
    private String userFullName;
    private String userUserName;
    private String userEmail;
    private String userCountryId;
    private String userCountryName;
    private String userImage;
    private String userPosts;
    private String userFollowId;
    private String userFollowers;
    private String userFollowing;
    private String userInterestName;
    private String userId;
    private String userInterestTitle;
    private String userInterestStartName;
    private String userInterestEndName;
    private String userInterestId;
    private String queTitle;
    private String queId;
    private String queOptionFirst;
    private String queOptionSecond;
    private String queOptionThird;
    private String queOptionFourth;
    private String queImage;
    private String queImageName;
    private String queAnswer;
    private Long queCreatedTime;
    private Long queTiming;
    private String queTimingDuration;
    private String queCategory;
    private String quePostDate;
    private String queType;
    private String queCommentId;
    private String queComment;
    private String userMessage;
    private String queVoteStatus;
    private String userCanVote;
    private String queCommentUser;
    private String queCommentUserId;
    private int queVoteTotalCount;
    private int queLikeTotalCount;
    private boolean isSelected;
    private String userFollowStatus;
    private String queLikeStatus;
    private String queRemainHour;
    private String queRemainMinute;
    private String queRemainSecond;
    private String userBlockStatus;
    public UserProfileDetails() {
    }
    public UserProfileDetails(String quename, boolean isSelected) {
        this.queTitle = quename;
        this.isSelected = isSelected;

    }

    public String getQueCommentUserId() {
        return queCommentUserId;
    }

    public void setQueCommentUserId(String queCommentUserId) {
        this.queCommentUserId = queCommentUserId;
    }

    public String getUserBlockStatus() {
        return userBlockStatus;
    }

    public void setUserBlockStatus(String userBlockStatus) {
        this.userBlockStatus = userBlockStatus;
    }

    public String getQueRemainHour() {
        return queRemainHour;
    }

    public void setQueRemainHour(String queRemainHour) {
        this.queRemainHour = queRemainHour;
    }

    public String getQueRemainMinute() {
        return queRemainMinute;
    }

    public void setQueRemainMinute(String queRemainMinute) {
        this.queRemainMinute = queRemainMinute;
    }

    public String getQueRemainSecond() {

        return queRemainSecond;
    }

    public void setQueRemainSecond(String queRemainSecond) {
        this.queRemainSecond = queRemainSecond;
    }

    public Long getQueCreatedTime() {
        return queCreatedTime;
    }

    public void setQueCreatedTime(Long queCreatedTime) {
        this.queCreatedTime = queCreatedTime;
    }

    public String getQueTimingDuration() {
        return queTimingDuration;
    }

    public void setQueTimingDuration(String queTimingDuration) {
        this.queTimingDuration = queTimingDuration;
    }

    public String getQueImageName() {
        return queImageName;
    }

    public void setQueImageName(String queImageName) {
        this.queImageName = queImageName;
    }

    public ArrayList<String> getUserFollowingId() {
        return userFollowingId;
    }

    public void setUserFollowingId(ArrayList<String> userFollowingId) {
        this.userFollowingId = userFollowingId;
    }

    public ArrayList<String> getUserFollowingName() {
        return userFollowingName;
    }

    public void setUserFollowingName(ArrayList<String> userFollowingName) {
        this.userFollowingName = userFollowingName;
    }

    public String getUserCanVote() {
        return userCanVote;
    }

    public void setUserCanVote(String userCanVote) {
        this.userCanVote = userCanVote;
    }

    public String getQueLikeStatus() {
        return queLikeStatus;
    }

    public void setQueLikeStatus(String queLikeStatus) {
        this.queLikeStatus = queLikeStatus;
    }

    public int getQueLikeTotalCount() {
        return queLikeTotalCount;
    }

    public void setQueLikeTotalCount(int queLikeTotalCount) {
        this.queLikeTotalCount = queLikeTotalCount;
    }

    public String getUserFollowStatus() {
        return userFollowStatus;
    }

    public void setUserFollowStatus(String userFollowStatus) {
        this.userFollowStatus = userFollowStatus;
    }

    public int getQueVoteTotalCount() {
        return queVoteTotalCount;
    }

    public void setQueVoteTotalCount(int queVoteTotalCount) {
        this.queVoteTotalCount = queVoteTotalCount;
    }

    public ArrayList<String> getEndRankName() {
        return endRankName;
    }

    public void setEndRankName(ArrayList<String> endRankName) {
        this.endRankName = endRankName;
    }

    public ArrayList<String> getStartRankName() {
        return startRankName;
    }

    public void setStartRankName(ArrayList<String> startRankName) {
        this.startRankName = startRankName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getQueCommentUser() {
        return queCommentUser;
    }

    public void setQueCommentUser(String queCommentUser) {
        this.queCommentUser = queCommentUser;
    }

    public String getQueVoteStatus() {
        return queVoteStatus;
    }

    public void setQueVoteStatus(String queVoteStatus) {
        this.queVoteStatus = queVoteStatus;
    }

    public ArrayList<String> getIntId() {
        return intId;
    }

    public void setIntId(ArrayList<String> intId) {
        this.intId = intId;
    }

    public ArrayList<String> getIntName() {
        return intName;
    }

    public void setIntName(ArrayList<String> intName) {
        this.intName = intName;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getUserFollowId() {
        return userFollowId;
    }

    public void setUserFollowId(String userFollowId) {
        this.userFollowId = userFollowId;
    }

    public String getQueCategory() {
        return queCategory;
    }

    public void setQueCategory(String queCategory) {
        this.queCategory = queCategory;
    }

    public String getQuePostDate() {
        return quePostDate;
    }

    public void setQuePostDate(String quePostDate) {
        this.quePostDate = quePostDate;
    }

    public String getUserCountryName() {
        return userCountryName;
    }

    public void setUserCountryName(String userCountryName) {
        this.userCountryName = userCountryName;
    }

    public String getQueCommentId() {
        return queCommentId;
    }

    public void setQueCommentId(String queCommentId) {
        this.queCommentId = queCommentId;
    }

    public String getQueComment() {
        return queComment;
    }

    public void setQueComment(String queComment) {
        this.queComment = queComment;
    }

    public String getQueType() {
        return queType;
    }

    public void setQueType(String queType) {
        this.queType = queType;
    }

    public Long getQueTiming() {
        return queTiming;
    }

    public void setQueTiming(Long queTiming) {
        this.queTiming = queTiming;
    }


    public String getQueAnswer() {
        return queAnswer;
    }

    public void setQueAnswer(String queAnswer) {
        this.queAnswer = queAnswer;
    }

    public String getQueImage() {
        return queImage;
    }

    public void setQueImage(String queImage) {
        this.queImage = queImage;
    }

    public String getQueTitle() {
        return queTitle;
    }

    public void setQueTitle(String queTitle) {
        this.queTitle = queTitle;
    }

    public String getQueId() {
        return queId;
    }

    public void setQueId(String queId) {
        this.queId = queId;
    }

    public String getQueOptionFirst() {
        return queOptionFirst;
    }

    public void setQueOptionFirst(String queOptionFirst) {
        this.queOptionFirst = queOptionFirst;
    }

    public String getQueOptionSecond() {
        return queOptionSecond;
    }

    public void setQueOptionSecond(String queOptionSecond) {
        this.queOptionSecond = queOptionSecond;
    }

    public String getQueOptionThird() {
        return queOptionThird;
    }

    public void setQueOptionThird(String queOptionThird) {
        this.queOptionThird = queOptionThird;
    }

    public String getQueOptionFourth() {
        return queOptionFourth;
    }

    public void setQueOptionFourth(String queOptionFourth) {
        this.queOptionFourth = queOptionFourth;
    }

    public String getUserInterestTitle() {
        return userInterestTitle;
    }

    public void setUserInterestTitle(String userInterestTitle) {
        this.userInterestTitle = userInterestTitle;
    }

    public String getUserInterestStartName() {
        return userInterestStartName;
    }

    public void setUserInterestStartName(String userInterestStartName) {
        this.userInterestStartName = userInterestStartName;
    }

    public String getUserInterestEndName() {
        return userInterestEndName;
    }

    public void setUserInterestEndName(String userInterestEndName) {
        this.userInterestEndName = userInterestEndName;
    }

    public String getUserInterestId() {
        return userInterestId;
    }

    public void setUserInterestId(String userInterestId) {
        this.userInterestId = userInterestId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserInterestName() {
        return userInterestName;
    }

    public void setUserInterestName(String userInterestName) {
        this.userInterestName = userInterestName;
    }

    public String getUserPosts() {
        return userPosts;
    }

    public void setUserPosts(String userPosts) {
        this.userPosts = userPosts;
    }

    public String getUserFollowers() {
        return userFollowers;
    }

    public void setUserFollowers(String userFollowers) {
        this.userFollowers = userFollowers;
    }

    public String getUserFollowing() {
        return userFollowing;
    }

    public void setUserFollowing(String userFollowing) {
        this.userFollowing = userFollowing;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserUserName() {
        return userUserName;
    }

    public void setUserUserName(String userUserName) {
        this.userUserName = userUserName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserCountryId() {
        return userCountryId;
    }

    public void setUserCountryId(String userCountryId) {
        this.userCountryId = userCountryId;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }
}
