@Test public void should_return_copy_of_iterator() throws Exception {
  List<Object> target=new ArrayList<>();
  target.add("a");
  target.add("b");
  ListWrapper listWrapper=prepareListWrapper(target);
  Iterator<Object> iterator=listWrapper.iterator();
  iterator.next();
  iterator.remove();
  assertThat(target).containsExactly("a","b");
}
