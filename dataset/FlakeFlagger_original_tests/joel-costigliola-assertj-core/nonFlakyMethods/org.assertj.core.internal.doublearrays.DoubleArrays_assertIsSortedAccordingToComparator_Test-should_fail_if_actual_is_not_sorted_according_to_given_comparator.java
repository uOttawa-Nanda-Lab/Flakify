@Test public void should_fail_if_actual_is_not_sorted_according_to_given_comparator(){
  AssertionInfo info=someInfo();
  actual=new double[]{3.0,2.0,1.0,9.0};
  try {
    arrays.assertIsSortedAccordingToComparator(info,actual,doubleDescendingOrderComparator);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldBeSortedAccordingToGivenComparator(2,actual,doubleDescendingOrderComparator));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
