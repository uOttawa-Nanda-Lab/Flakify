@Test public void testBasicGETRoute(){
  RouteBuilderImpl routeBuilder=new RouteBuilderImpl();
  routeBuilder.GET().route("/index");
  assertTrue(buildRoute(routeBuilder).matches("GET","/index"));
}
