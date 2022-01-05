@Test public void testGetSchema(){
  ProviderModule module=new TestProviderModule();
  ClusterController controller=new ClusterControllerImpl(module);
  Schema schema=controller.getSchema(Resource.Type.Host);
  ResourceProvider resourceProvider=module.getResourceProvider(Resource.Type.Host);
  Map<Resource.Type,String> keyPropertyIds=resourceProvider.getKeyPropertyIds();
  for (  Map.Entry<Resource.Type,String> entry : keyPropertyIds.entrySet()) {
    Assert.assertEquals(entry.getValue(),schema.getKeyPropertyId(entry.getKey()));
  }
  Map<String,Set<String>> categories=schema.getCategoryProperties();
  for (  String propertyId : resourceProvider.getPropertyIds()) {
    String category=PropertyHelper.getPropertyCategory(propertyId);
    Set<String> properties=categories.get(category);
    Assert.assertNotNull(properties);
    Assert.assertTrue(properties.contains(PropertyHelper.getPropertyName(propertyId)));
  }
  List<PropertyProvider> propertyProviders=module.getPropertyProviders(Resource.Type.Host);
  for (  PropertyProvider propertyProvider : propertyProviders) {
    for (    String propertyId : propertyProvider.getPropertyIds()) {
      String category=PropertyHelper.getPropertyCategory(propertyId);
      Set<String> properties=categories.get(category);
      Assert.assertNotNull(properties);
      Assert.assertTrue(properties.contains(PropertyHelper.getPropertyName(propertyId)));
    }
  }
}
