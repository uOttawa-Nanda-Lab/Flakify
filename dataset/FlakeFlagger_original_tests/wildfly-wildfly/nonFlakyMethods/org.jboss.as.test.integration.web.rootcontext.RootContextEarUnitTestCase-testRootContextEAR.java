@Test public void testRootContextEAR(@ArquillianResource URL url) throws Exception {
  String response=RootContextUtil.hitRootContext(url,HOST);
  assertTrue(response.contains("A Root Context Page"));
}
