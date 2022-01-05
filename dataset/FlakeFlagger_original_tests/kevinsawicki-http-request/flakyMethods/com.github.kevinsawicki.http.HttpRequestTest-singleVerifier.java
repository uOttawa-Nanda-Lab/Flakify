/** 
 * Verify single hostname verifier is created across all calls
 */
@Test public void singleVerifier(){
  HttpRequest request1=get("https://localhost").trustAllHosts();
  HttpRequest request2=get("https://localhost").trustAllHosts();
  assertNotNull(((HttpsURLConnection)request1.getConnection()).getHostnameVerifier());
  assertNotNull(((HttpsURLConnection)request2.getConnection()).getHostnameVerifier());
  assertEquals(((HttpsURLConnection)request1.getConnection()).getHostnameVerifier(),((HttpsURLConnection)request2.getConnection()).getHostnameVerifier());
}
