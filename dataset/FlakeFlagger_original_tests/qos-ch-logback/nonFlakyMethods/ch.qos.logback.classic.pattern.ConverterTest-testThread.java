@Test public void testThread(){
  DynamicConverter<ILoggingEvent> converter=new ThreadConverter();
  StringBuilder buf=new StringBuilder();
  converter.write(buf,le);
  System.out.println(buf.toString());
  String regex=ClassicTestConstants.NAKED_MAIN_REGEX;
  assertTrue(buf.toString().matches(regex));
}
