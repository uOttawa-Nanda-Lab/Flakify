@Test public void nativeConnectionFactory(){
  load(EmptyConfiguration.class,"spring.hornetq.mode:native");
  JmsTemplate jmsTemplate=this.context.getBean(JmsTemplate.class);
  HornetQConnectionFactory connectionFactory=this.context.getBean(HornetQConnectionFactory.class);
  assertEquals(jmsTemplate.getConnectionFactory(),connectionFactory);
  assertNettyConnectionFactory(connectionFactory,"localhost",5445);
}
