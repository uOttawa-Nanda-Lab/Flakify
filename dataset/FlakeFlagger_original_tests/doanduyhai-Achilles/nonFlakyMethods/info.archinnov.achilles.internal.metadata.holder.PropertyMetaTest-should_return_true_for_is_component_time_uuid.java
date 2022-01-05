@Test public void should_return_true_for_is_component_time_uuid() throws Exception {
  PropertyMeta pm=PropertyMetaTestBuilder.valueClass(String.class).compNames("id","comp1","comp2").compTimeUUID("comp1").build();
  assertThat(pm.isPrimaryKeyTimeUUID("comp1")).isTrue();
  assertThat(pm.isPrimaryKeyTimeUUID("comp2")).isFalse();
}
