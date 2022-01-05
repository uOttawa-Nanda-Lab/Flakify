@Test public void testValue() throws Exception {
  this.context=new AnnotationConfigApplicationContext(ValueConfig.class);
  assertSetPackagesToScan("com.mycorp.entity");
}
