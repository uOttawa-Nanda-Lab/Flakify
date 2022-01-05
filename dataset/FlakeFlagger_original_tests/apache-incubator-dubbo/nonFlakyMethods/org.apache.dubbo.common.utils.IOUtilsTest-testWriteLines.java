@Test public void testWriteLines() throws Exception {
  IOUtils.writeLines(os,new String[]{TEXT});
  ByteArrayOutputStream bos=(ByteArrayOutputStream)os;
  assertThat(new String(bos.toByteArray()),equalTo(TEXT + System.lineSeparator()));
}
