@Test public void timerTest() throws ExecutionException, InterruptedException {
  OrbitStage stage1=createStage();
  OrbitStage frontend=createClient();
  ISomeChatRoom chatRoom=frontend.getReference(ISomeChatRoom.class,"1");
  SomeChatObserver observer=new SomeChatObserver();
  chatRoom.join(observer).get();
  chatRoom.startCountdown(5,"counting").get();
  assertNotNull("counting 5",observer.messagesReceived.poll(20,TimeUnit.SECONDS).getRight());
  long start=System.currentTimeMillis();
  assertNotNull("counting 4",observer.messagesReceived.poll(2000,TimeUnit.SECONDS).getRight());
  assertNotNull("counting 3",observer.messagesReceived.poll(5,TimeUnit.SECONDS).getRight());
  assertNotNull("counting 2",observer.messagesReceived.poll(5,TimeUnit.SECONDS).getRight());
  assertNotNull("counting 1",observer.messagesReceived.poll(5,TimeUnit.SECONDS).getRight());
  assertTrue(System.currentTimeMillis() - start > 10);
  assertNull(observer.messagesReceived.poll(10,TimeUnit.MILLISECONDS));
}
