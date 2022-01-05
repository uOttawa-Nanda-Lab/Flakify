@Test public void testWithMarker(){
  String name="test";
  Marker marker=markerFactory.getMarker(name);
  String result=converter.convert(createLoggingEvent(marker));
  assertEquals(name,result);
}
