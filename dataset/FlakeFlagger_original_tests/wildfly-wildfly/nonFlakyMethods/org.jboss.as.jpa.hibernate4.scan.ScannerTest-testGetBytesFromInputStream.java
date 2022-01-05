@Test public void testGetBytesFromInputStream() throws Exception {
  File file=buildLargeJar();
  long start=System.currentTimeMillis();
  InputStream stream=new BufferedInputStream(new FileInputStream(file));
  int oldLength=getBytesFromInputStream(stream).length;
  stream.close();
  long oldTime=System.currentTimeMillis() - start;
  start=System.currentTimeMillis();
  stream=new BufferedInputStream(new FileInputStream(file));
  int newLength=ArchiveHelper.getBytesFromInputStream(stream).length;
  stream.close();
  long newTime=System.currentTimeMillis() - start;
  assertEquals(oldLength,newLength);
  assertTrue(oldTime > newTime);
}
