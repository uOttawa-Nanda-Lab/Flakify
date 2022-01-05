public void testStartUserPass(){
  appender.setUserName("test");
  appender.setPassword("test");
  appender.start();
  assertTrue(appender.isStarted());
  try {
    assertEquals(appender.queueBindingName,appender.queueSender.getQueue().getQueueName());
  }
 catch (  Exception e) {
    fail();
  }
}
