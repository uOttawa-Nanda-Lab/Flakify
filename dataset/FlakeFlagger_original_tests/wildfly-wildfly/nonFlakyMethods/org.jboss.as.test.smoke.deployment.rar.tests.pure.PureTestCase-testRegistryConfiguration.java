/** 
 * Test configuration
 * @throws Throwable Thrown if case of an error
 */
@Test public void testRegistryConfiguration() throws Throwable {
  ServiceController<?> controller=serviceContainer.getService(ConnectorServices.RA_REPOSITORY_SERVICE);
  assertNotNull(controller);
  ResourceAdapterRepository repository=(ResourceAdapterRepository)controller.getValue();
  assertNotNull(repository);
  Set<String> ids=repository.getResourceAdapters();
  assertNotNull(ids);
  int pureInflowListener=0;
  for (  String id : ids) {
    if (id.indexOf("PureInflow") != -1) {
      pureInflowListener++;
    }
  }
  assertEquals(1,pureInflowListener);
  for (  String piId : ids) {
    assertNotNull(piId);
    assertNotNull(repository.getResourceAdapter(piId));
  }
}
