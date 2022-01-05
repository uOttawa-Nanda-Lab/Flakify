@Test public void testWeightedComparator(){
  List<Weighted> list=new ArrayList<Weighted>();
  list.add(new Second());
  list.add(new First());
  Collections.sort(list,new WeightedComparator());
  assertEquals(First.class,list.get(0).getClass());
  assertEquals(Second.class,list.get(1).getClass());
}
