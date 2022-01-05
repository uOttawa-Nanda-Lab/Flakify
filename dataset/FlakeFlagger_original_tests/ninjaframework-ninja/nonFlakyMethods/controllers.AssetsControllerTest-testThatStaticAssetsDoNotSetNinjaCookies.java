@Test public void testThatStaticAssetsDoNotSetNinjaCookies(){
  Map<String,String> headers=Maps.newHashMap();
  headers.put("Cookie","NINJA_FLASH=\"success=This+is+a+flashed+success+-+with+placeholder%3A+PLACEHOLDER\";Path=/");
  HttpResponse httpResponse=ninjaTestBrowser.makeRequestAndGetResponse(getServerAddress() + "assets/files/test_for_mimetypes.dxf",headers);
  assertEquals(null,httpResponse.getFirstHeader("Set-Cookie"));
}
