@Test(expected=InvalidGroupNameException.class) public void testGetInvalidGroup(){
  Assert.assertFalse(victim.getGroups().isEmpty());
  victim.getGroupByName("INVALID_GROUP");
}
