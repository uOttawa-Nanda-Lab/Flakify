@Test public void testGetSize(){
  Assert.assertEquals(0,ClassUtils.getSize(null));
  List<Integer> list=new ArrayList<>();
  list.add(1);
  Assert.assertEquals(1,ClassUtils.getSize(list));
  Map map=new HashMap();
  map.put(1,1);
  Assert.assertEquals(1,ClassUtils.getSize(map));
  int[] array=new int[1];
  Assert.assertEquals(1,ClassUtils.getSize(array));
  Assert.assertEquals(-1,ClassUtils.getSize(new Object()));
}
