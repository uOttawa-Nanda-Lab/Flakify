@Test public void endsWith() throws Exception {
  AsciiBytes abc=new AsciiBytes(new byte[]{65,66,67});
  AsciiBytes bc=new AsciiBytes(new byte[]{65,66,67},1,2);
  AsciiBytes ab=new AsciiBytes(new byte[]{65,66});
  AsciiBytes aabc=new AsciiBytes(new byte[]{65,65,66,67});
  assertThat(abc.endsWith(abc),equalTo(true));
  assertThat(abc.endsWith(bc),equalTo(true));
  assertThat(abc.endsWith(ab),equalTo(false));
  assertThat(abc.endsWith(aabc),equalTo(false));
}
