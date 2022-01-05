@Test public void testThatNotFoundWorks(){
  Map<String,String> headers=Maps.newHashMap();
  HttpResponse httpResponse=ninjaTestBrowser.makeRequestAndGetResponse(getServerAddress() + "/_non_existing_url",headers);
  assertEquals(404,httpResponse.getStatusLine().getStatusCode());
  String content=ninjaTestBrowser.makeRequest(getServerAddress() + "/_non_existing_url",headers);
  assertTrue(content.contains("Oops. Not found."));
}
