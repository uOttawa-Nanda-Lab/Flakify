@Test public void hashCodeAndEquals() throws Exception {
  AsciiBytes abcd=new AsciiBytes(new byte[]{65,66,67,68});
  AsciiBytes bc=new AsciiBytes(new byte[]{66,67});
  AsciiBytes bc_substring=new AsciiBytes(new byte[]{65,66,67,68}).substring(1,3);
  AsciiBytes bc_string=new AsciiBytes("BC");
  assertThat(bc.hashCode(),equalTo(bc.hashCode()));
  assertThat(bc.hashCode(),equalTo(bc_substring.hashCode()));
  assertThat(bc.hashCode(),equalTo(bc_string.hashCode()));
  assertThat(bc,equalTo(bc));
  assertThat(bc,equalTo(bc_substring));
  assertThat(bc,equalTo(bc_string));
  assertThat(bc.hashCode(),not(equalTo(abcd.hashCode())));
  assertThat(bc,not(equalTo(abcd)));
}
