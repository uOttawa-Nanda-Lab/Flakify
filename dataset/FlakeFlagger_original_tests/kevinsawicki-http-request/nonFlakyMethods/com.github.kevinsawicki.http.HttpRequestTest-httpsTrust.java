/** 
 * Verify certificate and host helpers on HTTPS connection
 * @throws Exception
 */
@Test public void httpsTrust() throws Exception {
  assertNotNull(get("https://localhost").trustAllCerts().trustAllHosts());
}
