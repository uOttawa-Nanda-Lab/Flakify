@Test public void testGetInputStream() throws Exception {
  byte[] bytes=new byte[]{0,1,2,3,4,5};
  RandomAccessData data=new ByteArrayRandomAccessData(bytes);
  assertThat(FileCopyUtils.copyToByteArray(data.getInputStream(ResourceAccess.PER_READ)),equalTo(bytes));
  assertThat(data.getSize(),equalTo((long)bytes.length));
}
