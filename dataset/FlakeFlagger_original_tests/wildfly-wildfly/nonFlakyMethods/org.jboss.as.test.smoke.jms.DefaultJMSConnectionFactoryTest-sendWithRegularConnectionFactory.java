@Test public void sendWithRegularConnectionFactory() throws Exception {
  String text=UUID.randomUUID().toString();
  producerEJB.sendWithRegularConnectionFactory(queue,text);
  assertMessageInQueue(text);
}
