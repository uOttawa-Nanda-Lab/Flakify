@Test public void testStartUserPass(){
  appender.setUserName("test");
  appender.setPassword("test");
  appender.start();
  assertTrue(appender.isStarted());
  try {
    assertEquals(appender.topicBindingName,appender.topicPublisher.getTopic().getTopicName());
  }
 catch (  Exception e) {
    fail();
  }
}
