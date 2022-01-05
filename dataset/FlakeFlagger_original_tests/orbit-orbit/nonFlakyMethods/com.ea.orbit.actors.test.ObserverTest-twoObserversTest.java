@Test public void twoObserversTest() throws ExecutionException, InterruptedException {
  OrbitStage stage1=createStage();
  OrbitStage stage2=createStage();
  SomeChatObserver observer1=new SomeChatObserver();
  final ISomeChatObserver observerReference1=stage1.getObserverReference(ISomeChatObserver.class,observer1);
  SomeChatObserver observer2=new SomeChatObserver();
  final ISomeChatObserver observerReference2=stage2.getObserverReference(ISomeChatObserver.class,observer2);
  ISomeChatRoom chatRoom=stage1.getReference(ISomeChatRoom.class,"1");
  chatRoom.join(observerReference1).get();
  chatRoom.join(observerReference2).get();
  chatRoom.sendMessage(observerReference1,"bla").get();
  Pair<ISomeChatObserver,String> m=observer1.messagesReceived.poll(5,TimeUnit.SECONDS);
  assertNotNull(m);
  assertEquals("bla",m.getRight());
  Pair<ISomeChatObserver,String> m2=observer2.messagesReceived.poll(5,TimeUnit.SECONDS);
  assertNotNull(m2);
  assertEquals("bla",m2.getRight());
}
