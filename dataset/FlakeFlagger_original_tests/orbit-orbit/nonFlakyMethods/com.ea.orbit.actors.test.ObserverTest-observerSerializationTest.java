@Test public void observerSerializationTest() throws ExecutionException, InterruptedException {
  OrbitStage stage1=createStage();
  ISomeChatRoom chatRoom=stage1.getReference(ISomeChatRoom.class,"1");
  SomeChatObserver observer=new SomeChatObserver();
  chatRoom.join(observer).get();
  chatRoom.sendMessage(observer,"bla").get();
  Pair<ISomeChatObserver,String> m=observer.messagesReceived.poll(5,TimeUnit.SECONDS);
  assertNotNull(m);
  assertEquals("bla",m.getRight());
}
