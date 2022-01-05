@Test public void emptyTemplateLocation(){
  new File("target/test-classes/templates/empty-directory").mkdir();
  registerAndRefreshContext("spring.velocity.resourceLoaderPath:" + "classpath:/templates/empty-directory/");
}
