@Test public void testRegexInRouteWorksWithoutSlashAtTheEnd(){
  RouteBuilderImpl routeBuilder=new RouteBuilderImpl();
  routeBuilder.GET().route("/blah/{id}/.*");
  Route route=buildRoute(routeBuilder);
  String routeFromServer="/blah/my.id/and/some/more/stuff";
  assertTrue(route.matches("GET",routeFromServer));
  assertEquals(1,route.getPathParametersEncoded(routeFromServer).entrySet().size());
  assertEquals("my.id",route.getPathParametersEncoded(routeFromServer).get("id"));
  routeFromServer="/blah/my.id/";
  assertTrue(route.matches("GET","/blah/my.id/"));
  assertEquals(1,route.getPathParametersEncoded(routeFromServer).entrySet().size());
  assertEquals("my.id",route.getPathParametersEncoded(routeFromServer).get("id"));
  assertFalse(route.matches("GET","/blah/my.id"));
}
