public void testAppendOk(){
  appender.start();
  ILoggingEvent le=createLoggingEvent();
  appender.append(le);
  MockQueueSender qs=(MockQueueSender)appender.queueSender;
  assertEquals(1,qs.getMessageList().size());
  ObjectMessage message=(ObjectMessage)qs.getMessageList().get(0);
  try {
    Serializable witness=pst.transform(le);
    assertEquals(witness,message.getObject());
  }
 catch (  Exception e) {
    fail();
  }
}
