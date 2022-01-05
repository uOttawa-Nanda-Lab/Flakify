@Test public void testWithSeveralChildMarker(){
  Marker marker=markerFactory.getMarker("testParent");
  marker.add(markerFactory.getMarker("child1"));
  marker.add(markerFactory.getMarker("child2"));
  marker.add(markerFactory.getMarker("child3"));
  String result=converter.convert(createLoggingEvent(marker));
  assertEquals("testParent [ child1, child2, child3 ]",result);
}
