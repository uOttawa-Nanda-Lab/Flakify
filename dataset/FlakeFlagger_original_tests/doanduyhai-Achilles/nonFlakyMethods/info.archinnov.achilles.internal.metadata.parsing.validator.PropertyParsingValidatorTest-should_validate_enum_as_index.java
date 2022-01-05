@Test public void should_validate_enum_as_index() throws Exception {
class Test {
    @Index public ConsistencyLevel consistency;
  }
  Field consistencyField=Test.class.getField("consistency");
  when(context.getCurrentField()).thenReturn(consistencyField);
  when(context.<Test>getCurrentEntityClass()).thenReturn(Test.class);
  validator.validateIndexIfSet(context);
}
