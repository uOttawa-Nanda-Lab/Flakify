@Test public void testReverseProxy() throws Exception {
  try (CloseableHttpClient httpclient=HttpClients.createDefault()){
    final Set<String> results=new HashSet<>();
    for (int i=0; i < 10; ++i) {
      results.add(performCall(httpclient,"name"));
    }
    Assert.assertEquals(2,results.size());
    Assert.assertTrue(results.contains("server1"));
    Assert.assertTrue(results.contains("server2"));
  }
 }
