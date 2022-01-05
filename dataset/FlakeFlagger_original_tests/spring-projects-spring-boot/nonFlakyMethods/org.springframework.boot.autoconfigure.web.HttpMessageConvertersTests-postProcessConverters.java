@Test public void postProcessConverters() throws Exception {
  HttpMessageConverters converters=new HttpMessageConverters(){
    @Override protected List<HttpMessageConverter<?>> postProcessConverters(    List<HttpMessageConverter<?>> converters){
      for (Iterator<HttpMessageConverter<?>> iterator=converters.iterator(); iterator.hasNext(); ) {
        if (iterator.next() instanceof MappingJackson2XmlHttpMessageConverter) {
          iterator.remove();
        }
      }
      return converters;
    }
  }
;
  List<Class<?>> converterClasses=new ArrayList<Class<?>>();
  for (  HttpMessageConverter<?> converter : converters) {
    converterClasses.add(converter.getClass());
  }
  assertThat(converterClasses,equalTo(Arrays.<Class<?>>asList(ByteArrayHttpMessageConverter.class,StringHttpMessageConverter.class,ResourceHttpMessageConverter.class,SourceHttpMessageConverter.class,AllEncompassingFormHttpMessageConverter.class,MappingJackson2HttpMessageConverter.class)));
}
