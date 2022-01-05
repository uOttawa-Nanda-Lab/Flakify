@Test public void testLogingLogout(){
  Map<String,String> headers=Maps.newHashMap();
  String response=ninjaTestBrowser.makeRequest(getServerAddress() + "article/new",headers);
  System.out.println(response);
  assertTrue(response.contains("Error. Forbidden."));
  Map<String,String> formParameters=Maps.newHashMap();
  formParameters.put("username","bob@gmail.com");
  formParameters.put("password","secret");
  ninjaTestBrowser.makePostRequestWithFormParameters(getServerAddress() + "login",headers,formParameters);
  response=ninjaTestBrowser.makeRequest(getServerAddress() + "article/new",headers);
  assertTrue(response.contains("New article"));
  ninjaTestBrowser.makeRequest(getServerAddress() + "logout",headers);
  response=ninjaTestBrowser.makeRequest(getServerAddress() + "article/new",headers);
  System.out.println(response);
  assertTrue(response.contains("Error. Forbidden."));
}
