@Test public void testZip() throws IOException {
  String s="hello world";
  byte[] zip=Bytes.zip(s.getBytes());
  byte[] unzip=Bytes.unzip(zip);
  assertThat(unzip,is(s.getBytes()));
}
