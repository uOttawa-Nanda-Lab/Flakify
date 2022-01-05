@Test public void customJacksonConverter() throws Exception {
  this.context.register(JacksonObjectMapperConfig.class,JacksonConverterConfig.class,HttpMessageConvertersAutoConfiguration.class);
  this.context.refresh();
  assertConverterBeanExists(MappingJackson2HttpMessageConverter.class,"customJacksonMessageConverter");
}
