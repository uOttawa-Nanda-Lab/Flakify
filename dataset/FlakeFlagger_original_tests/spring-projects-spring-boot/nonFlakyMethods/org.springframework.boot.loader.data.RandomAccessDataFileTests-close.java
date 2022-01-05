@Test public void close() throws Exception {
  this.file.getInputStream(ResourceAccess.PER_READ).read();
  this.file.close();
  Field filePoolField=RandomAccessDataFile.class.getDeclaredField("filePool");
  filePoolField.setAccessible(true);
  Object filePool=filePoolField.get(this.file);
  Field filesField=filePool.getClass().getDeclaredField("files");
  filesField.setAccessible(true);
  Queue<?> queue=(Queue<?>)filesField.get(filePool);
  assertThat(queue.size(),equalTo(0));
}
