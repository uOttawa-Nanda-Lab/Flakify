@Test public void sendMessageWithMissingClientAcknowledge() throws Exception {
  Connection senderConnection=null;
  Connection consumerConnection=null;
  Session senderSession=null;
  Session consumerSession=null;
  MessageConsumer consumer=null;
  try {
    logger.trace("******* Creating connection for consumer");
    consumerConnection=factory.createConnection();
    logger.trace("Creating session for consumer");
    consumerSession=consumerConnection.createSession(false,Session.CLIENT_ACKNOWLEDGE);
    logger.trace("Creating consumer");
    consumer=consumerSession.createConsumer(queue3);
    logger.trace("Start session");
    consumerConnection.start();
    logger.trace("***** Start - sending message to topic");
    senderConnection=factory.createConnection();
    logger.trace("Creating session..");
    senderSession=senderConnection.createSession(false,Session.CLIENT_ACKNOWLEDGE);
    MessageProducer producer=senderSession.createProducer(queue3);
    TextMessage message=senderSession.createTextMessage("Hello world!");
    logger.trace("Sending..");
    producer.send(message);
    logger.trace("Message sent");
    senderConnection.start();
  }
 catch (  Exception ex) {
    ex.printStackTrace();
  }
 finally {
    logger.trace("Closing connections and sessions");
    if (senderSession != null) {
      senderSession.close();
    }
    if (senderConnection != null) {
      senderConnection.close();
    }
  }
  Message receivedMessage=null;
  try {
    logger.trace("Receiving");
    receivedMessage=consumer.receive(5000);
    try {
      Thread.sleep(1000);
    }
 catch (    InterruptedException ex) {
    }
    consumerSession.recover();
    receivedMessage=consumer.receive(5000);
    receivedMessage.acknowledge();
  }
 catch (  Exception e) {
    e.printStackTrace();
    Assert.fail(e.getMessage());
  }
 finally {
    if (consumerSession != null) {
      consumerSession.close();
    }
    if (consumerConnection != null) {
      consumerConnection.close();
    }
  }
  if (receivedMessage == null) {
    Assert.fail("Message should have been re-delivered, but subsequent attempt to receive it returned null");
  }
  Assert.assertTrue("received a " + receivedMessage.getClass().getName() + " instead of a TextMessage",receivedMessage instanceof TextMessage);
  Assert.assertEquals(((TextMessage)receivedMessage).getText(),"Hello world!");
  Assert.assertTrue("Redelivered header should have been set to true",receivedMessage.getJMSRedelivered());
}
