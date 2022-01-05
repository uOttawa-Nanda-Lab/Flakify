@Test public void testEnableJmsCreateDefaultContainerFactory(){
  load(EnableJmsConfiguration.class);
  JmsListenerContainerFactory<?> jmsListenerContainerFactory=this.context.getBean("jmsListenerContainerFactory",JmsListenerContainerFactory.class);
  assertEquals(DefaultJmsListenerContainerFactory.class,jmsListenerContainerFactory.getClass());
}
