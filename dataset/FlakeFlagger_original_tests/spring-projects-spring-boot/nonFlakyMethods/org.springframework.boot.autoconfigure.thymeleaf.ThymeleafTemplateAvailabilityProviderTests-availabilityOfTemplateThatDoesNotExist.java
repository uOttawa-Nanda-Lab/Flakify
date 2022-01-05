@Test public void availabilityOfTemplateThatDoesNotExist(){
  assertFalse(this.provider.isTemplateAvailable("whatever",this.environment,getClass().getClassLoader(),this.resourceLoader));
}
