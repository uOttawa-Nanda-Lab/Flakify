@Test public void testBasicPUTRoute(){
  RouteBuilderImpl routeBuilder=new RouteBuilderImpl();
  routeBuilder.PUT().route("/index");
  assertTrue(buildRoute(routeBuilder).matches("PUT","/index"));
}
