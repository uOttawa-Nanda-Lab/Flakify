@Test public void embeddedConnectionFactorEvenIfEmbeddedServiceDisabled(){
  load(EmptyConfiguration.class,"spring.hornetq.mode:embedded","spring.hornetq.embedded.enabled:false");
  assertEquals(0,this.context.getBeansOfType(EmbeddedJMS.class).size());
  HornetQConnectionFactory connectionFactory=this.context.getBean(HornetQConnectionFactory.class);
  assertInVmConnectionFactory(connectionFactory);
}
