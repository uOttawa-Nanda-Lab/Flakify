@Test @OperateOnDeployment(FORWARDED_SERVLET_NO_UT_HANDLERS) public void testRewriteGlobalSettingsServlet(@ArquillianResource URL url) throws Exception {
  commonConfigureExpression(new URL(url + "/forwarded"),false);
}
