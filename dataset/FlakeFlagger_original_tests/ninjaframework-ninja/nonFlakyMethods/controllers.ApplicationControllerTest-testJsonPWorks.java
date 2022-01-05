@Test public void testJsonPWorks(){
  String response=ninjaTestBrowser.makeRequest(getServerAddress() + "/jsonp?callback=App.callback");
  assertEquals("App.callback({\"object\":\"value\"})",response);
}
