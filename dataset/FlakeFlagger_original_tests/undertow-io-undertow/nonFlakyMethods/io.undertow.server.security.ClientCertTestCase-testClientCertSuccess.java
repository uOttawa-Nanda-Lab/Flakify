@Test public void testClientCertSuccess() throws Exception {
  setAuthenticationChain();
  TestHttpClient client=new TestHttpClient();
  client.setSSLContext(clientSSLContext);
  HttpGet get=new HttpGet(DefaultServer.getDefaultServerSSLAddress());
  HttpResponse result=client.execute(get);
  assertEquals(200,result.getStatusLine().getStatusCode());
  Header[] values=result.getHeaders("ProcessedBy");
  assertEquals("ProcessedBy Headers",1,values.length);
  assertEquals("ResponseHandler",values[0].getValue());
  values=result.getHeaders("AuthenticatedUser");
  assertEquals("AuthenticatedUser Headers",1,values.length);
  assertEquals("CN=Test Client,OU=OU,O=Org,L=City,ST=State,C=GB",values[0].getValue());
  HttpClientUtils.readResponse(result);
  assertSingleNotificationType(EventType.AUTHENTICATED);
}
