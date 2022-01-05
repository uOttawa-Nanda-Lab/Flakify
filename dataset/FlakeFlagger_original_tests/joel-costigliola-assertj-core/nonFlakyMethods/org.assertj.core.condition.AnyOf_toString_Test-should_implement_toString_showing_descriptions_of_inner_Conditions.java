@Test public void should_implement_toString_showing_descriptions_of_inner_Conditions(){
  String expected="any of:<[Condition 1, Condition 2]>";
  assertEquals(expected,anyOf.toString());
}
