@Test public void customAtomikosTransactionManagerName() throws BeansException, Exception {
  this.context=new AnnotationConfigApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"spring.jta.transactionManagerId:custom","spring.jta.logDir:target/transaction-logs");
  this.context.register(JtaPropertiesConfiguration.class,AtomikosJtaConfiguration.class);
  this.context.refresh();
  File epochFile=new File("target/transaction-logs/custom0.epoch");
  assertTrue(epochFile.isFile());
}
