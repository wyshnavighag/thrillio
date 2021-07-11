package com.company.thrillio;

import com.company.thrillio.bgjobs.WebpageDownloaderTask;
import com.company.thrillio.entities.Bookmark;
import com.company.thrillio.entities.User;
import com.company.thrillio.managers.BookmarkManager;
import com.company.thrillio.managers.UserManager;

import java.util.List;

public class Launch {
    private static List<User> users;
    private static List<List<Bookmark>> bookmarks;

    private static void loadData() {
        System.out.println("1. Loading data ...");
        DataStore.loadData();

        users= UserManager.getInstance().getUsers();
        bookmarks= BookmarkManager.getInstance().getBookmarks();

    }

    private static void printUserData(){
        for(User user : users){
            System.out.println(user);
        }
    }

    private static void printBookmarkData() {
        for (List<Bookmark> bookmarkList : bookmarks) {
            for (Bookmark bookmark : bookmarkList) {
                System.out.println(bookmark);
            }
        }
    }

    private static void start() {
        //System.out.println("\n2. Bookmarking ...");
        for( User user : users){
            View.browse(user,bookmarks);
        }
    }
    
    public static void main(String[] args){
        loadData();
        //start();

        // Background Jobs
        //runDownLoaderJob();

    }

    private static void runDownLoaderJob() {
        WebpageDownloaderTask task = new  WebpageDownloaderTask(true);
        (new Thread(task)).start();
    }


}
