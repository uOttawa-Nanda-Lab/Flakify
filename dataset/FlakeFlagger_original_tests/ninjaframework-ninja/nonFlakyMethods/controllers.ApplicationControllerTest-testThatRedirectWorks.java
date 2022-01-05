@Test public void testThatRedirectWorks(){
  Map<String,String> headers=Maps.newHashMap();
  String result=ninjaTestBrowser.makeRequest(getServerAddress() + "/redirect",headers);
  assertTrue(result.contains("Integration Test"));
}
