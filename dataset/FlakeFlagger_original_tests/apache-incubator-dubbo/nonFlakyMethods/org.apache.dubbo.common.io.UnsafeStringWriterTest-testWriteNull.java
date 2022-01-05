@Test public void testWriteNull() throws IOException {
  UnsafeStringWriter writer=new UnsafeStringWriter(3);
  char[] chars=new char[2];
  chars[0]='a';
  chars[1]='b';
  writer.write(chars);
  writer.write(chars,0,1);
  writer.flush();
  writer.close();
  assertThat(writer.toString(),is("aba"));
}
