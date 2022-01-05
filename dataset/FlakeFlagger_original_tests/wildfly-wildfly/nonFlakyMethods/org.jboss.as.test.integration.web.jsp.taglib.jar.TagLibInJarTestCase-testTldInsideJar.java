/** 
 * Tests if deployment with taglib-location pointing to jar fails during deployment phase. Test passes if the correct response is returned from the JSP.
 * @throws Exception
 */
@Test @OperateOnDeployment(TLD_INSIDE_JAR) public void testTldInsideJar() throws Exception {
  checkJspAvailable(urlDep3);
}
