@Test public void defaultGsonConverter() throws Exception {
  this.context.register(GsonConfig.class,HttpMessageConvertersAutoConfiguration.class);
  this.context.refresh();
  assertConverterBeanExists(GsonHttpMessageConverter.class,"gsonHttpMessageConverter");
  assertConverterBeanRegisteredWithHttpMessageConverters(GsonHttpMessageConverter.class);
}
