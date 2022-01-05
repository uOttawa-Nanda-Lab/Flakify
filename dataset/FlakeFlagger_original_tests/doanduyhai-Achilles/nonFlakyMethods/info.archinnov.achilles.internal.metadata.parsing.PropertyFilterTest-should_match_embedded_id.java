@Test public void should_match_embedded_id() throws Exception {
  Field clusteredId=BeanWithClusteredId.class.getDeclaredField("id");
  assertThat(filter.matches(clusteredId)).isTrue();
}
