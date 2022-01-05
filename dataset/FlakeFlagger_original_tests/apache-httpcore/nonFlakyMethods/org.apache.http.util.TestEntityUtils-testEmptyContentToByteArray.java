@Test public void testEmptyContentToByteArray() throws Exception {
  NullHttpEntity httpentity=new NullHttpEntity();
  byte[] bytes=EntityUtils.toByteArray(httpentity);
  Assert.assertNull(bytes);
}
