@Test public void testHtmlEscapingInTeamplateWorks(){
  String expectedContent="&lt;script&gt;alert('Hello');&lt;/script&gt;";
  Map<String,String> headers=Maps.newHashMap();
  String result=ninjaTestBrowser.makeRequest(getServerAddress() + "htmlEscaping",headers);
  assertTrue(result.contains(expectedContent));
}
