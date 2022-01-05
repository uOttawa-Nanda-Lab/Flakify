@Test public void failed_isIn_assertion_using_field_by_field_element_comparator(){
  List<Foo> list1=singletonList(new Foo("id",1));
  List<Foo> list2=singletonList(new Foo("id",2));
  try {
    assertThat(list1).usingFieldByFieldElementComparator().isIn(singletonList(list2));
  }
 catch (  AssertionError e) {
    assertThat(e).hasMessage("\nExpecting:\n" + " <[Foo(id=id, bar=1)]>\n" + "to be in:\n"+ " <[[Foo(id=id, bar=2)]]>\n"+ "when comparing elements using 'field by field comparator on all fields'");
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
