@Test public void basicObserverTest() throws ExecutionException, InterruptedException {
  OrbitStage stage1=createStage();
  ISomeChatRoom chatRoom=stage1.getReference(ISomeChatRoom.class,"1");
  SomeChatObserver observer=new SomeChatObserver();
  final ISomeChatObserver observerReference=stage1.getObserverReference(ISomeChatObserver.class,observer);
  assertNotNull(observerReference);
  chatRoom.join(observerReference).get();
  chatRoom.sendMessage(observerReference,"bla").get();
  Pair<ISomeChatObserver,String> m=observer.messagesReceived.poll(5,TimeUnit.SECONDS);
  assertNotNull(m);
  assertEquals("bla",m.getRight());
}
