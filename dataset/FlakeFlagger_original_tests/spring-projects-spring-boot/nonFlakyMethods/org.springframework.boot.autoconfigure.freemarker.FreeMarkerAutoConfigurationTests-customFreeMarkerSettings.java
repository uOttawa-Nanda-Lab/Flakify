@SuppressWarnings("deprecation") @Test public void customFreeMarkerSettings(){
  registerAndRefreshContext("spring.freemarker.settings.boolean_format:yup,nope");
  assertThat(this.context.getBean(FreeMarkerConfigurer.class).getConfiguration().getSetting("boolean_format"),equalTo("yup,nope"));
}
