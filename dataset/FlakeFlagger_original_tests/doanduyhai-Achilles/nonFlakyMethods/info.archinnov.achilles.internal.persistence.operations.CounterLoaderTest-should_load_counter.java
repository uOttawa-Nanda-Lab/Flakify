@Test public void should_load_counter() throws Exception {
  final long counterValue=11L;
  when(overrider.getReadLevel(context,counterMeta)).thenReturn(ONE);
  when(context.getSimpleCounter(counterMeta,ONE)).thenReturn(counterValue);
  loader.loadCounter(context,entity,counterMeta);
  verify(mapper).setCounterToEntity(counterMeta,entity,counterValue);
}
