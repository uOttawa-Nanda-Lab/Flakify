@Test public void defaultBitronixServerId() throws UnknownHostException {
  this.context=new AnnotationConfigApplicationContext(JtaPropertiesConfiguration.class,BitronixJtaConfiguration.class);
  String serverId=this.context.getBean(bitronix.tm.Configuration.class).getServerId();
  assertThat(serverId,is(equalTo(InetAddress.getLocalHost().getHostAddress())));
}
