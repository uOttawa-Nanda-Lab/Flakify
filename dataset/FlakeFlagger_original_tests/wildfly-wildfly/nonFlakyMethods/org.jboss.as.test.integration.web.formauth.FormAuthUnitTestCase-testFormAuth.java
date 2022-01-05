/** 
 * Test form authentication of a secured servlet
 * @throws Exception
 */
@Test @OperateOnDeployment("form-auth.war") public void testFormAuth() throws Exception {
  log.trace("+++ testFormAuth");
  doSecureGetWithLogin("restricted/SecuredServlet");
  doSecureGet("restricted/SecuredServlet");
}
