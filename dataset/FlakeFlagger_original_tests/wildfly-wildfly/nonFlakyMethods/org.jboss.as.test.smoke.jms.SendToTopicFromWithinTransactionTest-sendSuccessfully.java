@Test public void sendSuccessfully() throws Exception {
  try {
    sender.sendToTopicSuccessfully();
    Thread.sleep(2000);
  }
 catch (  Exception ex) {
    ex.printStackTrace();
  }
  Assert.assertTrue(messageReceived);
}
