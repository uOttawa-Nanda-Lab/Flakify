@Test public void should_increment_n_from_null_delta() throws Exception {
  InternalCounterImpl counterImpl=new InternalCounterImpl(null,null);
  counterImpl.incr(2L);
  assertThat(counterImpl.get()).isEqualTo(2L);
}
