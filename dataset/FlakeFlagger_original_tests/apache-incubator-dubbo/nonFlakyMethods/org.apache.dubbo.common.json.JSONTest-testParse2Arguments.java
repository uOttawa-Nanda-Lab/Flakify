@Test public void testParse2Arguments() throws Exception {
  Object[] test=JSON.parse("[1.2, 2, {name:'qianlei',array:[1,2,3,4,98.123]} ]",new Class<?>[]{int.class,int.class,Bean.class});
  assertEquals(test[1],2);
  assertEquals(test[2].getClass(),Bean.class);
  test=JSON.parse("[1.2, 2]",new Class<?>[]{int.class,int.class});
  assertEquals(test[0],1);
}
