@Test public void basePackages() throws Exception {
  this.context=new AnnotationConfigApplicationContext(BasePackagesConfig.class);
  assertSetPackagesToScan("com.mycorp.entity2");
}
