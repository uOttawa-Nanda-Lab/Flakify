/** 
 * Verify hostname verifier is set and accepts all
 */
@Test public void verifierAccepts(){
  HttpRequest request=get("https://localhost");
  HttpsURLConnection connection=(HttpsURLConnection)request.getConnection();
  request.trustAllHosts();
  assertNotNull(connection.getHostnameVerifier());
  assertTrue(connection.getHostnameVerifier().verify(null,null));
}
