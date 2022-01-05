@Test public void defaultConfiguration(){
  registerAndRefreshContext();
  assertThat(this.context.getBean(FreeMarkerViewResolver.class),notNullValue());
  assertThat(this.context.getBean(FreeMarkerConfigurer.class),notNullValue());
}
