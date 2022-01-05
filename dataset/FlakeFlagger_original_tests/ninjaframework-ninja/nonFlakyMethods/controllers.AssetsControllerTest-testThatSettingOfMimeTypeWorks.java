@Test public void testThatSettingOfMimeTypeWorks(){
  Map<String,String> headers=Maps.newHashMap();
  HttpResponse httpResponse=ninjaTestBrowser.makeRequestAndGetResponse(getServerAddress() + "assets/files/test_for_mimetypes.dxf",headers);
  assertEquals("application/dxf;charset=UTF-8",httpResponse.getHeaders("Content-Type")[0].getValue());
}
