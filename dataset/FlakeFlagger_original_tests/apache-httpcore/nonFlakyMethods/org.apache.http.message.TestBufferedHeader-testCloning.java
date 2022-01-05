@Test public void testCloning() throws Exception {
  CharArrayBuffer buf=new CharArrayBuffer(32);
  buf.append("name: value");
  BufferedHeader orig=new BufferedHeader(buf);
  BufferedHeader clone=(BufferedHeader)orig.clone();
  Assert.assertEquals(orig.getName(),clone.getName());
  Assert.assertEquals(orig.getValue(),clone.getValue());
}
