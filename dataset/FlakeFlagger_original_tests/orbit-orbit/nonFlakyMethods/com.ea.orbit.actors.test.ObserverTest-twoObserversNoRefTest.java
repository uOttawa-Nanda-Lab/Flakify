@Test public void twoObserversNoRefTest() throws ExecutionException, InterruptedException {
  OrbitStage stage1=createStage();
  OrbitStage stage2=createStage();
  SomeChatObserver observer1=new SomeChatObserver();
  SomeChatObserver observer2=new SomeChatObserver();
  ISomeChatRoom chatRoom=stage1.getReference(ISomeChatRoom.class,"1");
  ISomeChatRoom chatRoom_r2=stage2.getReference(ISomeChatRoom.class,"1");
  chatRoom.join(observer1).get();
  chatRoom_r2.join(observer2).get();
  chatRoom.sendMessage(observer1,"bla").get();
  Pair<ISomeChatObserver,String> m=observer1.messagesReceived.poll(5,TimeUnit.SECONDS);
  assertNotNull(m);
  assertEquals("bla",m.getRight());
  Pair<ISomeChatObserver,String> m2=observer2.messagesReceived.poll(5,TimeUnit.SECONDS);
  assertNotNull(m2);
  assertEquals("bla",m2.getRight());
}
