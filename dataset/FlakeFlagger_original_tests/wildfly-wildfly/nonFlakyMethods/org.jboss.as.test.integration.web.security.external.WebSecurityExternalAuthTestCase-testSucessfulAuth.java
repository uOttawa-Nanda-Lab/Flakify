/** 
 * Test with user 'anil' who has right role to be authenticated by our external login module.
 * @throws Exception
 */
@Test public void testSucessfulAuth() throws Exception {
  makeCall("anil",200);
}
