@Test public void startWith() throws Exception {
  AsciiBytes abc=new AsciiBytes(new byte[]{65,66,67});
  AsciiBytes ab=new AsciiBytes(new byte[]{65,66});
  AsciiBytes bc=new AsciiBytes(new byte[]{65,66,67},1,2);
  AsciiBytes abcd=new AsciiBytes(new byte[]{65,66,67,68});
  assertThat(abc.startsWith(abc),equalTo(true));
  assertThat(abc.startsWith(ab),equalTo(true));
  assertThat(abc.startsWith(bc),equalTo(false));
  assertThat(abc.startsWith(abcd),equalTo(false));
}
