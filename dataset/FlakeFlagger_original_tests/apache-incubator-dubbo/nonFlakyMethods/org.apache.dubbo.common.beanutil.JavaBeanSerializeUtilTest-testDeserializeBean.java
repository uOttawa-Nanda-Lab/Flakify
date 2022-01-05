@Test public void testDeserializeBean() throws Exception {
  Bean bean=new Bean();
  bean.setDate(new Date());
  bean.setStatus(PersonStatus.ENABLED);
  bean.setType(Bean.class);
  bean.setArray(new Phone[]{});
  Collection<Phone> collection=new ArrayList<Phone>();
  bean.setCollection(collection);
  Phone phone=new Phone();
  collection.add(phone);
  Map<String,FullAddress> map=new HashMap<String,FullAddress>();
  FullAddress address=new FullAddress();
  map.put("first",address);
  bean.setAddresses(map);
  JavaBeanDescriptor beanDescriptor=JavaBeanSerializeUtil.serialize(bean,JavaBeanAccessor.METHOD);
  Object deser=JavaBeanSerializeUtil.deserialize(beanDescriptor);
  Assert.assertTrue(deser instanceof Bean);
  Bean deserBean=(Bean)deser;
  Assert.assertEquals(bean.getDate(),deserBean.getDate());
  Assert.assertEquals(bean.getStatus(),deserBean.getStatus());
  Assert.assertEquals(bean.getType(),deserBean.getType());
  Assert.assertEquals(bean.getCollection().size(),deserBean.getCollection().size());
  Assert.assertEquals(bean.getCollection().iterator().next().getClass(),deserBean.getCollection().iterator().next().getClass());
  Assert.assertEquals(bean.getAddresses().size(),deserBean.getAddresses().size());
  Assert.assertEquals(bean.getAddresses().entrySet().iterator().next().getKey(),deserBean.getAddresses().entrySet().iterator().next().getKey());
  Assert.assertEquals(bean.getAddresses().entrySet().iterator().next().getValue().getClass(),deserBean.getAddresses().entrySet().iterator().next().getValue().getClass());
}
