@Test(expected=NullPointerException.class) public void cannotExtractGroupNameWithNullUri(){
  groupExtractor.getGroupName(null);
}
