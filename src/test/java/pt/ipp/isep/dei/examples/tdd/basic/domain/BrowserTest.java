package pt.ipp.isep.dei.examples.tdd.basic.domain;

import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BrowserTest {

    /**
     * User Story: As a user when I add a duplicate bookmark, I want the system to increase the rating of that bookmark, because no exact duplicates should exist
     */
    @Test
    public void ensureBookmarksCanBeAddedToBrowser() {
        assertDoesNotThrow(() -> {
            // Arrange
            Bookmark google = new Bookmark("https://www.google.com");
            Bookmark amazon = new Bookmark("https://www.amazon.com");
            Browser browser = new Browser();
            List<Bookmark> bookmarks = browser.getBookmarks();
            int expectedSize = 2;
            int actualSize;
            int initSize = bookmarks.size();
            int initExpectedSize = 0;

            // Act
            browser.add(google);
            browser.add(amazon);
            actualSize = bookmarks.size();

            // Assert
            assertEquals(initExpectedSize, initSize);
            assertEquals(expectedSize, actualSize);
        });
    }

    /**
     * User Story: As a user when I add a duplicate bookmark, I want the system to increase the rating of that bookmark, because no exact duplicates should exist
     */
    @Test
    public void ensureBrowserDoesntAllowDuplicatedBookmarks() {
        assertDoesNotThrow(() -> {
            // Arrange
            Bookmark google = new Bookmark("https://www.google.com");
            Bookmark duplicatedGoogle = new Bookmark("https://www.google.com");
            Browser browser = new Browser();
            List<Bookmark> bookmarks = browser.getBookmarks();
            int expectedSize = 1;
            int actualSize;

            // Act
            browser.add(google);
            browser.add(duplicatedGoogle);
            actualSize = bookmarks.size();

            // Assert
            assertEquals(expectedSize, actualSize);
        });
    }

    /**
     * User Story: As a user when I add a duplicate bookmark, I want the system to increase the rating of that bookmark, because no exact duplicates should exist
     */
    @Test
    public void ensureBrowserRaisesRatingOfBookmarkByInsertingADuplicatedOne() {
        assertDoesNotThrow(() -> {
            // Arrange
            Bookmark google = new Bookmark("https://www.google.com");
            Bookmark duplicatedGoogle = new Bookmark("https://www.google.com");
            Browser browser = new Browser();
            int expectedRating = 1;
            int googleRating;

            // Act
            browser.add(google);
            browser.add(duplicatedGoogle);
            googleRating = browser.find(google).get().getRating();

            // Assert
            assertEquals(expectedRating, googleRating);
        });
    }

    /**
     * User Story: As a user I want to be able to filter bookmarks by one keyword
     */
    @Test
    public void ensureBrowserFindsBookmarksByOneKeywordInsideTags() {
        assertDoesNotThrow(() -> {
            // Arrange
            Bookmark google = new Bookmark("https://www.google.com");
            Bookmark baeldung = new Bookmark("https://www.baeldung.com");
            Bookmark facebook = new Bookmark("https://www.facebook.com");
            Browser browser = new Browser();
            List<Bookmark> expectedBookmarks = asList(google, baeldung);
            List<Bookmark> foundBookmarks;
            String keyword = "Programming";

            google.setTag("Programming");
            baeldung.setTag("Programming");
            facebook.setTag("StalkingPeople");
            browser.add(google);
            browser.add(baeldung);
            browser.add(facebook);

            //Act
            foundBookmarks = browser.findBookmarksTaggedWith(keyword);

            // Assert
            assertEquals(expectedBookmarks, foundBookmarks);
        });
    }

    /**
     * User Story: As a user I want to be able to filter bookmarks by one keyword
     */
    @Test
    public void ensureBrowserFindsBookmarksByOneKeywordInsideURLs() {
        assertDoesNotThrow(() -> {
            // Arrange
            Bookmark google = new Bookmark("https://www.google.com");
            Bookmark baeldung = new Bookmark("https://www.baeldung.com");
            Bookmark facebook = new Bookmark("https://www.facebook.com");
            Browser browser = new Browser();
            List<Bookmark> expectedBookmarks = asList(google, baeldung, facebook);
            List<Bookmark> foundBookmarks;
            String keyword = "www";

            browser.add(google);
            browser.add(baeldung);
            browser.add(facebook);

            //Act
            foundBookmarks = browser.findBookmarksWithUrlContaining(keyword);

            // Assert
            assertEquals(expectedBookmarks, foundBookmarks);
        });
    }

    /**
     * User Story: As a user I want to be able to filter bookmarks by one keyword
     */
    @Test
    public void ensureBrowserDoesntFindBookmarksByOneKeywordInsideURLs() {
        assertDoesNotThrow(() -> {
            // Arrange
            Bookmark google = new Bookmark("https://www.google.com");
            Bookmark baeldung = new Bookmark("https://www.baeldung.com");
            Bookmark facebook = new Bookmark("https://www.facebook.com");
            Browser browser = new Browser();
            List<Bookmark> expectedBookmarks = asList(google, baeldung, facebook);
            List<Bookmark> foundBookmarks;
            String keyword = "amazon";

            browser.add(google);
            browser.add(baeldung);
            browser.add(facebook);

            //Act
            foundBookmarks = browser.findBookmarksWithUrlContaining(keyword);

            // Assert
            assertEquals(0, foundBookmarks.size());
        });
    }

    /**
     * User Story:
     * As a user I want to know how many of my bookmarks are secure URLs
     * Test:
     * ensure that a browser counts bookmarks with secure URLs
     */
    @Test
    public void ensureBrowserCountsBookmarkWithSecureURL() {
        // Arrange
        try {
            // Arrange
            Bookmark google = new Bookmark("https://www.google.com");
            Browser browser = new Browser();
            long expectedCount = 1;
            long actualCount;
            browser.add(google);

            // Act
            actualCount = browser.countSecureURLs();

            // Assert
            assertEquals(expectedCount, actualCount);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void ensureBrowserDoesNotCountBookmarkWithNoSecureURL() {
        // Arrange
        try {
            // Arrange
            Bookmark google = new Bookmark("http://www.google.com");
            Browser browser = new Browser();
            long expectedCount = 0;
            long actualCount;
            browser.add(google);

            // Act
            actualCount = browser.countSecureURLs();

            // Assert
            assertEquals(expectedCount, actualCount);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void ensureBrowserCountsOnlyBookmarkWithSecureURL() {
        // Arrange
        try {
            // Arrange
            Bookmark google = new Bookmark("https://www.google.com");
            Bookmark baeldung = new Bookmark("https://www.baeldung.com");
            Bookmark facebook = new Bookmark("http://www.facebook.com");
            Browser browser = new Browser();
            long expectedCount = 2;
            long actualCount;
            browser.add(google);
            browser.add(baeldung);
            browser.add(facebook);

            // Act
            actualCount = browser.countSecureURLs();

            // Assert
            assertEquals(expectedCount, actualCount);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * User Story:
     * As a user I want a new bookmark to become associated with other bookmarks that are from the same domain
     * Test:
     * ensure that a browser can find bookmarks based on their domain (host name)
     */
    @Test
    public void ensureBrowserFindsBookmarksByDomain() {
        // Arrange
        try {
            Bookmark google1 = new Bookmark("https://www.google.com");
            Bookmark google2 = new Bookmark("https://www.google.com/images");
            Bookmark google3 = new Bookmark("http://www.google.com/maps");
            Bookmark newBookmark = new Bookmark("http://www.google.com/news");
            Bookmark baeldung = new Bookmark("https://www.baeldung.com");
            Bookmark facebook = new Bookmark("http://www.facebook.com");
            Browser browser = new Browser();

            List<Bookmark> expectedBookmarks = asList(google1, google2, google3, newBookmark);
            List<Bookmark> foundBookmarks;

            browser.add(google1);
            browser.add(google2);
            browser.add(google3);
            browser.add(newBookmark);
            browser.add(baeldung);
            browser.add(facebook);

            // Act
            foundBookmarks = browser.getAssociatedURLs(newBookmark);

            // Assert
            assertEquals(expectedBookmarks, foundBookmarks);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    /**
     * User Story:
     * As a user I want to list my bookmarks sorted by rating (highest to smallest)
     */
    @Test
    public void ensureBrowserSortsTheBookmarksCorrectlyByTheirRatingInDescendingOrder() throws MalformedURLException {
        // Arrange
        Bookmark google = new Bookmark("https://www.google.com");
        Bookmark baeldung = new Bookmark("https://www.baeldung.com");
        Bookmark facebook = new Bookmark("http://www.facebook.com");
        Browser browser = new Browser();
        List<Bookmark> bookmarks = browser.getBookmarks();
        Bookmark firstBookmark;
        Bookmark secondBookmark;
        Bookmark thirdBookmark;

        browser.add(google);
        browser.add(baeldung);
        browser.add(baeldung);
        browser.add(facebook);
        browser.add(facebook);
        browser.add(facebook);

        // Act
        browser.sortBookmarksByRatingDescending();
        firstBookmark = bookmarks.get(0);
        secondBookmark = bookmarks.get(1);
        thirdBookmark = bookmarks.get(2);

        // Assert
        assertThat(firstBookmark, is(facebook));
        assertThat(secondBookmark, is(baeldung));
        assertThat(thirdBookmark, is(google));
    }

    /**
     * User Story:
     * As a user I want to list my bookmarks sorted by rating (highest to smallest)
     * ensure that the browser sorts the bookmarks correctly by their rating even when one increases its rating
     */
    @Test
    public void ensureBrowserSortsTheBookmarksCorrectlyByTheirRatingAfterOneIncreasesItsRating() throws MalformedURLException {
        // Arrange
        Bookmark google = new Bookmark("https://www.google.com");
        Bookmark baeldung = new Bookmark("https://www.baeldung.com");
        Bookmark facebook = new Bookmark("http://www.facebook.com");
        Browser browser = new Browser();
        List<Bookmark> bookmarks = browser.getBookmarks();
        Bookmark firstBookmark;
        Bookmark secondBookmark;
        Bookmark thirdBookmark;

        browser.add(google);
        browser.add(baeldung);
        browser.add(baeldung);
        browser.add(facebook);
        browser.add(facebook);
        browser.add(facebook);
        browser.sortBookmarksByRatingDescending();
        browser.add(google);
        browser.add(google);
        browser.add(google);

        // Act
        browser.sortBookmarksByRatingDescending();
        firstBookmark = bookmarks.get(0);
        secondBookmark = bookmarks.get(1);
        thirdBookmark = bookmarks.get(2);

        // Assert
        assertThat(firstBookmark, is(google));
        assertThat(secondBookmark, is(facebook));
        assertThat(thirdBookmark, is(baeldung));
    }

    /**
     * User Story:
     * As a user I want to list my bookmarks sorted by rating (highest to smallest)
     */
    @Test
    public void ensureBrowserSortsTheBookmarksCorrectlyByTheirRatingAfterNewOneIsAdded() throws MalformedURLException {
        // Arrange
        Bookmark google = new Bookmark("https://www.google.com");
        Bookmark baeldung = new Bookmark("https://www.baeldung.com");
        Bookmark facebook = new Bookmark("http://www.facebook.com");
        Browser browser = new Browser();
        List<Bookmark> bookmarks = browser.getBookmarks();
        Bookmark firstBookmark;
        Bookmark secondBookmark;
        Bookmark thirdBookmark;

        browser.add(baeldung);
        browser.add(baeldung);
        browser.add(facebook);
        browser.add(facebook);
        browser.add(facebook);
        browser.sortBookmarksByRatingDescending();
        browser.add(google);

        // Act
        browser.sortBookmarksByRatingDescending();
        firstBookmark = bookmarks.get(0);
        secondBookmark = bookmarks.get(1);
        thirdBookmark = bookmarks.get(2);

        // Assert
        assertThat(firstBookmark, is(facebook));
        assertThat(secondBookmark, is(baeldung));
        assertThat(thirdBookmark, is(google));
    }

    /**
     * User Story:
     * As a user I want to list my bookmarks sorted by rating (highest to smallest)
     */
    @Test
    public void ensureBrowserSortsTheBookmarksCorrectlyByTheirRatingAndOnTheirURLOnTwoEqualRatings() throws MalformedURLException {
        // Arrange
        Bookmark google = new Bookmark("https://www.google.com");
        Bookmark baeldung = new Bookmark("https://www.baeldung.com");
        Bookmark facebook = new Bookmark("https://www.facebook.com");
        Browser browser = new Browser();
        List<Bookmark> bookmarks = browser.getBookmarks();
        Bookmark firstBookmark;
        Bookmark secondBookmark;
        Bookmark thirdBookmark;

        browser.add(google);
        browser.add(baeldung);
        browser.add(baeldung);
        browser.add(facebook);
        browser.add(facebook);

        // Act
        browser.sortBookmarksByRatingDescending();
        firstBookmark = bookmarks.get(0);
        secondBookmark = bookmarks.get(1);
        thirdBookmark = bookmarks.get(2);

        // Assert
        assertThat(firstBookmark, is(baeldung));
        assertThat(secondBookmark, is(facebook));
        assertThat(thirdBookmark, is(google));
    }

    /**
     * User Story:
     * As a user I want to list my bookmarks sorted by rating (highest to smallest)
     */
    @Test
    public void ensureBrowserSortsTheBookmarksCorrectlyByTheirRatingAndOnTheirURLOnEqualRatings() throws MalformedURLException {
        // Arrange
        Bookmark google = new Bookmark("https://www.google.com");
        Bookmark baeldung = new Bookmark("https://www.baeldung.com");
        Bookmark facebook = new Bookmark("https://www.facebook.com");
        Browser browser = new Browser();
        List<Bookmark> bookmarks = browser.getBookmarks();
        Bookmark firstBookmark;
        Bookmark secondBookmark;
        Bookmark thirdBookmark;

        browser.add(google);
        browser.add(google);
        browser.add(baeldung);
        browser.add(baeldung);
        browser.add(facebook);
        browser.add(facebook);

        // Act
        browser.sortBookmarksByRatingDescending();
        firstBookmark = bookmarks.get(0);
        secondBookmark = bookmarks.get(1);
        thirdBookmark = bookmarks.get(2);

        // Assert
        assertThat(firstBookmark, is(baeldung));
        assertThat(secondBookmark, is(facebook));
        assertThat(thirdBookmark, is(google));
    }
}

//         bookmarks.forEach(bookmark -> System.out.println(bookmark.getRating() + " " + bookmark.getUrl()));