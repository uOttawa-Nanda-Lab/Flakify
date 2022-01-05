@Test public void testWithOneChildMarker(){
  Marker marker=markerFactory.getMarker("test");
  marker.add(markerFactory.getMarker("child"));
  String result=converter.convert(createLoggingEvent(marker));
  assertEquals("test [ child ]",result);
}
