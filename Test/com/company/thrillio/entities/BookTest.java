package com.company.thrillio.entities;

import com.company.thrillio.constants.BookGenre;
import com.company.thrillio.managers.BookmarkManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookTest {
    @Test
    public void testIsKidFriendlyEligible() {
        //Test 1
        Book book = BookmarkManager.getInstance().createBook(4000,    "Walden",1854,    "Wilder Publications", new String[] {"Henry David Thoreau"}, BookGenre.PHILOSOPHY,4.3);
        boolean isKidFriendlyEligible = book.isKidFriendlyEligible();
        assertFalse(isKidFriendlyEligible, "For philosophy genre - isKidFriendlyEligible should return false");

        //Test 2
        book = BookmarkManager.getInstance().createBook(4000,    "Walden",1854,    "Wilder Publications", new String[] {"Henry David Thoreau"}, BookGenre.SELF_HELP,4.3);
        isKidFriendlyEligible = book.isKidFriendlyEligible();
        assertFalse(isKidFriendlyEligible, "For self help genre - isKidFriendlyEligible should return false");
    }

}