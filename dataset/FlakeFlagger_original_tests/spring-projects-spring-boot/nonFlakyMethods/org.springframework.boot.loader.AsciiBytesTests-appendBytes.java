@Test public void appendBytes() throws Exception {
  AsciiBytes bc=new AsciiBytes(new byte[]{65,66,67,68},1,2);
  AsciiBytes appended=bc.append(new byte[]{68});
  assertThat(bc.toString(),equalTo("BC"));
  assertThat(appended.toString(),equalTo("BCD"));
}
