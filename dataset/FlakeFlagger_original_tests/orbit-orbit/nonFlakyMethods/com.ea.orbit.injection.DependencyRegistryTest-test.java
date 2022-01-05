@Test public void test(){
  final DependencyRegistry dr=new DependencyRegistry();
  dr.addSingleton(ArrayList.class);
  assertNotNull(dr.getSingleton(ArrayList.class));
  assertSame(dr.getSingleton(ArrayList.class),dr.getSingleton(ArrayList.class));
  assertNotNull(dr.locate(HashMap.class));
  assertNotSame(dr.locate(HashMap.class),dr.locate(HashMap.class));
}
