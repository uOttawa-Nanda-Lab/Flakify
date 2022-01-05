@Test public void testActiveMQOverriddenRemoteHost(){
  load(TestConfiguration.class,"spring.activemq.brokerUrl:tcp://remote-host:10000");
  JmsTemplate jmsTemplate=this.context.getBean(JmsTemplate.class);
  ActiveMQConnectionFactory connectionFactory=this.context.getBean(ActiveMQConnectionFactory.class);
  assertNotNull(jmsTemplate);
  assertNotNull(connectionFactory);
  assertEquals(jmsTemplate.getConnectionFactory(),connectionFactory);
  assertEquals("tcp://remote-host:10000",((ActiveMQConnectionFactory)jmsTemplate.getConnectionFactory()).getBrokerURL());
}
