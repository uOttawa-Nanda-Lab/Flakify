@Test public void testExposesXaAndNonXa() throws Exception {
  ApplicationContext context=SpringApplication.run(SampleBitronixApplication.class);
  Object jmsConnectionFactory=context.getBean("jmsConnectionFactory");
  Object xaJmsConnectionFactory=context.getBean("xaJmsConnectionFactory");
  Object nonXaJmsConnectionFactory=context.getBean("nonXaJmsConnectionFactory");
  assertThat(jmsConnectionFactory,sameInstance(xaJmsConnectionFactory));
  assertThat(jmsConnectionFactory,instanceOf(PoolingConnectionFactory.class));
  assertThat(nonXaJmsConnectionFactory,not(instanceOf(PoolingConnectionFactory.class)));
}
