@Test public void should_find_consistency_level_from_class() throws Exception {
@Consistency(read=ANY,write=LOCAL_QUORUM) class Test {
  }
  when(configContext.getDefaultReadConsistencyLevel()).thenReturn(ONE);
  when(configContext.getDefaultWriteConsistencyLevel()).thenReturn(TWO);
  when(configContext.getReadConsistencyLevelForTable("table")).thenReturn(null);
  when(configContext.getWriteConsistencyLevelForTable("table")).thenReturn(null);
  Pair<ConsistencyLevel,ConsistencyLevel> levels=introspector.findConsistencyLevels(Test.class,"table",configContext);
  assertThat(levels.left).isEqualTo(ANY);
  assertThat(levels.right).isEqualTo(LOCAL_QUORUM);
}
