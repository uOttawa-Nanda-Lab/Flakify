/** 
 * Check whether user with incorrect credentials has not access to secured page.
 * @param url
 * @throws Exception
 */
@OperateOnDeployment(DEPLOYMENT) @Test public void testWrongUser(@ArquillianResource URL url) throws Exception {
  makeCall(WebSecurityDigestSecurityDomainSetup.GOOD_USER_NAME,WebSecurityDigestSecurityDomainSetup.GOOD_USER_PASSWORD + "makeThisPasswordWrong",HTTP_UNAUTHORIZED);
}
