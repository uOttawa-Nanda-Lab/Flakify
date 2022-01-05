@Test public void testExternalAndInternalTagLib() throws Exception {
  try (CloseableHttpClient httpClient=HttpClients.createDefault()){
    HttpGet httpget=new HttpGet(both_dependencies_url.toExternalForm() + TEST_JSP);
    HttpResponse response=httpClient.execute(httpget);
    HttpEntity entity=response.getEntity();
    String result=EntityUtils.toString(entity);
    Assert.assertTrue(result,result.contains("External Tag!"));
    Assert.assertTrue(result,result.contains("Internal Tag!"));
  }
 }
