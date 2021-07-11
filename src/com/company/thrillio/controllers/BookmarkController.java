package com.company.thrillio.controllers;

import com.company.thrillio.constants.KidFriendlyStatus;
import com.company.thrillio.entities.Bookmark;
import com.company.thrillio.entities.User;
import com.company.thrillio.managers.BookmarkManager;

public class BookmarkController {
    private static BookmarkController instance = new BookmarkController();
    private BookmarkController() {}

    public static BookmarkController getInstance(){
        return instance;
    }

    public void saveUserBookmark(User user, Bookmark bookmark){
        BookmarkManager.getInstance().saveUserBookmark(user, bookmark);
    }

    public void setKidFriendlyStatus(User user, KidFriendlyStatus kidFriendlyStatus, Bookmark bookmark) {
        BookmarkManager.getInstance().setKidFriendlyStatus(user, kidFriendlyStatus, bookmark);
    }

    public void share(User user, Bookmark bookmark) {
        BookmarkManager.getInstance().share(user, bookmark);
    }
}
