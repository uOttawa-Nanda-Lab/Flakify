@Test public void testConnectionFactoryBackOff(){
  load(TestConfiguration2.class);
  assertEquals("foobar",this.context.getBean(ActiveMQConnectionFactory.class).getBrokerURL());
}
