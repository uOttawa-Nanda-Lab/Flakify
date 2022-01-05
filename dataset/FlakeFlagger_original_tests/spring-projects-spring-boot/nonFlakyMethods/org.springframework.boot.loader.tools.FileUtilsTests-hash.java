@Test public void hash() throws Exception {
  File file=this.temporaryFolder.newFile();
  OutputStream outputStream=new FileOutputStream(file);
  try {
    outputStream.write(new byte[]{1,2,3});
  }
  finally {
    outputStream.close();
  }
  assertThat(FileUtils.sha1Hash(file),equalTo("7037807198c22a7d2b0807371d763779a84fdfcf"));
}
