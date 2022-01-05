@Test public void should_return_new_HashSet(){
  LinkedHashSet<Object> set1=Sets.newLinkedHashSet();
  LinkedHashSet<Object> set2=Sets.newLinkedHashSet();
  assertThat(set2).isNotSameAs(set1);
  set1.add("element");
  assertThat(set2).isEmpty();
}
