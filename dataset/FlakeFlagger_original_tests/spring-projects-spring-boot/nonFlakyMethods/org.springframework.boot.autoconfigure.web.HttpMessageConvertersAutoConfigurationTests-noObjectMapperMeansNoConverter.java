@Test public void noObjectMapperMeansNoConverter() throws Exception {
  this.context.register(HttpMessageConvertersAutoConfiguration.class);
  this.context.refresh();
  assertTrue(this.context.getBeansOfType(ObjectMapper.class).isEmpty());
  assertTrue(this.context.getBeansOfType(MappingJackson2HttpMessageConverter.class).isEmpty());
  assertTrue(this.context.getBeansOfType(MappingJackson2XmlHttpMessageConverter.class).isEmpty());
}
