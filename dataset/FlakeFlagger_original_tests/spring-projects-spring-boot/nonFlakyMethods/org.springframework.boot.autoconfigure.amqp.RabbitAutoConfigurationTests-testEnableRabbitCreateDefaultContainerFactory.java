@Test public void testEnableRabbitCreateDefaultContainerFactory(){
  load(EnableRabbitConfiguration.class);
  RabbitListenerContainerFactory<?> rabbitListenerContainerFactory=this.context.getBean("rabbitListenerContainerFactory",RabbitListenerContainerFactory.class);
  assertEquals(SimpleRabbitListenerContainerFactory.class,rabbitListenerContainerFactory.getClass());
}
