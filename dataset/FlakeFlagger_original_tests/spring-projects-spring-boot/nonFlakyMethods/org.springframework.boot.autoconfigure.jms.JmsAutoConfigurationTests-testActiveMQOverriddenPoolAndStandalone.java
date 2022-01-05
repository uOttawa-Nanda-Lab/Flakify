@Test public void testActiveMQOverriddenPoolAndStandalone(){
  load(TestConfiguration.class,"spring.activemq.pooled:true","spring.activemq.inMemory:false");
  JmsTemplate jmsTemplate=this.context.getBean(JmsTemplate.class);
  PooledConnectionFactory pool=this.context.getBean(PooledConnectionFactory.class);
  assertNotNull(jmsTemplate);
  assertNotNull(pool);
  assertEquals(jmsTemplate.getConnectionFactory(),pool);
  ActiveMQConnectionFactory factory=(ActiveMQConnectionFactory)pool.getConnectionFactory();
  assertEquals(ACTIVEMQ_NETWORK_URL,factory.getBrokerURL());
}
