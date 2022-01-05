@Test public void testDefaultJmsConfiguration(){
  load(TestConfiguration.class);
  ActiveMQConnectionFactory connectionFactory=this.context.getBean(ActiveMQConnectionFactory.class);
  JmsTemplate jmsTemplate=this.context.getBean(JmsTemplate.class);
  JmsMessagingTemplate messagingTemplate=this.context.getBean(JmsMessagingTemplate.class);
  assertEquals(jmsTemplate.getConnectionFactory(),connectionFactory);
  assertEquals(jmsTemplate,messagingTemplate.getJmsTemplate());
  assertEquals(ACTIVEMQ_EMBEDDED_URL,((ActiveMQConnectionFactory)jmsTemplate.getConnectionFactory()).getBrokerURL());
  assertTrue("listener container factory should be created by default",this.context.containsBean("jmsListenerContainerFactory"));
}
