@Test public void addNewConverters(){
  HttpMessageConverter<?> converter1=mock(HttpMessageConverter.class);
  HttpMessageConverter<?> converter2=mock(HttpMessageConverter.class);
  HttpMessageConverters converters=new HttpMessageConverters(converter1,converter2);
  assertTrue(converters.getConverters().contains(converter1));
  assertEquals(converter1,converters.getConverters().get(0));
  assertEquals(converter2,converters.getConverters().get(1));
}
