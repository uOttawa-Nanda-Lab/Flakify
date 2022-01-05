@Test public void testRouteWithUrlEncodedSlashGetsChoppedCorrectly(){
  RouteBuilderImpl routeBuilder=new RouteBuilderImpl();
  routeBuilder.GET().route("/blah/{id}/.*");
  Route route=buildRoute(routeBuilder);
  String routeFromServer="/blah/my%2fid/and/some/more/stuff";
  assertTrue(route.matches("GET",routeFromServer));
  assertEquals(1,route.getPathParametersEncoded(routeFromServer).entrySet().size());
  assertEquals("my%2fid",route.getPathParametersEncoded(routeFromServer).get("id"));
}
