@Test public void should_filter_Collection(){
  List<Object> original=new ArrayList<>();
  original.add(1);
  original.add("Frodo");
  original.add(5);
  List<String> filtered=new TypeFilter<>(String.class).filter(original);
  assertEquals(1,filtered.size());
  assertEquals("Frodo",filtered.get(0));
}
