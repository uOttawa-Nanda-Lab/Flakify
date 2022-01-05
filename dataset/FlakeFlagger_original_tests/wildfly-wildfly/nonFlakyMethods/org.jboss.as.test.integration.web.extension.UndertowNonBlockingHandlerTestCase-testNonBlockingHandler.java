@Test public void testNonBlockingHandler() throws Exception {
  try (CloseableHttpClient httpClient=HttpClients.createDefault()){
    HttpGet httpget=new HttpGet(url.toExternalForm());
    HttpResponse response=httpClient.execute(httpget);
    HttpEntity entity=response.getEntity();
    StatusLine statusLine=response.getStatusLine();
    assertEquals(200,statusLine.getStatusCode());
    String result=EntityUtils.toString(entity);
    Assert.assertEquals(SimpleUndertowExtension.THIS_IS_NOT_A_SERVLET,result);
  }
 }
