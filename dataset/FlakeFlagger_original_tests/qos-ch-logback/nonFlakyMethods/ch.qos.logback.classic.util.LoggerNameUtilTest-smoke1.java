@Test public void smoke1(){
  List<String> witnessList=new ArrayList<String>();
  witnessList.add("com");
  witnessList.add("foo");
  witnessList.add("Bar");
  List<String> partList=LoggerNameUtil.computeNameParts("com.foo.Bar");
  assertEquals(witnessList,partList);
}
