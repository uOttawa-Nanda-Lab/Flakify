@Test public void testAppendOk(){
  appender.start();
  ILoggingEvent le=createLoggingEvent();
  appender.append(le);
  MockTopicPublisher tp=(MockTopicPublisher)appender.topicPublisher;
  assertEquals(1,tp.getMessageList().size());
  ObjectMessage message=(ObjectMessage)tp.getMessageList().get(0);
  try {
    Serializable witness=pst.transform(le);
    assertEquals(witness,message.getObject());
  }
 catch (  Exception e) {
    fail();
  }
}
