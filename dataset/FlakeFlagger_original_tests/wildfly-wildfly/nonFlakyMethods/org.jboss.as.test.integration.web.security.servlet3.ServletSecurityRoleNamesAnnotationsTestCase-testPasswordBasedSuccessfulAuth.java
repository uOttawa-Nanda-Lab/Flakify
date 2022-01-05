/** 
 * Test with user "anil" who has the right password and the right role to access the servlet.
 * @throws Exception
 */
@Test public void testPasswordBasedSuccessfulAuth() throws Exception {
  makeCallSecured("anil","anil",200);
  makeCallWeaklySecured("anil","anil",200);
  makeCallHardSecured("anil","anil",403);
}
