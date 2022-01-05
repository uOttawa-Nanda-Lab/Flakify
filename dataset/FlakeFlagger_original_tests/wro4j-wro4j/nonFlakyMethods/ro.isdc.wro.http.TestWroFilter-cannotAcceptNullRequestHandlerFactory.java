@Test(expected=NullPointerException.class) public void cannotAcceptNullRequestHandlerFactory(){
  victim.setRequestHandlerFactory(null);
}
