package com.eng.stream.hackathon.bookmark.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eng.stream.hackathon.bookmark.EntityNotFoundException;
import com.eng.stream.hackathon.bookmark.models.Bookmark;
import com.eng.stream.hackathon.bookmark.models.BookmarkBean;
import com.eng.stream.hackathon.bookmark.services.BookmarkService;
import com.eng.stream.hackathon.bookmark.utils.BeanToEntityConverter;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/bookmarks")
public class BookmarksController {

	@Autowired
	private BookmarkService bookmarkService;
	private Logger logger = LogManager.getLogger(BookmarksController.class);

	private  static final String ALL_BOOKMARKS = "/api/v1/bookmarks";
	private  static final String CREATE_BOOKMARK = "/createBookmark";
	
	@GetMapping
	@ApiOperation(value = "Get all the bookmarks", notes = "This service is to get all the bookmarks", response =ResponseEntity.class )
	public ResponseEntity<List<Bookmark>> allBookmarks() throws EntityNotFoundException {
		try {
			logger.info("Inside allBookmarks service");
            return ResponseEntity.ok()
                    .location((new URI(ALL_BOOKMARKS)))
                    .body(bookmarkService.getAllBookmarks());
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
	}
	
	@PostMapping("/createBookmark")
	@ApiOperation(value = "Create the bookmark", notes = "Service to create a bookmark", response =ResponseEntity.class )
	public ResponseEntity<Bookmark> createBookmark(@RequestBody BookmarkBean bookmarkBean, @RequestHeader("username") String currentUser) {
		try {
			bookmarkBean.setCurrentUser(currentUser);
            return ResponseEntity.created(new URI(CREATE_BOOKMARK))
                    .body(bookmarkService.createBookmark(BeanToEntityConverter.convertToEntity(bookmarkBean)));
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
	}
	
}
