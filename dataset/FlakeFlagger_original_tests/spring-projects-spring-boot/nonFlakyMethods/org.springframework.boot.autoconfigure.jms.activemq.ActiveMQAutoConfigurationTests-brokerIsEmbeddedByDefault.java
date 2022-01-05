@Test public void brokerIsEmbeddedByDefault(){
  load(EmptyConfiguration.class);
  ConnectionFactory connectionFactory=this.context.getBean(ConnectionFactory.class);
  assertThat(connectionFactory,instanceOf(ActiveMQConnectionFactory.class));
  String brokerUrl=((ActiveMQConnectionFactory)connectionFactory).getBrokerURL();
  assertEquals("vm://localhost?broker.persistent=false",brokerUrl);
}
