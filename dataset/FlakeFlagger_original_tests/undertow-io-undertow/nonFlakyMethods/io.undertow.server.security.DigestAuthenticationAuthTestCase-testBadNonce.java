/** 
 * Test for a failed authentication where a bad nonce is provided.
 */
@Test public void testBadNonce() throws Exception {
  setAuthenticationChain();
  TestHttpClient client=new TestHttpClient();
  HttpGet get=new HttpGet(DefaultServer.getDefaultServerURL());
  HttpResponse result=client.execute(get);
  assertEquals(401,result.getStatusLine().getStatusCode());
  Header[] values=result.getHeaders(WWW_AUTHENTICATE.toString());
  assertEquals(1,values.length);
  String value=values[0].getValue();
  assertTrue(value.startsWith(DIGEST.toString()));
  Map<DigestWWWAuthenticateToken,String> parsedHeader=DigestWWWAuthenticateToken.parseHeader(value.substring(7));
  assertEquals(REALM_NAME,parsedHeader.get(DigestWWWAuthenticateToken.REALM));
  assertEquals(DigestAlgorithm.MD5.getToken(),parsedHeader.get(DigestWWWAuthenticateToken.ALGORITHM));
  assertEquals(DigestQop.AUTH.getToken(),parsedHeader.get(DigestWWWAuthenticateToken.MESSAGE_QOP));
  String clientNonce=createNonce();
  int nonceCount=1;
  String nonce="AU1aCIiy48ENMTM1MTE3OTUxMDU2OLrHnBlV2GBzzguCWOPET+0=";
  String opaque=parsedHeader.get(DigestWWWAuthenticateToken.OPAQUE);
  assertNotNull(opaque);
  client=new TestHttpClient();
  get=new HttpGet(DefaultServer.getDefaultServerURL());
  int thisNonceCount=nonceCount++;
  String authorization=createAuthorizationLine("userOne","badPassword","GET","/",nonce,thisNonceCount,clientNonce,opaque);
  get.addHeader(AUTHORIZATION.toString(),authorization);
  result=client.execute(get);
  assertEquals(401,result.getStatusLine().getStatusCode());
  assertSingleNotificationType(EventType.FAILED_AUTHENTICATION);
}
