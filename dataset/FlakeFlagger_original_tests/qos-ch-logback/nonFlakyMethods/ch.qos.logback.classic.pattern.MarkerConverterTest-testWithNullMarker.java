@Test public void testWithNullMarker(){
  String result=converter.convert(createLoggingEvent(null));
  assertEquals("",result);
}
