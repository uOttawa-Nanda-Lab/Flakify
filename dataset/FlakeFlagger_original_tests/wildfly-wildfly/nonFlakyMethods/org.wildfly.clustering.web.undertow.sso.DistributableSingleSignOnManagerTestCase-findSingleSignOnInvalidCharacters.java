@Test public void findSingleSignOnInvalidCharacters(){
  String id="sso+";
  SingleSignOn result=this.subject.findSingleSignOn(id);
  assertNull(result);
  verifyZeroInteractions(this.manager);
}
