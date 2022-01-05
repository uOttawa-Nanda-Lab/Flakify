@Test public void disableCache(){
  registerAndRefreshContext("spring.velocity.cache:false");
  assertThat(this.context.getBean(VelocityViewResolver.class).getCacheLimit(),equalTo(0));
}
