@Test public void testMessaging() throws Exception {
  final CountDownLatch latch=new CountDownLatch(1);
  final AtomicReference<ClientMessage> message=new AtomicReference<ClientMessage>();
  ClientConsumer consumer=session.createConsumer(QUEUE_EXAMPLE_QUEUE);
  consumer.setMessageHandler(new MessageHandler(){
    @Override public void onMessage(    ClientMessage m){
      try {
        m.acknowledge();
        message.set(m);
        latch.countDown();
      }
 catch (      ActiveMQException e) {
        e.printStackTrace();
      }
    }
  }
);
  String text=UUID.randomUUID().toString();
  sendMessage(text);
  assertTrue(latch.await(1,SECONDS));
  assertEquals(text,message.get().getStringProperty(BODY));
}
