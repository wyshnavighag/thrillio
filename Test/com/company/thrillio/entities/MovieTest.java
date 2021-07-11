package com.company.thrillio.entities;

import com.company.thrillio.constants.MovieGenre;
import com.company.thrillio.managers.BookmarkManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class MovieTest {

    @Test
    public void testIsKidFriendlyEligible() {
        //Test 1
        Movie movie = BookmarkManager.getInstance().createMovie(3000, "Citizen Kane", "", 1941, new String[] {"Orson Welles","Joseph Cotten"}, new String[] {"Orson Welles"}, MovieGenre.HORROR, 8.5);
        boolean isKidFriendlyEligible = movie.isKidFriendlyEligible();
        assertFalse(isKidFriendlyEligible, "For Horror Genre - isKidFriendlyEligible should return false");

        //Test 2
        movie = BookmarkManager.getInstance().createMovie(3000, "Citizen Kane", "", 1941, new String[] {"Orson Welles","Joseph Cotten"}, new String[] {"Orson Welles"}, MovieGenre.THRILLERS, 8.5);
        isKidFriendlyEligible = movie.isKidFriendlyEligible();
        assertFalse(isKidFriendlyEligible, "For Thriller Genre - isKidFriendlyEligible should return false");
    }
}