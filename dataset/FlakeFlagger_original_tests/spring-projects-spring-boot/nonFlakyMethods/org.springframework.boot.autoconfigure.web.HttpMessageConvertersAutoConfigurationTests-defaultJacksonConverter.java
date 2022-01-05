@Test public void defaultJacksonConverter() throws Exception {
  this.context.register(JacksonObjectMapperConfig.class,HttpMessageConvertersAutoConfiguration.class);
  this.context.refresh();
  assertConverterBeanExists(MappingJackson2HttpMessageConverter.class,"mappingJackson2HttpMessageConverter");
  assertConverterBeanRegisteredWithHttpMessageConverters(MappingJackson2HttpMessageConverter.class);
}
