@Test public void createFromBytesWithOffset() throws Exception {
  AsciiBytes bytes=new AsciiBytes(new byte[]{65,66,67,68},1,2);
  assertThat(bytes.toString(),equalTo("BC"));
}
