@Test public void assertGetIdentity(){
  assertThat(new JobEventRdbIdentity().getIdentity(),is("rdb"));
}
