@Test public void withMissingTargetDirWithCompression() throws IOException {
  defaultTest("test7","%d{yyyy-MM-dd, aux}/",".gz",FILE_OPTION_SET,NO_RESTART);
}
