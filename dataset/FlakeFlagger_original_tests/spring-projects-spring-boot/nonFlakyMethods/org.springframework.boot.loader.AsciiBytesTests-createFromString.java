@Test public void createFromString() throws Exception {
  AsciiBytes bytes=new AsciiBytes("AB");
  assertThat(bytes.toString(),equalTo("AB"));
}
