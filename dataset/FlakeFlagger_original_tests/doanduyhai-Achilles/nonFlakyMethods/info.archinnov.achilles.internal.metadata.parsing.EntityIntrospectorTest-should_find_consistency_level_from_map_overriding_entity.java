@Test public void should_find_consistency_level_from_map_overriding_entity() throws Exception {
@Consistency(read=ANY,write=LOCAL_QUORUM) class Test {
  }
  when(configContext.getDefaultReadConsistencyLevel()).thenReturn(ONE);
  when(configContext.getDefaultWriteConsistencyLevel()).thenReturn(TWO);
  when(configContext.getReadConsistencyLevelForTable("table")).thenReturn(THREE);
  when(configContext.getWriteConsistencyLevelForTable("table")).thenReturn(ALL);
  Pair<ConsistencyLevel,ConsistencyLevel> levels=introspector.findConsistencyLevels(Test.class,"table",configContext);
  assertThat(levels.left).isEqualTo(THREE);
  assertThat(levels.right).isEqualTo(ALL);
}
