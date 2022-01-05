@Test public void testReverseRoutingWithMap(){
  startServer();
  Map<String,Object> map=Maps.newHashMap();
  String generatedReverseRoute=router.getReverseRoute(ApplicationController.class,"index",map);
  assertEquals("/",generatedReverseRoute);
  map=Maps.newHashMap();
  map.put("id","myId");
  map.put("email","myEmail");
  generatedReverseRoute=router.getReverseRoute(ApplicationController.class,"userDashboard",map);
  assertEquals("/user/myId/myEmail/userDashboard",generatedReverseRoute);
}
