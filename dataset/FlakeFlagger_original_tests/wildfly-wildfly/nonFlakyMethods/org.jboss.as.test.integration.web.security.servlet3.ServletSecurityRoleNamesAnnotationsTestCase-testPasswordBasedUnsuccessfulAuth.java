/** 
 * <p> Test with user "marcus" who has the right password but does not have the right role. </p> <p> Should be a HTTP/403 </p>
 * @throws Exception
 */
@Test public void testPasswordBasedUnsuccessfulAuth() throws Exception {
  makeCallSecured("marcus","marcus",403);
  makeCallWeaklySecured("marcus","marcus",200);
  makeCallHardSecured("marcus","marcus",403);
}
