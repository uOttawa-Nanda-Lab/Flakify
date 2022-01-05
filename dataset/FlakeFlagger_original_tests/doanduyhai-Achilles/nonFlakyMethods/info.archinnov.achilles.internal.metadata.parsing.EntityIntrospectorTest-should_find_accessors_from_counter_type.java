@Test public void should_find_accessors_from_counter_type() throws Exception {
  Method[] accessors=introspector.findAccessors(CompleteBean.class,CompleteBean.class.getDeclaredField("count"));
  assertThat(accessors).hasSize(2);
  assertThat(accessors[0].getName()).isEqualTo("getCount");
  assertThat(accessors[1].getName()).isEqualTo("setCount");
}
