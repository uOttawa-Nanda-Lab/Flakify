@Test public void smoke0(){
  List<String> witnessList=new ArrayList<String>();
  witnessList.add("a");
  witnessList.add("b");
  witnessList.add("c");
  List<String> partList=LoggerNameUtil.computeNameParts("a.b.c");
  assertEquals(witnessList,partList);
}
