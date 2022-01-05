@Test public void should_find_consistency_level_by_default() throws Exception {
class Test {
  }
  when(configContext.getDefaultReadConsistencyLevel()).thenReturn(ONE);
  when(configContext.getDefaultWriteConsistencyLevel()).thenReturn(TWO);
  when(configContext.getReadConsistencyLevelForTable("table")).thenReturn(null);
  when(configContext.getWriteConsistencyLevelForTable("table")).thenReturn(null);
  Pair<ConsistencyLevel,ConsistencyLevel> levels=introspector.findConsistencyLevels(Test.class,"table",configContext);
  assertThat(levels.left).isEqualTo(ONE);
  assertThat(levels.right).isEqualTo(TWO);
}
