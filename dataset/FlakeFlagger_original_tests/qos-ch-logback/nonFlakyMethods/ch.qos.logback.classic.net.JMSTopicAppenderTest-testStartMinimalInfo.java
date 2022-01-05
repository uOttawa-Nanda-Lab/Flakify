@Test public void testStartMinimalInfo(){
  appender.setProviderURL(null);
  appender.start();
  assertTrue(appender.isStarted());
  try {
    assertEquals(appender.topicBindingName,appender.topicPublisher.getTopic().getTopicName());
  }
 catch (  Exception e) {
    fail();
  }
}
