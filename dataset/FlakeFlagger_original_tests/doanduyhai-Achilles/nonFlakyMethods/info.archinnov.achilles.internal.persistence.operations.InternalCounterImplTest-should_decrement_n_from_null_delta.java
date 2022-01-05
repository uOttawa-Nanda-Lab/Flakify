@Test public void should_decrement_n_from_null_delta() throws Exception {
  InternalCounterImpl counterImpl=new InternalCounterImpl(null,null);
  counterImpl.decr(2L);
  assertThat(counterImpl.get()).isEqualTo(-2L);
}
