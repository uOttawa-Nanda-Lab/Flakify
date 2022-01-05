@Test public void testGetFeatures(){
  assertEquals(2,manager.getFeatures().length);
  assertEquals(MyFeatures.DELETE_USERS,manager.getFeatures()[0]);
  assertEquals(MyFeatures.EXPERIMENTAL,manager.getFeatures()[1]);
}
