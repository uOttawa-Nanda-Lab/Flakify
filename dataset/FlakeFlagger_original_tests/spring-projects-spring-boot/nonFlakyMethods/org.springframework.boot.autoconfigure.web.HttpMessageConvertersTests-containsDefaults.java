@Test public void containsDefaults() throws Exception {
  HttpMessageConverters converters=new HttpMessageConverters();
  List<Class<?>> converterClasses=new ArrayList<Class<?>>();
  for (  HttpMessageConverter<?> converter : converters) {
    converterClasses.add(converter.getClass());
  }
  assertThat(converterClasses,equalTo(Arrays.<Class<?>>asList(ByteArrayHttpMessageConverter.class,StringHttpMessageConverter.class,ResourceHttpMessageConverter.class,SourceHttpMessageConverter.class,AllEncompassingFormHttpMessageConverter.class,MappingJackson2HttpMessageConverter.class,MappingJackson2XmlHttpMessageConverter.class)));
}
