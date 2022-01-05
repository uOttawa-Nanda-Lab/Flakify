@Test public void test(){
  String expected="expected";
  when(this.generator.createSessionId()).thenReturn(expected);
  String result=this.factory.createIdentifier();
  assertSame(expected,result);
}
