@Test public void sendMessageWithClientAcknowledge() throws Exception {
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
    consumer=consumerSession.createConsumer(queue2);
    logger.trace("Start session");
    consumerConnection.start();
    logger.trace("***** Start - sending message to topic");
    senderConnection=factory.createConnection();
    logger.trace("Creating session..");
    senderSession=senderConnection.createSession(false,Session.CLIENT_ACKNOWLEDGE);
    MessageProducer producer=senderSession.createProducer(queue2);
    TextMessage message=senderSession.createTextMessage("Hahaha!");
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
  Message receivedMessage2=null;
  try {
    logger.trace("Receiving");
    receivedMessage=consumer.receive(5000);
    logger.trace("Received a message");
    receivedMessage.acknowledge();
    consumerSession.recover();
    receivedMessage2=consumer.receive(5000);
  }
 catch (  Exception e) {
    e.printStackTrace();
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
    Assert.fail("received null instead of a TextMessage");
  }
  Assert.assertTrue("received a " + receivedMessage.getClass().getName() + " instead of a TextMessage",receivedMessage instanceof TextMessage);
  Assert.assertEquals("Hahaha!",((TextMessage)receivedMessage).getText());
  if (receivedMessage2 != null) {
    Assert.fail("redelivered=" + String.valueOf(receivedMessage2.getJMSRedelivered()) + ", text="+ ((TextMessage)receivedMessage).getText());
  }
  Assert.assertNull("Message should not have been re-delivered",receivedMessage2);
}
