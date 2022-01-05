@Test public void testThatAssetsWork(){
  Map<String,String> headers=Maps.newHashMap();
  HttpResponse httpResponse=ninjaTestBrowser.makeRequestAndGetResponse(getServerAddress() + "assets/js/google-code-prettify/prettify.css",headers);
  assertEquals(200,httpResponse.getStatusLine().getStatusCode());
}
