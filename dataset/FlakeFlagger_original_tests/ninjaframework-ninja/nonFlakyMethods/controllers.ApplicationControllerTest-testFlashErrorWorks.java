@Test public void testFlashErrorWorks(){
  String response=ninjaTestBrowser.makeRequest(getServerAddress() + "/flash_error");
  assertTrue(response.contains("This is a flashed error - with placeholder: PLACEHOLDER"));
}
