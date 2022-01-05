@Test public void testRoutes(){
  startServer();
  aRequestLike("GET","/").isHandledBy(ApplicationController.class,"index");
}
