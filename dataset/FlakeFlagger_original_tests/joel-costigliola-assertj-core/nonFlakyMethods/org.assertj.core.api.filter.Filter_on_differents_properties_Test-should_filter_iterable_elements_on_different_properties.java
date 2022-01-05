@Test public void should_filter_iterable_elements_on_different_properties(){
  assertThat(filter(players).with("reboundsPerGame").equalsTo(5).and("team").notEqualsTo("OKC").get()).containsOnly(rose);
  assertThat(players).hasSize(4);
}
