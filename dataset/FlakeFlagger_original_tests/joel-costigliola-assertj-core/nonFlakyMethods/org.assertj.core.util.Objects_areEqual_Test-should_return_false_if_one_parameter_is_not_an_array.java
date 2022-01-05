@Test public void should_return_false_if_one_parameter_is_not_an_array(){
  Object[] a1={"Luke","Yoda","Leia"};
  assertFalse(Objects.areEqual(a1,""));
  assertFalse(Objects.areEqual("",a1));
}
