@Test public void should_fail_if_actual_size_is_not_equal_to_other_size(){
  AssertionInfo info=someInfo();
  String[] actual=array("Yoda");
  List<String> other=newArrayList("Solo","Leia");
  try {
    arrays.assertHasSameSizeAs(info,actual,other);
  }
 catch (  AssertionError e) {
    assertThat(e).hasMessage(shouldHaveSameSizeAs(actual,actual.length,other.size()).create(null,info.representation()));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
