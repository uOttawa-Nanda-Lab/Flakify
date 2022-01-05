/** 
 * Send a message on the source queue Consumes it on the target queue The test will pass since a JMS Bridge has been created to bridge the source destination to the target destination.
 */
@Test public void sendAndReceiveMessage() throws Exception {
  Connection connection=null;
  Session session=null;
  Message receivedMessage=null;
  try {
    connection=factory.createConnection();
    session=connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
    MessageProducer producer=session.createProducer(sourceQueue);
    String text=MESSAGE_TEXT + " " + UUID.randomUUID().toString();
    Message message=session.createTextMessage(text);
    message.setJMSDeliveryMode(DeliveryMode.NON_PERSISTENT);
    producer.send(message);
    connection.start();
    connection.close();
    connection=factory.createConnection();
    session=connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
    MessageConsumer consumer=session.createConsumer(targetQueue);
    connection.start();
    receivedMessage=consumer.receive(5000);
    assertNotNull("did not receive expected message",receivedMessage);
    assertTrue(receivedMessage instanceof TextMessage);
    assertEquals(text,((TextMessage)receivedMessage).getText());
    assertNotNull("did not get header set by the JMS bridge",receivedMessage.getStringProperty(ActiveMQJMSConstants.AMQ_MESSAGING_BRIDGE_MESSAGE_ID_LIST));
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
 finally {
    if (session != null) {
      session.close();
    }
    if (connection != null) {
      connection.close();
    }
  }
}
