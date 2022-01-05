@Test public void testPostFormParsingWorks(){
  Map<String,String> headers=Maps.newHashMap();
  Map<String,String> formParameters=Maps.newHashMap();
  formParameters.put("description","test3");
  formParameters.put("email","test2@email.com");
  formParameters.put("name","test1");
  String response=ninjaTestBrowser.makePostRequestWithFormParameters(getServerAddress() + "/contactForm",headers,formParameters);
  assertTrue(response.contains("test3"));
  assertTrue(response.contains("test2@email.com"));
  assertTrue(response.contains("test1"));
}
