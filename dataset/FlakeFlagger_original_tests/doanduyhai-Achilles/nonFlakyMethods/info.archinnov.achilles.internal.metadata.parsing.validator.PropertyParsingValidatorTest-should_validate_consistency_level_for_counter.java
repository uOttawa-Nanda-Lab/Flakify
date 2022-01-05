@Test public void should_validate_consistency_level_for_counter() throws Exception {
class Test {
    @SuppressWarnings("unused") public Long counter;
  }
  Field counterField=Test.class.getField("counter");
  when(context.getCurrentField()).thenReturn(counterField);
  when(context.<Test>getCurrentEntityClass()).thenReturn(Test.class);
  Pair<ConsistencyLevel,ConsistencyLevel> consistencyLevels=Pair.create(ANY,ALL);
  exception.expect(AchillesBeanMappingException.class);
  exception.expectMessage("Counter field 'counter' of entity '" + Test.class.getCanonicalName() + "' cannot have ANY as read/write consistency level. All consistency levels except ANY are allowed");
  validator.validateConsistencyLevelForCounter(context,consistencyLevels);
  consistencyLevels=Pair.create(ALL,ANY);
  exception.expect(AchillesBeanMappingException.class);
  exception.expectMessage("Counter field 'counter' of entity '" + Test.class.getCanonicalName() + "' cannot have ANY as read/write consistency level. All consistency levels except ANY are allowed");
  validator.validateConsistencyLevelForCounter(context,consistencyLevels);
}
