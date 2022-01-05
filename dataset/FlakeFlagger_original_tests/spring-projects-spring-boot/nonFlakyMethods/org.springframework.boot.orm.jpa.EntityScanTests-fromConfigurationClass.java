@Test public void fromConfigurationClass() throws Exception {
  this.context=new AnnotationConfigApplicationContext(FromConfigConfig.class);
  assertSetPackagesToScan(getClass().getPackage().getName());
}
