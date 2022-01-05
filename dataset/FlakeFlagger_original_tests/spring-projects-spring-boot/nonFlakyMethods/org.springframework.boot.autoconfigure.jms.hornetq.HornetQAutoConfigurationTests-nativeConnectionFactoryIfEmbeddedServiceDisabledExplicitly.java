@Test public void nativeConnectionFactoryIfEmbeddedServiceDisabledExplicitly(){
  load(EmptyConfiguration.class,"spring.hornetq.embedded.enabled:false");
  assertEquals(0,this.context.getBeansOfType(EmbeddedJMS.class).size());
  HornetQConnectionFactory connectionFactory=this.context.getBean(HornetQConnectionFactory.class);
  assertNettyConnectionFactory(connectionFactory,"localhost",5445);
}
