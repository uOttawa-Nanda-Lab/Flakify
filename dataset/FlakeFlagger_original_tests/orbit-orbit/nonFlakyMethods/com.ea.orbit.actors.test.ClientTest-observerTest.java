@Test public void observerTest() throws ExecutionException, InterruptedException {
  OrbitStage stage1=createStage();
  OrbitStage stage2=createStage();
  OrbitStage client1=createClient();
  OrbitStage client2=createClient();
  SomeChatObserver observer1=new SomeChatObserver();
  SomeChatObserver observer2=new SomeChatObserver();
{
    ISomeChatRoom chatRoom=client1.getReference(ISomeChatRoom.class,"chat");
    chatRoom.join(observer1).get();
  }
{
    ISomeChatRoom chatRoom=client2.getReference(ISomeChatRoom.class,"chat");
    final ISomeChatObserver reference=client2.getObserverReference(observer2);
    chatRoom.join(reference).get();
    chatRoom.sendMessage(reference,"bla");
  }
  assertEquals("bla",observer1.messagesReceived.poll(5,TimeUnit.SECONDS).getRight());
  assertEquals("bla",observer2.messagesReceived.poll(5,TimeUnit.SECONDS).getRight());
}
