@Test(expected=NullPointerException.class) public void cannotExtractResourceTypeWithNullUri(){
  groupExtractor.getResourceType(null);
}
