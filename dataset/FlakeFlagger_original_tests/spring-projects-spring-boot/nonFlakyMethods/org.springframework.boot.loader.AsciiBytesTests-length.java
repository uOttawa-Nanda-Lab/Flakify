@Test public void length() throws Exception {
  AsciiBytes b1=new AsciiBytes(new byte[]{65,66});
  AsciiBytes b2=new AsciiBytes(new byte[]{65,66,67,68},1,2);
  assertThat(b1.length(),equalTo(2));
  assertThat(b2.length(),equalTo(2));
}
