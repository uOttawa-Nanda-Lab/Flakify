@Test public void defaultConfiguration(){
  registerAndRefreshContext();
  assertThat(this.context.getBean(VelocityViewResolver.class),notNullValue());
  assertThat(this.context.getBean(VelocityConfigurer.class),notNullValue());
}
