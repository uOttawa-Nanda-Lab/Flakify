@Test public void testBasicPOSTRoute(){
  RouteBuilderImpl routeBuilder=new RouteBuilderImpl();
  routeBuilder.POST().route("/index");
  assertTrue(buildRoute(routeBuilder).matches("POST","/index"));
}
