@Test public void testRewrite() throws Exception {
  try (CloseableHttpClient httpClient=HttpClients.createDefault()){
    HttpGet httpget=new HttpGet(url.toExternalForm() + "rewritea");
    HttpResponse response=httpClient.execute(httpget);
    HttpEntity entity=response.getEntity();
    StatusLine statusLine=response.getStatusLine();
    assertEquals(200,statusLine.getStatusCode());
    String result=EntityUtils.toString(entity);
    Assert.assertEquals("A file",result);
    Header[] headers=response.getHeaders("MyHeader");
    Assert.assertEquals(1,headers.length);
    Assert.assertEquals("MyValue",headers[0].getValue());
  }
 }
