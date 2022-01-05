@Test public void emptyStringShouldReturnAListContainingOneEmptyString(){
  List<String> witnessList=new ArrayList<String>();
  witnessList.add("");
  List<String> partList=LoggerNameUtil.computeNameParts("");
  assertEquals(witnessList,partList);
}
