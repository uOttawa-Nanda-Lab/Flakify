@Test public void availabilityOfTemplateWithCustomSuffix(){
  this.environment.setProperty("spring.thymeleaf.suffix",".thymeleaf");
  assertTrue(this.provider.isTemplateAvailable("suffixed",this.environment,getClass().getClassLoader(),this.resourceLoader));
}
