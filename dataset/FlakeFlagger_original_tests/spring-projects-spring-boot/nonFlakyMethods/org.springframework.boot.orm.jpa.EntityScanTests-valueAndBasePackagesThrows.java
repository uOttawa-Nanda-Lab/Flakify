@Test public void valueAndBasePackagesThrows() throws Exception {
  this.thrown.expect(IllegalStateException.class);
  this.thrown.expectMessage("@EntityScan basePackages and value " + "attributes are mutually exclusive");
  this.context=new AnnotationConfigApplicationContext(ValueAndBasePackages.class);
}
