package com.eng.stream.hackathon.bookmark.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eng.stream.hackathon.bookmark.models.Group;
import com.eng.stream.hackathon.bookmark.models.GroupBean;
import com.eng.stream.hackathon.bookmark.models.GroupReference;
import com.eng.stream.hackathon.bookmark.services.GroupService;
import com.eng.stream.hackathon.bookmark.utils.BeanToEntityConverter;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/groups")
public class GroupsController {

	@Autowired
	private GroupService groupService;
	
	private  static final String ALL_GROUPS_MAPPING = "/api/v1/groups";
	private  static final String CREATE_GROUP = "/createGroup";
	private static final String GROUP_TYPES="/groupTypes";
	private static final String GROUP_VALUES="/groupValues";
	
	@GetMapping
	@ApiOperation(value = "Get all the groups", notes = "This service will get all the Groups available in the system", response =ResponseEntity.class )
	public ResponseEntity<List<Group>> allGroups() {
		try {
            return ResponseEntity.ok()
                    .location((new URI(ALL_GROUPS_MAPPING)))
                    .body(groupService.findAllGroups());
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
	}
	
	@PostMapping("/createGroup")
	@ApiOperation(value = "Create the group", notes = "Service to create a group in the system", response =ResponseEntity.class )
	public ResponseEntity<Group> createGroup(@RequestBody GroupBean groupBean, @RequestHeader("username") String currentUser) {
		try {
			groupBean.setUsername(currentUser);
            return ResponseEntity.created(new URI(CREATE_GROUP))
                    .body(groupService.createGroup(BeanToEntityConverter.convertToEntity(groupBean)));
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
	}
	
	@GetMapping(GROUP_TYPES)
	@ApiOperation(value = "Get all the group values based on group type", notes = "This service will get all the group values for the given group type", response =ResponseEntity.class)
	public ResponseEntity<String[]> groupTypes() {
		try {
            return ResponseEntity.ok()
                    .location((new URI(GROUP_TYPES)))
                    .body(groupService.groupTypes());
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
	}
	
	@GetMapping(GROUP_VALUES)
	@ApiOperation(value = "Get all the group values", notes = "This service will get all the Groups values based on the given group type", response =ResponseEntity.class)
	public ResponseEntity<List<GroupReference>> groupValues(@RequestParam String groupType) {
		try {
            return ResponseEntity.ok()
                    .location((new URI(GROUP_TYPES)))
                    .body(groupService.groupValues(groupType));
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
	}
	
}
