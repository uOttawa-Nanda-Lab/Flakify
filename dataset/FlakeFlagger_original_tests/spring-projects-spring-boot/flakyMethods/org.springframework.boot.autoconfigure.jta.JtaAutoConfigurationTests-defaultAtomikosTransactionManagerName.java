@Test public void defaultAtomikosTransactionManagerName() throws UnknownHostException {
  this.context=new AnnotationConfigApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"spring.jta.logDir:target/transaction-logs");
  this.context.register(JtaPropertiesConfiguration.class,AtomikosJtaConfiguration.class);
  this.context.refresh();
  File epochFile=new File("target/transaction-logs/" + InetAddress.getLocalHost().getHostAddress() + ".tm0.epoch");
  assertTrue(epochFile.isFile());
}
