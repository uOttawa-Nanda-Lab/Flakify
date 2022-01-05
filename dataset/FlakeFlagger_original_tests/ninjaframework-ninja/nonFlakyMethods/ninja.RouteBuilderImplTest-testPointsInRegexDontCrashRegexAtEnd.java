@Test public void testPointsInRegexDontCrashRegexAtEnd(){
  RouteBuilderImpl routeBuilder=new RouteBuilderImpl();
  routeBuilder.GET().route("/blah/{id}");
  Route route=buildRoute(routeBuilder);
  String routeFromServer="/blah/my.id";
  assertTrue(route.matches("GET","/blah/my.id"));
  assertEquals(1,route.getPathParametersEncoded(routeFromServer).entrySet().size());
  assertEquals("my.id",route.getPathParametersEncoded(routeFromServer).get("id"));
}
