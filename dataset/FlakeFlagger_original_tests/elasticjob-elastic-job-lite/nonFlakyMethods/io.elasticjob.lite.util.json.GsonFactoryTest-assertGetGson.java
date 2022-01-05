@Test public void assertGetGson(){
  assertThat(GsonFactory.getGson(),is(GsonFactory.getGson()));
}
