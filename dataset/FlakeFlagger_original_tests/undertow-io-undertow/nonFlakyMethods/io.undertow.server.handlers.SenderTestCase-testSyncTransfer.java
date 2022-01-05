@Test public void testSyncTransfer() throws Exception {
  StringBuilder sb=new StringBuilder(TXS);
  for (int i=0; i < TXS; ++i) {
    sb.append("a");
  }
  HttpGet get=new HttpGet(DefaultServer.getDefaultServerURL() + "/transfer?blocking=true");
  TestHttpClient client=new TestHttpClient();
  try {
    HttpResponse result=client.execute(get);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    File file=new File(SenderTestCase.class.getResource(SenderTestCase.class.getSimpleName() + ".class").toURI());
    byte[] data=new byte[(int)file.length() * TXS];
    for (int i=0; i < TXS; i++) {
      DataInputStream is=new DataInputStream(new FileInputStream(file));
      is.readFully(data,(int)(i * file.length()),(int)file.length());
      is.close();
    }
    Assert.assertArrayEquals(data,HttpClientUtils.readRawResponse(result));
  }
  finally {
    client.getConnectionManager().shutdown();
  }
}
