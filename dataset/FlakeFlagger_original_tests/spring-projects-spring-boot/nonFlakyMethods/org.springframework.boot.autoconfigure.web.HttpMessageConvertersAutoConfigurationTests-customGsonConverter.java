@Test public void customGsonConverter() throws Exception {
  this.context.register(GsonConfig.class,GsonConverterConfig.class,HttpMessageConvertersAutoConfiguration.class);
  this.context.refresh();
  assertConverterBeanExists(GsonHttpMessageConverter.class,"customGsonMessageConverter");
  assertConverterBeanRegisteredWithHttpMessageConverters(GsonHttpMessageConverter.class);
}
