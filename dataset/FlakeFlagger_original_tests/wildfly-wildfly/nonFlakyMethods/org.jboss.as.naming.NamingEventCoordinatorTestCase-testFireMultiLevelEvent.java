@Test public void testFireMultiLevelEvent() throws Exception {
  final NamingEventCoordinator coordinator=new NamingEventCoordinator();
  final CollectingListener subtreeListener=new CollectingListener(1);
  coordinator.addListener("foo",EventContext.SUBTREE_SCOPE,subtreeListener);
  final CollectingListener subtreeListenerTwo=new CollectingListener(1);
  coordinator.addListener("foo/bar",EventContext.SUBTREE_SCOPE,subtreeListenerTwo);
  final CollectingListener subtreeListenerThree=new CollectingListener(1);
  coordinator.addListener("foo/bar/baz",EventContext.SUBTREE_SCOPE,subtreeListenerThree);
  coordinator.fireEvent(context,new CompositeName("foo/bar/baz/boo"),null,null,NamingEvent.OBJECT_ADDED,"bind",EventContext.OBJECT_SCOPE,EventContext.ONELEVEL_SCOPE,EventContext.SUBTREE_SCOPE);
  subtreeListener.latch.await(1,TimeUnit.SECONDS);
  subtreeListenerTwo.latch.await(1,TimeUnit.SECONDS);
  subtreeListenerThree.latch.await(1,TimeUnit.SECONDS);
  assertEquals(1,subtreeListener.capturedEvents.size());
  assertEquals(1,subtreeListenerTwo.capturedEvents.size());
  assertEquals(1,subtreeListenerThree.capturedEvents.size());
}
