package com.eng.stream.hackathon.bookmark.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.junit.jupiter.api.Test;

public class GroupAdminTests {

public Class<GroupAdmin> groupAdminClass = GroupAdmin.class; 
	
	@Test
	void mustHaveEntityAnnotation() {
		assertTrue(groupAdminClass.isAnnotationPresent(Entity.class));
		
	}
	
	@Test
	void mustHaveTableAnnotation() {
		assertTrue(groupAdminClass.isAnnotationPresent(Entity.class));
	}

	@Test
	void mustMatchTableName()  {
		Table table = groupAdminClass.getAnnotation(Table.class);
		assertEquals("TGROUPADMINSENG",table.name());
	}
	
	@Test
	void validateGroupUserIdField() throws NoSuchFieldException, SecurityException {
		Field groupAdminField = groupAdminClass.getDeclaredField("groupUserId");
		
		//check @Id present
		Id idAnnotation = groupAdminField.getAnnotation(Id.class);
		assertNotNull(idAnnotation);
		
		//check @GeneratedValue present
		GeneratedValue generateValue = groupAdminField.getAnnotation(GeneratedValue.class);
		assertNotNull(generateValue);
		
		//check @Column present
		Column columnAnnotation = groupAdminField.getAnnotation(Column.class);
		assertNotNull(columnAnnotation);
		
		// check for column name and length matches
		assertEquals("GROUPUSERID", columnAnnotation.name());
		assertEquals(255, columnAnnotation.length());
	}
	
	@Test
	void validateUserId() throws NoSuchFieldException, SecurityException {
		Field groupAdminField = groupAdminClass.getDeclaredField("userId");
		Column columnAnnotation = groupAdminField.getAnnotation(Column.class);
		assertNotNull(columnAnnotation);
		assertEquals("USERID", columnAnnotation.name());
		assertEquals(100, columnAnnotation.length());
	}
	
}
