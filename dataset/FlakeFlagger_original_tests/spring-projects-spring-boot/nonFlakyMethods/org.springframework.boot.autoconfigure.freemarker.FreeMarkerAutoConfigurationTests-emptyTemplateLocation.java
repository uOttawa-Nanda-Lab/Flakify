@Test public void emptyTemplateLocation(){
  new File("target/test-classes/templates/empty-directory").mkdir();
  registerAndRefreshContext("spring.freemarker.templateLoaderPath:" + "classpath:/templates/empty-directory/");
}
