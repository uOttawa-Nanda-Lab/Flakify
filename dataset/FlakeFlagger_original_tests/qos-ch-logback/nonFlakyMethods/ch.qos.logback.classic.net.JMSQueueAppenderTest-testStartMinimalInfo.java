public void testStartMinimalInfo(){
  appender.setProviderURL(null);
  appender.start();
  assertTrue(appender.isStarted());
  try {
    assertEquals(appender.queueBindingName,appender.queueSender.getQueue().getQueueName());
  }
 catch (  Exception e) {
    fail();
  }
}
