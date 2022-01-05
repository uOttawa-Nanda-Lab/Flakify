@Test public void should_build() throws Exception {
  List<Object> target=new ArrayList<Object>();
  ListWrapper wrapper=ListWrapperBuilder.builder(target).dirtyMap(dirtyMap).setter(setter).propertyMeta(propertyMeta).build();
  assertThat(wrapper.getTarget()).isSameAs(target);
  assertThat(wrapper.getDirtyMap()).isSameAs(dirtyMap);
  assertThat(Whitebox.getInternalState(wrapper,"setter")).isSameAs(setter);
  assertThat(Whitebox.getInternalState(wrapper,"propertyMeta")).isSameAs(propertyMeta);
}
