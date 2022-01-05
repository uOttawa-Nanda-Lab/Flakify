@Test public void addBeforeExistingConverter(){
  MappingJackson2HttpMessageConverter converter1=new MappingJackson2HttpMessageConverter();
  MappingJackson2HttpMessageConverter converter2=new MappingJackson2HttpMessageConverter();
  HttpMessageConverters converters=new HttpMessageConverters(converter1,converter2);
  assertTrue(converters.getConverters().contains(converter1));
  assertTrue(converters.getConverters().contains(converter2));
  List<MappingJackson2HttpMessageConverter> httpConverters=new ArrayList<MappingJackson2HttpMessageConverter>();
  for (  HttpMessageConverter<?> candidate : converters) {
    if (candidate instanceof MappingJackson2HttpMessageConverter) {
      httpConverters.add((MappingJackson2HttpMessageConverter)candidate);
    }
  }
  assertEquals(3,httpConverters.size());
  assertEquals(0,httpConverters.indexOf(converter1));
  assertEquals(1,httpConverters.indexOf(converter2));
  assertNotEquals(0,converters.getConverters().indexOf(converter1));
}
