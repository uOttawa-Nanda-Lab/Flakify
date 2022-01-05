@Test public void testPubSubDomainOverride(){
  load(TestConfiguration.class,"spring.jms.pubSubDomain:false");
  JmsTemplate jmsTemplate=this.context.getBean(JmsTemplate.class);
  ActiveMQConnectionFactory connectionFactory=this.context.getBean(ActiveMQConnectionFactory.class);
  assertNotNull(jmsTemplate);
  assertFalse(jmsTemplate.isPubSubDomain());
  assertNotNull(connectionFactory);
  assertEquals(jmsTemplate.getConnectionFactory(),connectionFactory);
}
