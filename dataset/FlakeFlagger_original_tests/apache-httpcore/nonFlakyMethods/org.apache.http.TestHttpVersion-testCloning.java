@Test public void testCloning() throws Exception {
  HttpVersion orig=HttpVersion.HTTP_1_1;
  HttpVersion clone=(HttpVersion)orig.clone();
  Assert.assertEquals(orig,clone);
}
