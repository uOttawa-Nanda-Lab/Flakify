@Test public void test_EnumArray_withType() throws Exception {
  assertObjectArrayWithType(new AnimalEnum[]{AnimalEnum.bull,AnimalEnum.cat,AnimalEnum.dog,AnimalEnum.horse},AnimalEnum[].class);
}
