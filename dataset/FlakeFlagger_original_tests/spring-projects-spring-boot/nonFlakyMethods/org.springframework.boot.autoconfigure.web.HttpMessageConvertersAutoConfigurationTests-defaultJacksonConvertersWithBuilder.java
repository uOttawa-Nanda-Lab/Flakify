@Test public void defaultJacksonConvertersWithBuilder() throws Exception {
  this.context.register(JacksonObjectMapperBuilderConfig.class,HttpMessageConvertersAutoConfiguration.class);
  this.context.refresh();
  assertConverterBeanExists(MappingJackson2HttpMessageConverter.class,"mappingJackson2HttpMessageConverter");
  assertConverterBeanExists(MappingJackson2XmlHttpMessageConverter.class,"mappingJackson2XmlHttpMessageConverter");
  assertConverterBeanRegisteredWithHttpMessageConverters(MappingJackson2HttpMessageConverter.class);
  assertConverterBeanRegisteredWithHttpMessageConverters(MappingJackson2XmlHttpMessageConverter.class);
}
