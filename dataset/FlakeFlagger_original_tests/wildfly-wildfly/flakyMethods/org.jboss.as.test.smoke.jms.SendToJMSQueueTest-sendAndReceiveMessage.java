@Test public void sendAndReceiveMessage() throws Exception {
  Connection connection=null;
  Session session=null;
  Message receivedMessage=null;
  try {
    connection=factory.createConnection();
    session=connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
    MessageProducer producer=session.createProducer(queue);
    Message message=session.createTextMessage(MESSAGE_TEXT);
    producer.send(message);
    connection.start();
    session.close();
    connection.close();
    try {
      Thread.sleep(2000);
    }
 catch (    InterruptedException ex) {
    }
    connection=factory.createConnection();
    session=connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
    MessageConsumer consumer=session.createConsumer(queue);
    connection.start();
    receivedMessage=consumer.receive(5000);
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
  Assert.assertTrue(receivedMessage instanceof TextMessage);
  Assert.assertTrue(((TextMessage)receivedMessage).getText().equals(MESSAGE_TEXT));
}
