@Test public void testActivateComparator(){
  Filter1 f1=new Filter1();
  Filter2 f2=new Filter2();
  Filter3 f3=new Filter3();
  Filter4 f4=new Filter4();
  OldFilter5 f5=new OldFilter5();
  List<Filter0> filters=new ArrayList<>();
  filters.add(f1);
  filters.add(f2);
  filters.add(f3);
  filters.add(f4);
  filters.add(f5);
  Collections.sort(filters,ActivateComparator.COMPARATOR);
  Assert.assertEquals(f4,filters.get(0));
  Assert.assertEquals(f5,filters.get(1));
  Assert.assertEquals(f3,filters.get(2));
  Assert.assertEquals(f2,filters.get(3));
  Assert.assertEquals(f1,filters.get(4));
}
