# TDDBookmarking

## UserStory
````
As a user I want to bookmark a URL (URLs must be valid)
````
### Tests:
- [x] ensure that a Bookmark/Collection can be created
- [x] ensure that a Bookmark can be added to the BookmarkCollection
- [x] ensure that a given string can be parsed to a valid URL
- [x] ensure that a given string is an invalid URL and throws a MalformedURLException 
// https://docs.oracle.com/javase/7/docs/api/java/net/URL.html#URL(java.lang.String)

## UserStory
````
As a user I want to be able to Tag a URL with a keyword
````
### Tests:
- [x] ensure that a bookmark can be tagged

## UserStory
````
As a user when I add a duplicate bookmark, I want the system to increase the rating of that bookmark, because no exact duplicates should exist
````
### Tests:
- [x] ensure that a browser can have multiple bookmarks
- [x] ensure that a browser doesnt allow duplicate bookmarks
- [x] ensure that a rating starts with zero
- [x] ensure that a duplicated bookmark inside a browser raises its rating instead of inserting it again

## UserStory
````
As a user I want to be able to filter bookmarks by one keyword
````
### Tests:
- [x] ensure that a browser can find bookmarks based on its tag
- [x] ensure that a browser can find bookmarks based on its URLs

## User Story
````
As a user I want to add a current date/time when I add a bookmark
````
### Tests:
- [x] ensure that the time in Bookmark is not null
- [x] ensure that the time in Bookmark is from today when its getting added

## User Story
````
As a user I want to know how many of my bookmarks are secure URLs
````
### Tests:
- [x] ensure that a browser counts bookmarks with https
- [x] ensure that a browser does not count bookmarks with http

## User Story
````
As a user I want a new bookmark to become associated with other bookmarks that are from the same domain
````
### Tests:
- [x] ensure that a browser can find bookmarks based on their domain (host name)

## User Story
````
As a user I want to remove a TAG from a bookmark
````
### Tests:
- [ ] ensure that 

## User Story
````
As a user I want to list my bookmarks sorted by rating (highest to smallest)
````
### Tests:
- [x] ensure that the browser sorts the bookmarks correctly by their rating  - descending
- [x] ensure that the browser sorts the bookmarks correctly by their rating even when one increases its rating
- [ ] ensure that the browser sorts the bookmarks correctly by their rating even when one decreases its rating
- [ ] ensure that a newly created bookmark is at the bottom of the order when no other equal rating exist
- [ ] ensure that bookmarks are sorted correctly by their rating and on equal ones, they are ordered by their URL
