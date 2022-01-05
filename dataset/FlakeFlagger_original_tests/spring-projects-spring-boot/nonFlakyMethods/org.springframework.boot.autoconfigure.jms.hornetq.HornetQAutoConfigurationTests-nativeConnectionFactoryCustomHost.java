@Test public void nativeConnectionFactoryCustomHost(){
  load(EmptyConfiguration.class,"spring.hornetq.mode:native","spring.hornetq.host:192.168.1.144","spring.hornetq.port:9876");
  HornetQConnectionFactory connectionFactory=this.context.getBean(HornetQConnectionFactory.class);
  assertNettyConnectionFactory(connectionFactory,"192.168.1.144",9876);
}
