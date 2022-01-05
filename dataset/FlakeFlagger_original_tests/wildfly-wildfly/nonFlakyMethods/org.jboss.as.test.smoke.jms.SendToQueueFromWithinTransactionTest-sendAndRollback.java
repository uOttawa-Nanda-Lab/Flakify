@Test public void sendAndRollback(){
  try {
    sender2.sendToQueueAndRollback();
    Thread.sleep(2000);
  }
 catch (  Exception ex) {
    ex.printStackTrace();
  }
  Assert.assertFalse(messageReceived);
}
