@Test public void availabilityOfTemplateWithCustomPrefix(){
  this.environment.setProperty("spring.velocity.prefix","prefix/");
  assertTrue(this.provider.isTemplateAvailable("prefixed",this.environment,getClass().getClassLoader(),this.resourceLoader));
}
