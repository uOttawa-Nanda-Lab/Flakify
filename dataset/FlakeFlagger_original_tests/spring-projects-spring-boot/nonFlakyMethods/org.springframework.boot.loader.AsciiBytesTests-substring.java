@Test public void substring() throws Exception {
  AsciiBytes abcd=new AsciiBytes(new byte[]{65,66,67,68});
  assertThat(abcd.substring(0,4).toString(),equalTo("ABCD"));
  assertThat(abcd.substring(1,3).toString(),equalTo("BC"));
  assertThat(abcd.substring(3,4).toString(),equalTo("D"));
  assertThat(abcd.substring(3,3).toString(),equalTo(""));
  this.thrown.expect(IndexOutOfBoundsException.class);
  abcd.substring(3,5);
}
