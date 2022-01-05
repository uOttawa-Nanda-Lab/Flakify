@Test public void basePackageClasses() throws Exception {
  this.context=new AnnotationConfigApplicationContext(BasePackageClassesConfig.class);
  assertSetPackagesToScan(getClass().getPackage().getName());
}
