@SuppressWarnings("unchecked") @Test() public void should_fail_if_actual_does_not_contains_an_annotation(){
  AssertionInfo info=someInfo();
  actual=AnnotatedClass.class;
  Class<Annotation> expected[]=new Class[]{Override.class,Deprecated.class,MyAnnotation.class};
  try {
    classes.assertContainsAnnotations(someInfo(),actual,expected);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldHaveAnnotations(actual,Sets.<Class<? extends Annotation>>newLinkedHashSet(expected),Sets.<Class<? extends Annotation>>newLinkedHashSet(Override.class,Deprecated.class)));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
