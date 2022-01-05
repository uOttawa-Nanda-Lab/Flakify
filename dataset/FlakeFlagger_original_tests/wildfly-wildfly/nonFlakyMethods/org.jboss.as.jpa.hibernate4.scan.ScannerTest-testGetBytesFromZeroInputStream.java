@Test public void testGetBytesFromZeroInputStream() throws Exception {
  URL emptyTxtUrl=getClass().getResource("/org/hibernate/jpa/test/packaging/empty.txt");
  if (emptyTxtUrl == null) {
    throw new RuntimeException("Bah!");
  }
  InputStream emptyStream=new BufferedInputStream(emptyTxtUrl.openStream());
  int length=ArchiveHelper.getBytesFromInputStream(emptyStream).length;
  assertEquals(length,0);
  emptyStream.close();
}
