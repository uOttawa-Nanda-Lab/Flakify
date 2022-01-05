@Test @OperateOnDeployment(FORWARDED_SERVLET) public void testRewriteWithUndertowHandlersServlet(@ArquillianResource URL url) throws Exception {
  commonTestPart(new URL(url + "/forwarded"),false);
}
