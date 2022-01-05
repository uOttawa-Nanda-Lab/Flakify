@Test public void should_build() throws Exception {
  Map<Object,Object> map=new HashMap<Object,Object>();
  map.put(1,"FR");
  map.put(2,"Paris");
  map.put(3,"75014");
  MapWrapper wrapper=MapWrapperBuilder.builder(map).dirtyMap(dirtyMap).setter(setter).propertyMeta(propertyMeta).build();
  assertThat(wrapper.getTarget()).isSameAs(map);
  assertThat(wrapper.getDirtyMap()).isSameAs(dirtyMap);
  assertThat(Whitebox.getInternalState(wrapper,"setter")).isSameAs(setter);
  assertThat(Whitebox.getInternalState(wrapper,"propertyMeta")).isSameAs(propertyMeta);
}
