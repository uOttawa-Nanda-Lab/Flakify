@Test public void should_build() throws Exception {
  Set<Object> target=new HashSet<>();
  SetWrapper wrapper=SetWrapperBuilder.builder(target).dirtyMap(dirtyMap).setter(setter).propertyMeta(propertyMeta).build();
  assertThat(wrapper.getTarget()).isSameAs(target);
  assertThat(wrapper.getDirtyMap()).isSameAs(dirtyMap);
  assertThat(Whitebox.getInternalState(wrapper,"setter")).isSameAs(setter);
  assertThat(Whitebox.getInternalState(wrapper,"propertyMeta")).isSameAs(propertyMeta);
}
