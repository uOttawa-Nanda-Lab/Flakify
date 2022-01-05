@Test public void should_get_target() throws Exception {
  ArrayList<Object> target=new ArrayList<>();
  ListWrapper listWrapper=prepareListWrapper(target);
  assertThat(listWrapper.getTarget()).isSameAs(target);
}
