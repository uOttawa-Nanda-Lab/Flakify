@Test public void valueAndBasePackageClassesMerges() throws Exception {
  this.context=new AnnotationConfigApplicationContext(ValueAndBasePackageClasses.class);
  assertSetPackagesToScan("com.mycorp.entity",getClass().getPackage().getName());
}
