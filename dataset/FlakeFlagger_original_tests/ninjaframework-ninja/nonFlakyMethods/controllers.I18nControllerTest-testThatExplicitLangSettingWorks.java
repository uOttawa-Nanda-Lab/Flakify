@Test public void testThatExplicitLangSettingWorks(){
  Map<String,String> headers=Maps.newHashMap();
  headers.put("Accept-Language","de-DE");
  String result=ninjaTestBrowser.makeRequest(getServerAddress() + "/i18n/en",headers);
  assertTrue(result.contains(TEXT_EN));
  headers=Maps.newHashMap();
  headers.put("Accept-Language","de-DE");
  result=ninjaTestBrowser.makeRequest(getServerAddress() + "/i18n/tk",headers);
  assertTrue(result.contains(TEXT_EN));
  headers=Maps.newHashMap();
  result=ninjaTestBrowser.makeRequest(getServerAddress() + "/i18n/tk",headers);
  assertTrue(result.contains(TEXT_EN));
  headers=Maps.newHashMap();
  headers.put("Accept-Language","de-DE");
  result=ninjaTestBrowser.makeRequest(getServerAddress() + "/i18n/de",headers);
  assertTrue(result.contains(TEXT_DE));
}
