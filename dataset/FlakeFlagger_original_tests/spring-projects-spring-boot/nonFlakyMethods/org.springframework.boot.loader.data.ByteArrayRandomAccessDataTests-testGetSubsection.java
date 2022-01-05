@Test public void testGetSubsection() throws Exception {
  byte[] bytes=new byte[]{0,1,2,3,4,5};
  RandomAccessData data=new ByteArrayRandomAccessData(bytes);
  data=data.getSubsection(1,4).getSubsection(1,2);
  assertThat(FileCopyUtils.copyToByteArray(data.getInputStream(ResourceAccess.PER_READ)),equalTo(new byte[]{2,3}));
  assertThat(data.getSize(),equalTo(2L));
}
