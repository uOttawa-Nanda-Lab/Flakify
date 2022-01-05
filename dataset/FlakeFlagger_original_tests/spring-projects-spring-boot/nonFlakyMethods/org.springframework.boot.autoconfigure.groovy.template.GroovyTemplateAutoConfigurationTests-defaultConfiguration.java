@Test public void defaultConfiguration(){
  registerAndRefreshContext();
  assertThat(this.context.getBean(GroovyTemplateViewResolver.class),notNullValue());
}
