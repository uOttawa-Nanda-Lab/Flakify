@Test public void should_increment_from_null_delta() throws Exception {
  InternalCounterImpl counterImpl=new InternalCounterImpl(null,null);
  counterImpl.incr();
  assertThat(counterImpl.get()).isEqualTo(1L);
}
