@Test public void testHtmlEscapingInTeamplateWorks(){
  Map<String,String> headers=Maps.newHashMap();
  String result=ninjaTestBrowser.makeRequest(getServerAddress() + "upload",headers);
  assertTrue(result.contains("Please specify file to upload:"));
}
