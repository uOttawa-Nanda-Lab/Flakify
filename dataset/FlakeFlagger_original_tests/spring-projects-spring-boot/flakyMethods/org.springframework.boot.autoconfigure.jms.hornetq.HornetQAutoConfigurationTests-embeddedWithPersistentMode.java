@Test public void embeddedWithPersistentMode() throws IOException, JMSException {
  File dataFolder=this.folder.newFolder();
  load(EmptyConfiguration.class,"spring.hornetq.embedded.queues=TestQueue","spring.hornetq.embedded.persistent:true","spring.hornetq.embedded.dataDirectory:" + dataFolder.getAbsolutePath());
  final String msgId=UUID.randomUUID().toString();
  JmsTemplate jmsTemplate=this.context.getBean(JmsTemplate.class);
  jmsTemplate.send("TestQueue",new MessageCreator(){
    @Override public Message createMessage(    Session session) throws JMSException {
      return session.createTextMessage(msgId);
    }
  }
);
  this.context.close();
  load(EmptyConfiguration.class,"spring.hornetq.embedded.queues=TestQueue","spring.hornetq.embedded.persistent:true","spring.hornetq.embedded.dataDirectory:" + dataFolder.getAbsolutePath());
  JmsTemplate jmsTemplate2=this.context.getBean(JmsTemplate.class);
  jmsTemplate2.setReceiveTimeout(1000L);
  Message message=jmsTemplate2.receive("TestQueue");
  assertNotNull("No message on persistent queue",message);
  assertEquals("Invalid message received on queue",msgId,((TextMessage)message).getText());
}
