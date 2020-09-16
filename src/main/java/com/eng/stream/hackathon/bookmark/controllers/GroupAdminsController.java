package com.eng.stream.hackathon.bookmark.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eng.stream.hackathon.bookmark.models.GroupAdmin;
import com.eng.stream.hackathon.bookmark.models.GroupAdminBean;
import com.eng.stream.hackathon.bookmark.services.GroupAdminService;
import com.eng.stream.hackathon.bookmark.utils.BeanToEntityConverter;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/groupAdmins")
public class GroupAdminsController {

	@Autowired
	private GroupAdminService adminService;
	
	private  static final String ALL_GROUP_ADMINS = "/api/v1/groupAdmins";
	private  static final String CREATE_GROUP_ADMIN = "/createGroupAdmin";
	private  static final String DELETE_GROUP_ADMIN = "/deleteGroupAdmin";
	@GetMapping
	@ApiOperation(value = "Get all the group admins", notes = "This service will get all the admins of group", response =ResponseEntity.class )
	public ResponseEntity<List<GroupAdmin>> allGroups(@RequestParam Long groupId) {
		try {
            return ResponseEntity.ok()
                    .location((new URI(ALL_GROUP_ADMINS)))
                    .body(adminService.getGroupAdmins(groupId));
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
	}
	
	@PostMapping(CREATE_GROUP_ADMIN)
	@ApiOperation(value = "Create the group admin", notes = "Service to create a admin of a group  in the system", response =ResponseEntity.class )
	public ResponseEntity<GroupAdmin> createGroupAdmin(@RequestBody GroupAdminBean groupAdminBean,@RequestHeader("username") String currentUser) {
		try {
			groupAdminBean.setUsername(currentUser);
            return ResponseEntity.created(new URI(CREATE_GROUP_ADMIN))
                    .body(adminService.addGroupAdmin(BeanToEntityConverter.convertToEntity(groupAdminBean)));
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
	}
	
	@DeleteMapping(DELETE_GROUP_ADMIN)
    public ResponseEntity<?> deleteGroupAdmin(@RequestParam Long groupUserId) {
		adminService.deleteGroupAdmin(groupUserId);
        return ResponseEntity.ok().build();
    }
}
