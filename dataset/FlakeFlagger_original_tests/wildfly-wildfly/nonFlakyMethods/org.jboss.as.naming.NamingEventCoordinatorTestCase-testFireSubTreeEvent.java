@Test public void testFireSubTreeEvent() throws Exception {
  final NamingEventCoordinator coordinator=new NamingEventCoordinator();
  final CollectingListener objectListener=new CollectingListener(0);
  coordinator.addListener("test/path",EventContext.OBJECT_SCOPE,objectListener);
  final CollectingListener subtreeListener=new CollectingListener(1);
  coordinator.addListener("test",EventContext.SUBTREE_SCOPE,subtreeListener);
  final CollectingListener oneLevelListener=new CollectingListener(0);
  coordinator.addListener("test",EventContext.ONELEVEL_SCOPE,oneLevelListener);
  coordinator.fireEvent(context,new CompositeName("test/path"),null,null,NamingEvent.OBJECT_ADDED,"bind",EventContext.SUBTREE_SCOPE);
  subtreeListener.latch.await(1,TimeUnit.SECONDS);
  assertTrue(objectListener.capturedEvents.isEmpty());
  assertTrue(oneLevelListener.capturedEvents.isEmpty());
  assertEquals(1,subtreeListener.capturedEvents.size());
}
