@Test public void basePackageAndBasePackageClassesMerges() throws Exception {
  this.context=new AnnotationConfigApplicationContext(BasePackagesAndBasePackageClasses.class);
  assertSetPackagesToScan("com.mycorp.entity2",getClass().getPackage().getName());
}
