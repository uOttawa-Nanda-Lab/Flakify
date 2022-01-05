@Test public void substringFromBeingIndex() throws Exception {
  AsciiBytes abcd=new AsciiBytes(new byte[]{65,66,67,68});
  assertThat(abcd.substring(0).toString(),equalTo("ABCD"));
  assertThat(abcd.substring(1).toString(),equalTo("BCD"));
  assertThat(abcd.substring(2).toString(),equalTo("CD"));
  assertThat(abcd.substring(3).toString(),equalTo("D"));
  assertThat(abcd.substring(4).toString(),equalTo(""));
  this.thrown.expect(IndexOutOfBoundsException.class);
  abcd.substring(5);
}
