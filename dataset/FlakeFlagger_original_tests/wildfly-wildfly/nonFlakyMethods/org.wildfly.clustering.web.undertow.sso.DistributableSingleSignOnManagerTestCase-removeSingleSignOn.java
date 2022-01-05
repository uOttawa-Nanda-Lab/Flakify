@Test public void removeSingleSignOn(){
  InvalidatableSingleSignOn sso=mock(InvalidatableSingleSignOn.class);
  this.subject.removeSingleSignOn(sso);
  verify(sso).invalidate();
}
