@Test public void testMetadataConfiguration() throws Throwable {
  ServiceController<?> controller=serviceContainer.getService(ConnectorServices.IRONJACAMAR_MDR);
  assertNotNull(controller);
  MetadataRepository repository=(MetadataRepository)controller.getValue();
  assertNotNull(repository);
  Set<String> ids=repository.getResourceAdapters();
  assertNotNull(ids);
  for (  String piId : ids) {
    assertNotNull(piId);
    assertNotNull(repository.getResourceAdapter(piId));
  }
}
