@Test public void should_return_new_HashSet(){
  HashSet<Object> set1=Sets.newHashSet();
  HashSet<Object> set2=Sets.newHashSet();
  assertThat(set2).isNotSameAs(set1);
  set1.add("element");
  assertThat(set2).isEmpty();
}
