@Test public void availabilityOfTemplateWithCustomPrefix(){
  this.environment.setProperty("spring.thymeleaf.prefix","classpath:/custom-templates/");
  assertTrue(this.provider.isTemplateAvailable("custom",this.environment,getClass().getClassLoader(),this.resourceLoader));
}
