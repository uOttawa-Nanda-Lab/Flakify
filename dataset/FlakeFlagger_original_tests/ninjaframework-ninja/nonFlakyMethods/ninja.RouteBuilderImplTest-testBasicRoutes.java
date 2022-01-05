@Test public void testBasicRoutes(){
  RouteBuilderImpl routeBuilder=new RouteBuilderImpl();
  routeBuilder.OPTIONS().route("/index");
  assertTrue(buildRoute(routeBuilder).matches("OPTIONS","/index"));
}
