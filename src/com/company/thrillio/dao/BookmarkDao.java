package com.company.thrillio.dao;

import com.company.thrillio.DataStore;
import com.company.thrillio.entities.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookmarkDao {

    public List<List<Bookmark>> getBookmarks(){
        return DataStore.getBookmarks();
    }

    public void saveUserBookmark(UserBookmark userBookmark){
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jid_thrillio?allowPublicKeyRetrieval=true&useSSL=false", "root", "Khushi");
             Statement stmt = conn.createStatement();) {

            if(userBookmark.getBookmark() instanceof Book) {
                saveUserBook(userBookmark, stmt);
            }
            else if(userBookmark.getBookmark() instanceof Movie) {
                saveUserMovie(userBookmark, stmt);;
            }
            else{
                saveUserWebLink(userBookmark, stmt);;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void saveUserBook(UserBookmark userBookmark, Statement stmt) throws SQLException {
        String query = "insert into user_book (user_id, book_id) values ("+ userBookmark.getUser().getId()+", "+ userBookmark.getBookmark().getId() + ")";
        stmt.executeUpdate(query);
    }

    private void saveUserMovie(UserBookmark userBookmark, Statement stmt) throws SQLException {
        String query = "insert into user_movie (user_id, movie_id) values ("+ userBookmark.getUser().getId()+", "+ userBookmark.getBookmark().getId() + ")";
        stmt.executeUpdate(query);

    }

    private void saveUserWebLink(UserBookmark userBookmark, Statement stmt) throws SQLException {
        String query = "insert into user_webLink (user_id, weblink_id) values ("+ userBookmark.getUser().getId()+", "+ userBookmark.getBookmark().getId() + ")";
        stmt.executeUpdate(query);
    }

    public List<WebLink> getAllWebLinks() {
        List<WebLink> result = new ArrayList<>();

        List<List<Bookmark>> bookmarks = DataStore.getBookmarks();
        List<Bookmark> allWebLinks = bookmarks.get(0);

        for (Bookmark bookmark : allWebLinks){
            result.add((WebLink)bookmark);
        }
        return result;
    }
    public List<WebLink> getWebLinks(WebLink.DownloadStatus downloadStatus) {
        List<WebLink> result = new ArrayList<>();

        List<WebLink> allWebLinks = getAllWebLinks();

        for(WebLink webLink : allWebLinks) {
            if (webLink.getDownloadStatus().equals(downloadStatus)){
                result.add(webLink);
            }
        }
        return result;
    }

    public void updateKidFriendlyStatus(Bookmark bookmark) {
        int kidFriendlyStatus = bookmark.getKidFriendlyStatus().ordinal();
        long userId = bookmark.getKidFriendlyMarkedBy().getId();

        String tableToUpdate = "Book";
        if(bookmark instanceof Movie) {
            tableToUpdate = "Movie";
        } else if(bookmark instanceof WebLink) {
            tableToUpdate = "WebLink";
        }

        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jid_thrillio?useSSL=false");
            Statement stmt = conn.createStatement()){
            String query = "update "+ tableToUpdate + " set kid_friendly_status = "+ kidFriendlyStatus + ", kid_friendly_marked_by = "+ userId +" where id = "+ bookmark.getId();
            System.out.println("query (updateKidFriendlyStatus): "+ query);
            stmt.executeUpdate(query);
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void sharedByInfo(Bookmark bookmark){
        long userId = bookmark.getSharedBy().getId();

        String tableToUpdate = "book";
        if (bookmark instanceof WebLink){
            tableToUpdate = "weblink";
        }
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jid_thrillio?useSSL=false");
                Statement stmt = conn.createStatement()) {
            String query = "update" + tableToUpdate + "set shared_by = " + userId + "where id = " + bookmark.getId();
            System.out.println("query (updateKidFriendlyStatus): " + query);
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
