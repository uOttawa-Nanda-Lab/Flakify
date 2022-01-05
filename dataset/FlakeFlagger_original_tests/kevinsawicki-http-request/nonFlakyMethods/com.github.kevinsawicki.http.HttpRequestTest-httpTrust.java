/** 
 * Verify certificate and host helpers ignore non-HTTPS connection
 * @throws Exception
 */
@Test public void httpTrust() throws Exception {
  assertNotNull(get("http://localhost").trustAllCerts().trustAllHosts());
}
