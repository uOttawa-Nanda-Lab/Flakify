@Test public void testWrite(){
  UnsafeByteArrayOutputStream outputStream=new UnsafeByteArrayOutputStream(1);
  outputStream.write((int)'a');
  outputStream.write("bc".getBytes(),0,2);
  assertThat(outputStream.size(),is(3));
  assertThat(outputStream.toString(),is("abc"));
}
