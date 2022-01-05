@Test public void sendWithDefaultJMSConnectionFactory() throws Exception {
  String text=UUID.randomUUID().toString();
  producerEJB.sendWithDefaultJMSConnectionFactory(queue,text);
  assertMessageInQueue(text);
}
