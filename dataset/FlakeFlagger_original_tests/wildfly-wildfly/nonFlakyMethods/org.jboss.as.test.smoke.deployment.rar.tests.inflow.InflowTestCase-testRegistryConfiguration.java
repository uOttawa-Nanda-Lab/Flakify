/** 
 * Test configuration
 * @throws Throwable Thrown if case of an error
 */
@Test public void testRegistryConfiguration() throws Throwable {
  ServiceController<?> controller=serviceContainer.getService(ConnectorServices.RA_REPOSITORY_SERVICE);
  assertNotNull(controller);
  ResourceAdapterRepository repository=(ResourceAdapterRepository)controller.getValue();
  assertNotNull(repository);
  Set<String> ids=repository.getResourceAdapters(javax.jms.MessageListener.class);
  assertNotNull(ids);
  int pureInflowListener=0;
  for (  String id : ids) {
    if (id.indexOf("PureInflow") != -1) {
      pureInflowListener++;
    }
  }
  assertEquals(1,pureInflowListener);
  String piId=ids.iterator().next();
  assertNotNull(piId);
  Endpoint endpoint=repository.getEndpoint(piId);
  assertNotNull(endpoint);
  List<MessageListener> listeners=repository.getMessageListeners(piId);
  assertNotNull(listeners);
  assertEquals(1,listeners.size());
  MessageListener listener=listeners.get(0);
  ActivationSpec as=listener.getActivation().createInstance();
  assertNotNull(as);
  assertNotNull(as.getResourceAdapter());
}
