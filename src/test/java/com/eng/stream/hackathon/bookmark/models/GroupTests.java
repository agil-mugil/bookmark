package com.eng.stream.hackathon.bookmark.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.annotation.JsonFormat;


class GroupTests {

	public Class<Group> groupClass = Group.class; 
	
	@Test
	void mustHaveEntityAnnotation() {
		assertTrue(groupClass.isAnnotationPresent(Entity.class));
		
	}
	
	@Test
	void mustHaveTableAnnotation() {
		assertTrue(groupClass.isAnnotationPresent(Entity.class));
	}

	@Test
	void mustMatchTableName()  {
		Table table = groupClass.getAnnotation(Table.class);
		assertEquals("TBOOKMARKGROUPSENG",table.name());
	}
	
	@Test
	void validateGroupIdField() throws NoSuchFieldException, SecurityException {
		Field groupField = groupClass.getDeclaredField("groupId");
		
		//check @Id present
		Id idAnnotation = groupField.getAnnotation(Id.class);
		assertNotNull(idAnnotation);
		
		//check @GeneratedValue present
		GeneratedValue generateValue = groupField.getAnnotation(GeneratedValue.class);
		assertNotNull(generateValue);
		
		//check @Column present
		Column columnAnnotation = groupField.getAnnotation(Column.class);
		assertNotNull(columnAnnotation);
		
		// check for column name and length matches
		assertEquals("GROUPID", columnAnnotation.name());
		assertEquals(255, columnAnnotation.length());
	}
	
	@Test
	void validateGroupType() throws NoSuchFieldException, SecurityException {
		Field groupField = groupClass.getDeclaredField("groupType");
		Column columnAnnotation = groupField.getAnnotation(Column.class);
		assertNotNull(columnAnnotation);
		assertEquals("GROUPTYPE", columnAnnotation.name());
		assertEquals(15, columnAnnotation.length());
	}
	
	@Test
	void validateGroupValue() throws NoSuchFieldException, SecurityException {
		Field groupField = groupClass.getDeclaredField("groupValue");
		Column columnAnnotation = groupField.getAnnotation(Column.class);
		assertNotNull(columnAnnotation);
		assertEquals("GROUPVALUE", columnAnnotation.name());
		assertEquals(30, columnAnnotation.length());
	}
	
	@Test
	void validateGroupCreator() throws NoSuchFieldException, SecurityException {
		Field groupField = groupClass.getDeclaredField("creator");
		Column columnAnnotation = groupField.getAnnotation(Column.class);
		assertNotNull(columnAnnotation);
		assertEquals("GROUPCREATOR", columnAnnotation.name());
		assertEquals(100, columnAnnotation.length());
	}
	
	@Test
	void validateModifier() throws NoSuchFieldException, SecurityException {
		Field groupField = groupClass.getDeclaredField("modifier");
		Column columnAnnotation = groupField.getAnnotation(Column.class);
		assertNotNull(columnAnnotation);
		assertEquals("OPEMOD", columnAnnotation.name());
		assertEquals(100, columnAnnotation.length());
	}
	
	@Test
	void validateCreatedDate() throws NoSuchFieldException, SecurityException {
		Field groupField = groupClass.getDeclaredField("createdDate");
		Column columnAnnotation = groupField.getAnnotation(Column.class);
		assertNotNull(columnAnnotation);
		assertEquals("DATCRE", columnAnnotation.name());
		
		//check for date format 
		JsonFormat dateFomat=groupField.getAnnotation(JsonFormat.class);
		assertNotNull(dateFomat);
		assertEquals("MM-dd-yyyy", dateFomat.pattern());
	}
	
	@Test
	void validateModifierDate() throws NoSuchFieldException, SecurityException {
		Field groupField = groupClass.getDeclaredField("modifiedDate");
		Column columnAnnotation = groupField.getAnnotation(Column.class);
		assertNotNull(columnAnnotation);
		assertEquals("DATMOD", columnAnnotation.name());
		
		//check for date format 
		JsonFormat dateFomat=groupField.getAnnotation(JsonFormat.class);
		assertNotNull(dateFomat);
		assertEquals("MM-dd-yyyy", dateFomat.pattern());
	}
	
	@Test
	void validateEraser() throws NoSuchFieldException, SecurityException {
		Field groupField = groupClass.getDeclaredField("eraser");
		Column columnAnnotation = groupField.getAnnotation(Column.class);
		assertNotNull(columnAnnotation);
		assertEquals("OPEDEL", columnAnnotation.name());
		assertEquals(100, columnAnnotation.length());
	}
	
	@Test
	void validateGroupAdmins() throws NoSuchFieldException, SecurityException {
		Field groupField = groupClass.getDeclaredField("groupAdmins");
		OneToMany relationShipAnnoation = groupField.getAnnotation(OneToMany.class);
		assertNotNull(relationShipAnnoation);
	}
	
	@Test
	void validateGroupCards() throws NoSuchFieldException, SecurityException {
		Field groupField = groupClass.getDeclaredField("groupCards");
		OneToMany relationShipAnnoation = groupField.getAnnotation(OneToMany.class);
		assertNotNull(relationShipAnnoation);
	}
}
