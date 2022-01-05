@Test public void testMD5ForFile() throws IOException {
  byte[] md5=Bytes.getMD5(new File(getClass().getClassLoader().getResource("md5.testfile.txt").getFile()));
  assertThat(md5,is(Bytes.base642bytes("iNZ+5qHafVNPLJxHwLKJ3w==")));
}
