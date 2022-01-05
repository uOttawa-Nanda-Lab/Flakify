@Test public void testGenericSerializationJava() throws Exception {
  ServiceConfig<DemoService> service=new ServiceConfig<DemoService>();
  service.setApplication(new ApplicationConfig("generic-provider"));
  service.setRegistry(new RegistryConfig("N/A"));
  service.setProtocol(new ProtocolConfig("dubbo",29581));
  service.setInterface(DemoService.class.getName());
  DemoServiceImpl ref=new DemoServiceImpl();
  service.setRef(ref);
  service.export();
  try {
    ReferenceConfig<GenericService> reference=new ReferenceConfig<GenericService>();
    reference.setApplication(new ApplicationConfig("generic-consumer"));
    reference.setInterface(DemoService.class);
    reference.setUrl("dubbo://127.0.0.1:29581?scope=remote&timeout=3000");
    reference.setGeneric(Constants.GENERIC_SERIALIZATION_NATIVE_JAVA);
    GenericService genericService=reference.get();
    try {
      String name="kimi";
      ByteArrayOutputStream bos=new ByteArrayOutputStream(512);
      ExtensionLoader.getExtensionLoader(Serialization.class).getExtension("nativejava").serialize(null,bos).writeObject(name);
      byte[] arg=bos.toByteArray();
      Object obj=genericService.$invoke("sayName",new String[]{String.class.getName()},new Object[]{arg});
      Assert.assertTrue(obj instanceof byte[]);
      byte[] result=(byte[])obj;
      Assert.assertEquals(ref.sayName(name),ExtensionLoader.getExtensionLoader(Serialization.class).getExtension("nativejava").deserialize(null,new ByteArrayInputStream(result)).readObject().toString());
      List<User> users=new ArrayList<User>();
      User user=new User();
      user.setName(name);
      users.add(user);
      bos=new ByteArrayOutputStream(512);
      ExtensionLoader.getExtensionLoader(Serialization.class).getExtension("nativejava").serialize(null,bos).writeObject(users);
      obj=genericService.$invoke("getUsers",new String[]{List.class.getName()},new Object[]{bos.toByteArray()});
      Assert.assertTrue(obj instanceof byte[]);
      result=(byte[])obj;
      Assert.assertEquals(users,ExtensionLoader.getExtensionLoader(Serialization.class).getExtension("nativejava").deserialize(null,new ByteArrayInputStream(result)).readObject());
      bos=new ByteArrayOutputStream(512);
      ExtensionLoader.getExtensionLoader(Serialization.class).getExtension("nativejava").serialize(null,bos).writeObject(Integer.MAX_VALUE);
      obj=genericService.$invoke("echo",new String[]{int.class.getName()},new Object[]{bos.toByteArray()});
      Assert.assertTrue(obj instanceof byte[]);
      Assert.assertEquals(Integer.MAX_VALUE,ExtensionLoader.getExtensionLoader(Serialization.class).getExtension("nativejava").deserialize(null,new ByteArrayInputStream((byte[])obj)).readObject());
    }
  finally {
      reference.destroy();
    }
  }
  finally {
    service.unexport();
  }
}
