/** 
 * This message should not be compressed as it is too small
 * @throws IOException
 */
@Test public void testSmallMessagePredicateDoesNotCompress() throws IOException {
  ContentEncodingHttpClient client=new ContentEncodingHttpClient();
  try {
    message="Hi";
    HttpGet get=new HttpGet(DefaultServer.getDefaultServerURL() + "/path");
    get.setHeader(Headers.ACCEPT_ENCODING_STRING,"deflate");
    HttpResponse result=client.execute(get);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    Header[] header=result.getHeaders(Headers.CONTENT_ENCODING_STRING);
    Assert.assertEquals(0,header.length);
    final String body=HttpClientUtils.readResponse(result);
    Assert.assertEquals("Hi",body);
  }
  finally {
    client.getConnectionManager().shutdown();
  }
}
