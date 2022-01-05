@Test public void inputStreamReadBytesPastSubsection() throws Exception {
  RandomAccessData subsection=this.file.getSubsection(1,2);
  InputStream inputStream=subsection.getInputStream(ResourceAccess.PER_READ);
  byte[] b=new byte[3];
  int amountRead=inputStream.read(b);
  assertThat(b,equalTo(new byte[]{1,2,0}));
  assertThat(amountRead,equalTo(2));
}
