@Test public void testThatPathParamParsingWorks(){
  Map<String,String> headers=Maps.newHashMap();
  String response=ninjaTestBrowser.makeRequest(getServerAddress() + "user/12345/john@example.com/userDashboard",headers);
  assertTrue(response.contains("john@example.com"));
  assertTrue(response.contains("12345"));
  assertTrue(response.contains("By the way... Reverse url of this rawUrl is: /user/12345/john@example.com/userDashboard"));
}
