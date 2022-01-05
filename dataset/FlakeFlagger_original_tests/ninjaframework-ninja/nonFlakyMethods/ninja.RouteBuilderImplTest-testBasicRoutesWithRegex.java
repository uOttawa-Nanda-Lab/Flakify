@Test public void testBasicRoutesWithRegex(){
  RouteBuilderImpl routeBuilder=new RouteBuilderImpl();
  routeBuilder.GET().route("/.*");
  Route route=buildRoute(routeBuilder);
  assertTrue(route.matches("GET","/index"));
  assertTrue(route.matches("GET","/stylesheet.css"));
  assertTrue(route.matches("GET","/public/stylesheet.css"));
  assertTrue(route.matches("GET","/public/bootstrap.js"));
}
