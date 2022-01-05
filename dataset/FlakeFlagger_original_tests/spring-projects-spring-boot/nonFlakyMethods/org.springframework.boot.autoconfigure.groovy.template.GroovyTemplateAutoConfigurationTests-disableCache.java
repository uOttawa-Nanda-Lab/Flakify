@Test public void disableCache(){
  registerAndRefreshContext("spring.groovy.template.cache:false");
  assertThat(this.context.getBean(GroovyTemplateViewResolver.class).getCacheLimit(),equalTo(0));
}
