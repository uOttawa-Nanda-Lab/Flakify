@Test public void disableCache(){
  registerAndRefreshContext("spring.freemarker.cache:false");
  assertThat(this.context.getBean(FreeMarkerViewResolver.class).getCacheLimit(),equalTo(0));
}
