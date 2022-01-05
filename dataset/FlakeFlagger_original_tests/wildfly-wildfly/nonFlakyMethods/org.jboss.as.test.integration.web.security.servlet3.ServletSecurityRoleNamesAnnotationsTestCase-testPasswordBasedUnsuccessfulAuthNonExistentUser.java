/** 
 * <p> Test with non-existent user "non-existent-user". </p> <p> Should be a HTTP/403 </p>
 * @throws Exception
 */
@Test public void testPasswordBasedUnsuccessfulAuthNonExistentUser() throws Exception {
  makeCallSecured("non-existent-user","non-existent-user",401);
  makeCallWeaklySecured("non-existent-user","non-existent-user",401);
  makeCallHardSecured("non-existent-user","non-existent-user",403);
}
