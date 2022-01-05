@Test public void availabilityOfTemplateWithCustomPrefix(){
  this.environment.setProperty("spring.freemarker.prefix","prefix/");
  assertTrue(this.provider.isTemplateAvailable("prefixed",this.environment,getClass().getClassLoader(),this.resourceLoader));
}
