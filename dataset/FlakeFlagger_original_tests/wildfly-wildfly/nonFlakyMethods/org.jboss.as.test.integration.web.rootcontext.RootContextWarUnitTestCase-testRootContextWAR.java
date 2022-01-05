@Test public void testRootContextWAR(@ArquillianResource URL url) throws Exception {
  String response=RootContextUtil.hitRootContext(url,HOST);
  assertTrue(response.contains("A Root Context Page"));
}
