@Test public void testJoinCollectionString() throws Exception {
  List<String> list=new ArrayList<String>();
  assertEquals("",StringUtils.join(list,","));
  list.add("v1");
  assertEquals("v1",StringUtils.join(list,"-"));
  list.add("v2");
  list.add("v3");
  String out=StringUtils.join(list,":");
  assertEquals("v1:v2:v3",out);
}
