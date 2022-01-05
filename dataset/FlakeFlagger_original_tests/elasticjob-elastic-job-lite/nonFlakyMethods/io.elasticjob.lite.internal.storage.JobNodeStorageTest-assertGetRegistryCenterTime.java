@Test public void assertGetRegistryCenterTime(){
  when(regCenter.getRegistryCenterTime("/test_job/systemTime/current")).thenReturn(0L);
  assertThat(jobNodeStorage.getRegistryCenterTime(),is(0L));
  verify(regCenter).getRegistryCenterTime("/test_job/systemTime/current");
}
