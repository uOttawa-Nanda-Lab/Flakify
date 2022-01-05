@Test public void should_validate_index() throws Exception {
class Test {
    @Index public Long counter;
  }
  Field counterField=Test.class.getField("counter");
  when(context.getCurrentField()).thenReturn(counterField);
  when(context.<Test>getCurrentEntityClass()).thenReturn(Test.class);
  validator.validateIndexIfSet(context);
}
