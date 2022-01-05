@Test @OperateOnDeployment(FORWARDED_HANDLER_NO_UT_HANDLERS) public void testRewriteGlobalSettings(@ArquillianResource URL url) throws Exception {
  commonConfigureExpression(url,true);
}
