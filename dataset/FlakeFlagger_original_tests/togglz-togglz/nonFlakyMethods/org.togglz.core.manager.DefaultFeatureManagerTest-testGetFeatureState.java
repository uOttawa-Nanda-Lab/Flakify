@Test public void testGetFeatureState(){
  FeatureState state=manager.getFeatureState(MyFeatures.DELETE_USERS);
  assertEquals(MyFeatures.DELETE_USERS,state.getFeature());
  assertEquals(true,state.isEnabled());
  assertEquals(Arrays.asList("admin"),state.getUsers());
}
