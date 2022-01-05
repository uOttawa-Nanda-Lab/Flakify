@Test public void customBitronixServerId() throws UnknownHostException {
  this.context=new AnnotationConfigApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"spring.jta.transactionManagerId:custom");
  this.context.register(JtaPropertiesConfiguration.class,BitronixJtaConfiguration.class);
  this.context.refresh();
  String serverId=this.context.getBean(bitronix.tm.Configuration.class).getServerId();
  assertThat(serverId,is(equalTo("custom")));
}
