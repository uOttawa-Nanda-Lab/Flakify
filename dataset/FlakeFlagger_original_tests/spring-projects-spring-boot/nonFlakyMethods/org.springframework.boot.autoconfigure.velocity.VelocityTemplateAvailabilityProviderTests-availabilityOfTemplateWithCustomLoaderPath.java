@Test public void availabilityOfTemplateWithCustomLoaderPath(){
  this.environment.setProperty("spring.velocity.resourceLoaderPath","classpath:/custom-templates/");
  assertTrue(this.provider.isTemplateAvailable("custom",this.environment,getClass().getClassLoader(),this.resourceLoader));
}
