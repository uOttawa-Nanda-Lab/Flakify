@Test(expected=AchillesException.class) public void should_exception_when_not_implements_comparable() throws Exception {
  Validator.validateComparable(UserBean.class,"");
}
