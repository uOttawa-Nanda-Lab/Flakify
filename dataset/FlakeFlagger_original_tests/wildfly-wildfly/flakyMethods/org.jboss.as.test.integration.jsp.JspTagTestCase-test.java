@Test public void test(@ArquillianResource URL url) throws Exception {
  Assert.assertEquals(RESULT.replace("\r",""),HttpRequest.get(url + "index.jsp",10,TimeUnit.SECONDS).replace("\r",""));
  Assert.assertEquals(RESULT.replace("\r",""),HttpRequest.get(url + "index2.jsp",10,TimeUnit.SECONDS).replace("\r",""));
}
