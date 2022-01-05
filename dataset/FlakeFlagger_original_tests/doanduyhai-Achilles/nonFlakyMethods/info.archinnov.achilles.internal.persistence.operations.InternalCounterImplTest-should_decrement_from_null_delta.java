@Test public void should_decrement_from_null_delta() throws Exception {
  InternalCounterImpl counterImpl=new InternalCounterImpl(null,null);
  counterImpl.decr();
  assertThat(counterImpl.get()).isEqualTo(-1L);
}
