@Test public void availabilityOfTemplateWithCustomSuffix(){
  this.environment.setProperty("spring.freemarker.suffix",".freemarker");
  assertTrue(this.provider.isTemplateAvailable("suffixed",this.environment,getClass().getClassLoader(),this.resourceLoader));
}
