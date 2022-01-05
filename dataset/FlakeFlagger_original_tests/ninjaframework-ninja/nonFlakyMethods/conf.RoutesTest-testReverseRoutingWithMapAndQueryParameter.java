@Test public void testReverseRoutingWithMapAndQueryParameter(){
  startServer();
  Map<String,Object> map=Maps.newHashMap();
  map.put("id","myId");
  map.put("email","myEmail");
  map.put("paging_size","100");
  map.put("page","1");
  String generatedReverseRoute=router.getReverseRoute(ApplicationController.class,"userDashboard",map);
  assertEquals("/user/myId/myEmail/userDashboard?paging_size=100&page=1",generatedReverseRoute);
}
