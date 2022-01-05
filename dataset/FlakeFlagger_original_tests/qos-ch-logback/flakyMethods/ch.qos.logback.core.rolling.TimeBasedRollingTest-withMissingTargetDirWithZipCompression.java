@Test public void withMissingTargetDirWithZipCompression() throws IOException {
  defaultTest("test8","%d{yyyy-MM-dd, aux}/",".zip",FILE_OPTION_SET,NO_RESTART);
}
