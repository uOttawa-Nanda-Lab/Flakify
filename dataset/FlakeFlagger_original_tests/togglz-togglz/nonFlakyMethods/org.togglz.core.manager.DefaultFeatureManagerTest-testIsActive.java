@Test public void testIsActive(){
  featureUserProvider.setFeatureUser(null);
  assertEquals(false,manager.isActive(MyFeatures.DELETE_USERS));
  featureUserProvider.setFeatureUser(new SimpleFeatureUser("admin",false));
  assertEquals(true,manager.isActive(MyFeatures.DELETE_USERS));
  featureUserProvider.setFeatureUser(new SimpleFeatureUser("somebody",false));
  assertEquals(false,manager.isActive(MyFeatures.DELETE_USERS));
  featureUserProvider.setFeatureUser(null);
  assertEquals(false,manager.isActive(MyFeatures.EXPERIMENTAL));
}
