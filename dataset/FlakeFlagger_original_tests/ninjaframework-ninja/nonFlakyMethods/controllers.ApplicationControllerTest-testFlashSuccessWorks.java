@Test public void testFlashSuccessWorks(){
  String response=ninjaTestBrowser.makeRequest(getServerAddress() + "/flash_success");
  assertTrue(response.contains("This is a flashed success - with placeholder: PLACEHOLDER"));
}
