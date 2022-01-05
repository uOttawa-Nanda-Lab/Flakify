@Test public void testActiveMQOverriddenStandalone(){
  load(TestConfiguration.class,"spring.activemq.inMemory:false");
  JmsTemplate jmsTemplate=this.context.getBean(JmsTemplate.class);
  ActiveMQConnectionFactory connectionFactory=this.context.getBean(ActiveMQConnectionFactory.class);
  assertNotNull(jmsTemplate);
  assertNotNull(connectionFactory);
  assertEquals(jmsTemplate.getConnectionFactory(),connectionFactory);
  assertEquals(ACTIVEMQ_NETWORK_URL,((ActiveMQConnectionFactory)jmsTemplate.getConnectionFactory()).getBrokerURL());
}
