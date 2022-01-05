@Test public void testFlashAnyWorks(){
  String response=ninjaTestBrowser.makeRequest(getServerAddress() + "/flash_any");
  assertTrue(response.contains("This is an arbitrary message as flash message - with placeholder: PLACEHOLDER"));
}
