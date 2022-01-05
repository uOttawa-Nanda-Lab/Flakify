@Test public void embeddedConnectionFactoryByDefault(){
  load(EmptyConfiguration.class);
  assertEquals(1,this.context.getBeansOfType(EmbeddedJMS.class).size());
  org.hornetq.core.config.Configuration configuration=this.context.getBean(org.hornetq.core.config.Configuration.class);
  assertFalse("Persistence disabled by default",configuration.isPersistenceEnabled());
  assertFalse("Security disabled by default",configuration.isSecurityEnabled());
  HornetQConnectionFactory connectionFactory=this.context.getBean(HornetQConnectionFactory.class);
  assertInVmConnectionFactory(connectionFactory);
}
