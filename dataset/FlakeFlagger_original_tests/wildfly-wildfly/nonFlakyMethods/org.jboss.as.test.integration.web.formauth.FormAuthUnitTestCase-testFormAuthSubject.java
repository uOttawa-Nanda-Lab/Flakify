/** 
 * Test form authentication of a secured servlet and validate that there is a SecurityAssociation setting Subject.
 */
@Test @OperateOnDeployment("form-auth.war") public void testFormAuthSubject() throws Exception {
  log.trace("+++ testFormAuthSubject");
  doSecureGetWithLogin("restricted/SecuredServlet");
}
