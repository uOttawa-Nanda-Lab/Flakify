@Test public void createFromBytes() throws Exception {
  AsciiBytes bytes=new AsciiBytes(new byte[]{65,66});
  assertThat(bytes.toString(),equalTo("AB"));
}
