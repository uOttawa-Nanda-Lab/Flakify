@Test public void availabilityOfTemplateInDefaultLocation(){
  assertTrue(this.provider.isTemplateAvailable("home",this.environment,getClass().getClassLoader(),this.resourceLoader));
}
