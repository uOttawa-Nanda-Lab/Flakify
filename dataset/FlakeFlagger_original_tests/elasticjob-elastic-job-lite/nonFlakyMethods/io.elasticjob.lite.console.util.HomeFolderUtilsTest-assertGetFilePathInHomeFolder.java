@Test public void assertGetFilePathInHomeFolder(){
  assertThat(HomeFolderUtils.getFilePathInHomeFolder("test_file"),is(HOME_FOLDER + "test_file"));
}
