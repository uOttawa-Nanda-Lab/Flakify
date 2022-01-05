@Test public void testActiveMQOverriddenPoolAndRemoteServer(){
  load(TestConfiguration.class,"spring.activemq.pooled:true","spring.activemq.brokerUrl:tcp://remote-host:10000");
  JmsTemplate jmsTemplate=this.context.getBean(JmsTemplate.class);
  PooledConnectionFactory pool=this.context.getBean(PooledConnectionFactory.class);
  assertNotNull(jmsTemplate);
  assertNotNull(pool);
  assertEquals(jmsTemplate.getConnectionFactory(),pool);
  ActiveMQConnectionFactory factory=(ActiveMQConnectionFactory)pool.getConnectionFactory();
  assertEquals("tcp://remote-host:10000",factory.getBrokerURL());
}
