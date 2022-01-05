@Test public void testToStringWithCharset() throws IOException {
  UnsafeByteArrayOutputStream outputStream=new UnsafeByteArrayOutputStream();
  outputStream.write("Hòa Bình".getBytes());
  assertThat(outputStream.toString("UTF-8"),is("Hòa Bình"));
}
