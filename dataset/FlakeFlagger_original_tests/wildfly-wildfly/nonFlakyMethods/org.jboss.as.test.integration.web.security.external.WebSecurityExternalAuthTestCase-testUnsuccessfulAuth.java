/** 
 * <p> Test with user "marcus" who does not have the right role to be authenticated by our external login module. </p> <p> Should be a HTTP/403 </p>
 * @throws Exception
 */
@Test public void testUnsuccessfulAuth() throws Exception {
  makeCall("marcus",403);
}
