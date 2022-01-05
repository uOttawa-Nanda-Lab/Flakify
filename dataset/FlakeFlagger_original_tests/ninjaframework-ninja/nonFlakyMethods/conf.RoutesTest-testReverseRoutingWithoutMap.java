@Test public void testReverseRoutingWithoutMap(){
  startServer();
  String generatedReverseRoute=router.getReverseRoute(ApplicationController.class,"index");
  assertEquals("/",generatedReverseRoute);
  generatedReverseRoute=router.getReverseRoute(ApplicationController.class,"userDashboard");
  assertEquals("/user/{id}/{email}/userDashboard",generatedReverseRoute);
}
