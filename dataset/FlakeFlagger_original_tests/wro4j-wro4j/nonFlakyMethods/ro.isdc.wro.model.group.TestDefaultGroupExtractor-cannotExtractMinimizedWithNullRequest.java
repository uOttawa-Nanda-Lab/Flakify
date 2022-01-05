@Test(expected=NullPointerException.class) public void cannotExtractMinimizedWithNullRequest(){
  groupExtractor.isMinimized(null);
}
