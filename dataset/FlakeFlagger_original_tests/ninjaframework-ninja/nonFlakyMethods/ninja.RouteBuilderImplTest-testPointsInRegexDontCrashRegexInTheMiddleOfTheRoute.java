@Test public void testPointsInRegexDontCrashRegexInTheMiddleOfTheRoute(){
  RouteBuilderImpl routeBuilder=new RouteBuilderImpl();
  routeBuilder.GET().route("/blah/{id}/myname");
  Route route=buildRoute(routeBuilder);
  String routeFromServer="/blah/my.id/myname";
  assertTrue(route.matches("GET",routeFromServer));
  assertEquals(1,route.getPathParametersEncoded(routeFromServer).entrySet().size());
  assertEquals("my.id",route.getPathParametersEncoded(routeFromServer).get("id"));
  routeFromServer="/blah/my.id/myname/should_not_match";
  assertFalse(route.matches("GET",routeFromServer));
  assertEquals(0,route.getPathParametersEncoded(routeFromServer).entrySet().size());
}
