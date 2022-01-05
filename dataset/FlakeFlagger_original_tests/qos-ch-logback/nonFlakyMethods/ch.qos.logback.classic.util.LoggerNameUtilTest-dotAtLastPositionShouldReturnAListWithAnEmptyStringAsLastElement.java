@Test public void dotAtLastPositionShouldReturnAListWithAnEmptyStringAsLastElement(){
  List<String> witnessList=new ArrayList<String>();
  witnessList.add("com");
  witnessList.add("foo");
  witnessList.add("");
  List<String> partList=LoggerNameUtil.computeNameParts("com.foo.");
  assertEquals(witnessList,partList);
}
