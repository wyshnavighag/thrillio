package com.company.thrillio.entities;

import com.company.thrillio.constants.KidFriendlyStatus;

public abstract class Bookmark {

    private long id;
    private String title;
    private String profileUrl;
    private KidFriendlyStatus kidFriendlyStatus= KidFriendlyStatus.UNKNOWN;
    private User KidFriendlyMarkedBy;

    public User getSharedBy() {
        return sharedBy;
    }

    public void setSharedBy(User sharedBy) {
        this.sharedBy = sharedBy;
    }

    private User sharedBy;

    public User getKidFriendlyMarkedBy() {
        return KidFriendlyMarkedBy;
    }

    public void setKidFriendlyMarkedBy(User kidFriendlyMarkedBy) {
        KidFriendlyMarkedBy = kidFriendlyMarkedBy;
    }

    @Override
    public String toString() {
        return "Bookmark{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", profileUrl='" + profileUrl + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public KidFriendlyStatus getKidFriendlyStatus() {
        return kidFriendlyStatus;
    }

    public void setKidFriendlyStatus(KidFriendlyStatus kidFriendlyStatus) {
        this.kidFriendlyStatus = kidFriendlyStatus;
    }
    public abstract boolean isKidFriendlyEligible();
}
