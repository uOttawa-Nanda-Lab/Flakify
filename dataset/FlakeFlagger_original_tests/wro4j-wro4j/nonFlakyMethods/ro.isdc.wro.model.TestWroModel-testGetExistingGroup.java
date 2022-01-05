@Test public void testGetExistingGroup(){
  Assert.assertFalse(victim.getGroups().isEmpty());
  final Group group=victim.getGroupByName("g1");
  Assert.assertEquals(1,group.getResources().size());
}
