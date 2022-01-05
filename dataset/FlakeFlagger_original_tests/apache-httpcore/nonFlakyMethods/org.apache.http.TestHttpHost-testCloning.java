@Test public void testCloning() throws Exception {
  HttpHost orig=new HttpHost("somehost",8080,"https");
  HttpHost clone=(HttpHost)orig.clone();
  Assert.assertEquals(orig,clone);
}
