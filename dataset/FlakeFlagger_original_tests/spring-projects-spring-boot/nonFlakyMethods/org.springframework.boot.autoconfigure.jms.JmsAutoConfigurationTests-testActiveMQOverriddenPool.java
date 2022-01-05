@Test public void testActiveMQOverriddenPool(){
  load(TestConfiguration.class,"spring.activemq.pooled:true");
  JmsTemplate jmsTemplate=this.context.getBean(JmsTemplate.class);
  PooledConnectionFactory pool=this.context.getBean(PooledConnectionFactory.class);
  assertNotNull(jmsTemplate);
  assertNotNull(pool);
  assertEquals(jmsTemplate.getConnectionFactory(),pool);
  ActiveMQConnectionFactory factory=(ActiveMQConnectionFactory)pool.getConnectionFactory();
  assertEquals(ACTIVEMQ_EMBEDDED_URL,factory.getBrokerURL());
}
