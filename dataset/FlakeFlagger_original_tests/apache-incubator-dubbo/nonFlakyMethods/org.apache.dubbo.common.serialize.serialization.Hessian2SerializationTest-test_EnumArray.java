@Test public void test_EnumArray() throws Exception {
  assertObjectArray(new AnimalEnum[]{AnimalEnum.bull,AnimalEnum.cat,AnimalEnum.dog,AnimalEnum.horse},AnimalEnum[].class);
}
