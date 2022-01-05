@Test public void testEmptyContentToString() throws Exception {
  NullHttpEntity httpentity=new NullHttpEntity();
  String s=EntityUtils.toString(httpentity);
  Assert.assertNull(s);
}
