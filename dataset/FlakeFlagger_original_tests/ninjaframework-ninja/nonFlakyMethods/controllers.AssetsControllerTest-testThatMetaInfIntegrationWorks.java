@Test public void testThatMetaInfIntegrationWorks(){
  Map<String,String> headers=Maps.newHashMap();
  HttpResponse httpResponse=ninjaTestBrowser.makeRequestAndGetResponse(getServerAddress() + "assets/webjars/bootstrap/3.0.0/css/bootstrap.min.css",headers);
  assertEquals(200,httpResponse.getStatusLine().getStatusCode());
}
