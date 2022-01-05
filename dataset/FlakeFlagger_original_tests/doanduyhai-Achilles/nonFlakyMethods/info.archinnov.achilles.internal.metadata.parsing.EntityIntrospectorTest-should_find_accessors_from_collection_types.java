@Test public void should_find_accessors_from_collection_types() throws Exception {
  Method[] accessors=introspector.findAccessors(ComplexBean.class,ComplexBean.class.getDeclaredField("friends"));
  assertThat(accessors).hasSize(2);
  assertThat(accessors[0].getName()).isEqualTo("getFriends");
  assertThat(accessors[1].getName()).isEqualTo("setFriends");
}
