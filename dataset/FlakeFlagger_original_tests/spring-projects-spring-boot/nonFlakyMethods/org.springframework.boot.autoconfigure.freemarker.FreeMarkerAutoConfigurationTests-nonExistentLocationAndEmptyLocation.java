@Test public void nonExistentLocationAndEmptyLocation(){
  new File("target/test-classes/templates/empty-directory").mkdir();
  registerAndRefreshContext("spring.freemarker.templateLoaderPath:" + "classpath:/does-not-exist/,classpath:/templates/empty-directory/");
}
