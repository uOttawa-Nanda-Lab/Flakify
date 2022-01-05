@Test public void nested(){
  Throwable t=makeNestedException(1);
  verify(t);
}
