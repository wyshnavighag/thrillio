package com.company.thrillio;

import com.company.thrillio.constants.KidFriendlyStatus;
import com.company.thrillio.constants.UserType;
import com.company.thrillio.controllers.BookmarkController;
import com.company.thrillio.entities.Bookmark;
import com.company.thrillio.entities.User;
import com.company.thrillio.partner.Shareable;

import java.util.List;

public class View {

    public static void browse(User user, List<List<Bookmark>> bookmarks) {
        System.out.println("\n" + user.getEmail() + " is browsing items ...");
        int bookmarkCount=0;

        for(List<Bookmark> bookmarkList: bookmarks){
            for(Bookmark bookmark: bookmarkList){
                //if(bookmarkCount<DataStore.USER_BOOKMARK_LIMIT){
                    boolean isBookmarked = getBookmarkDecision(bookmark);
                    if(isBookmarked){
                        bookmarkCount++;
                        BookmarkController.getInstance().saveUserBookmark(user, bookmark);

                        System.out.println("New item bookmarked - "+bookmark);
                    }
                // }


                if(user.getUserType().equals(UserType.EDITOR)||user.getUserType().equals(UserType.CHIEF_EDITOR)){

                    //Marking as kid friendly
                    if(bookmark.isKidFriendlyEligible() && bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.UNKNOWN)){
                        KidFriendlyStatus kidFriendlyStatus = getKidFriendlyStatusDecision(bookmark);
                        if(!kidFriendlyStatus.equals(KidFriendlyStatus.UNKNOWN)){
                            BookmarkController.getInstance().setKidFriendlyStatus(user,kidFriendlyStatus,bookmark);


                        }
                    }
                    // Sharing!!
                    if (bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.APPROVED)
                        && bookmark instanceof Shareable) {
                       boolean isShared = getShareDecision();
                       if(isShared) {
                           BookmarkController.getInstance().share(user, bookmark);
                       }
                    }
                }


            }
        }
    }
    // TODO: Below methods simulate user input. After IO, we take input via console.
    private static boolean getShareDecision() {
        return Math.random()<0.5?true:false;
    }

    private static KidFriendlyStatus getKidFriendlyStatusDecision(Bookmark bookmark) {
       double decision = Math.random();

       return decision < 0.4 ? KidFriendlyStatus.APPROVED
               : (decision >= 0.4 && decision < 0.8) ? KidFriendlyStatus.REJECTED
                      : KidFriendlyStatus.UNKNOWN;
        /*return Math.random()<0.4 ? KidFriendlyStatus.APPROVED:
                (Math.random()>=0.4&&Math.random()<0.8)? KidFriendlyStatus.REJECTED : KidFriendlyStatus.UNKNOWN;*/
    }

    private static boolean getBookmarkDecision(Bookmark bookmark) {
        return !(Math.random() < 0.5 ? false : true);
    }

    /*public static void bookmark(User user, Bookmark[][] bookmarks){
        System.out.println("\n"+user.getEmail()+" is bookmarking");
        for(int i=0;i<DataStore.USER_BOOKMARK_LIMIT;i++){
            int typeOffset = (int) (Math.random() * DataStore.BOOKMARK_TYPES_COUNT);
            int bookmark0ffset = (int) (Math.random() * DataStore.BOOKMARK_COUNT_PER_TYPE);

            Bookmark bookmark = bookmarks[typeOffset][bookmark0ffset];

            BookmarkController.getInstance().saveUserBookmark(user, bookmark);

            System.out.println(bookmark)
        }
    }*/
}
