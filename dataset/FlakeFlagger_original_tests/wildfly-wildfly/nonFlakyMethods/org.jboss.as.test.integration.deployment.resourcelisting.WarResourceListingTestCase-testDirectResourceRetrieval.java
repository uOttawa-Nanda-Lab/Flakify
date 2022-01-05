@Test() public void testDirectResourceRetrieval(){
  log.trace("Test accessing resources using getResource method");
  ModuleClassLoader classLoader=(ModuleClassLoader)getClass().getClassLoader();
  URL manifestResource=classLoader.getResource("META-INF/example.txt");
  assertNotNull("Resource in META-INF should be accessible",manifestResource);
  URL nestedManifestResource=classLoader.getResource("META-INF/properties/nested.properties");
  assertNotNull("Nested resource should be also accessible",nestedManifestResource);
  URL nonManifestResource=classLoader.getResource("example2.txt");
  assertNull("Resource in the root of WAR shouldn't be accessible",nonManifestResource);
}
