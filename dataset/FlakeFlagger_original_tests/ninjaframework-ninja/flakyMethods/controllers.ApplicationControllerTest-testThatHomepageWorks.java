@Test public void testThatHomepageWorks(){
  String result=ninjaTestBrowser.makeRequest(getServerAddress() + "/");
  assertTrue(result.contains("Hello to the blog example!"));
  assertTrue(result.contains("My second post"));
}
