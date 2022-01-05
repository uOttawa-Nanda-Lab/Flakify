@Test public void supportNestedClasses(){
  List<String> witnessList=new ArrayList<String>();
  witnessList.add("com");
  witnessList.add("foo");
  witnessList.add("Bar");
  witnessList.add("Nested");
  List<String> partList=LoggerNameUtil.computeNameParts("com.foo.Bar$Nested");
  assertEquals(witnessList,partList);
}
