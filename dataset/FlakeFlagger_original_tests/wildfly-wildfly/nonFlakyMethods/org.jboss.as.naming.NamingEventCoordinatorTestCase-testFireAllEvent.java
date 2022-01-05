@Test public void testFireAllEvent() throws Exception {
  final NamingEventCoordinator coordinator=new NamingEventCoordinator();
  final CollectingListener objectListener=new CollectingListener(1);
  coordinator.addListener("test/path",EventContext.OBJECT_SCOPE,objectListener);
  final CollectingListener subtreeListener=new CollectingListener(1);
  coordinator.addListener("test",EventContext.SUBTREE_SCOPE,subtreeListener);
  final CollectingListener oneLevelListener=new CollectingListener(1);
  coordinator.addListener("test",EventContext.ONELEVEL_SCOPE,oneLevelListener);
  coordinator.fireEvent(context,new CompositeName("test/path"),null,null,NamingEvent.OBJECT_ADDED,"bind",EventContext.OBJECT_SCOPE,EventContext.ONELEVEL_SCOPE,EventContext.SUBTREE_SCOPE);
  objectListener.latch.await(1,TimeUnit.SECONDS);
  oneLevelListener.latch.await(1,TimeUnit.SECONDS);
  subtreeListener.latch.await(1,TimeUnit.SECONDS);
  assertEquals(1,objectListener.capturedEvents.size());
  assertEquals(1,subtreeListener.capturedEvents.size());
  assertEquals(1,oneLevelListener.capturedEvents.size());
}
