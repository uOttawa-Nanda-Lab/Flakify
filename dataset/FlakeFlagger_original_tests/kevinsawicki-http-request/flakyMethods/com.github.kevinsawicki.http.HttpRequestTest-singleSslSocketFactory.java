/** 
 * Verify single SSL socket factory is created across all calls
 */
@Test public void singleSslSocketFactory(){
  HttpRequest request1=get("https://localhost").trustAllCerts();
  HttpRequest request2=get("https://localhost").trustAllCerts();
  assertNotNull(((HttpsURLConnection)request1.getConnection()).getSSLSocketFactory());
  assertNotNull(((HttpsURLConnection)request2.getConnection()).getSSLSocketFactory());
  assertEquals(((HttpsURLConnection)request1.getConnection()).getSSLSocketFactory(),((HttpsURLConnection)request2.getConnection()).getSSLSocketFactory());
}
