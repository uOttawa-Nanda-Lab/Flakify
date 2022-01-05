@Test public void emptyTemplateLocation(){
  new File("target/test-classes/templates/empty-directory").mkdir();
  registerAndRefreshContext("spring.groovy.template.prefix:" + "classpath:/templates/empty-directory/");
}
