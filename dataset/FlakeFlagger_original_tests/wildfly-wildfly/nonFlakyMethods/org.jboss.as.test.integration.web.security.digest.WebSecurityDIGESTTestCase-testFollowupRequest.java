/** 
 * Check that after successful login, the nonce can be re-used without an extra 401 Unauthorized response loop.
 * @param url
 * @throws Exception
 */
@OperateOnDeployment(DEPLOYMENT) @Test public void testFollowupRequest(@ArquillianResource URL url) throws Exception {
  makeCallFollowup(WebSecurityDigestSecurityDomainSetup.GOOD_USER_NAME,WebSecurityDigestSecurityDomainSetup.GOOD_USER_PASSWORD,HTTP_OK,true);
}
