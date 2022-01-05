@Test public void shouldNotFailWhenReloadingModelOutsideOfContext() throws Exception {
  Context.unset();
  victim.onModelPeriodChanged(0);
}
