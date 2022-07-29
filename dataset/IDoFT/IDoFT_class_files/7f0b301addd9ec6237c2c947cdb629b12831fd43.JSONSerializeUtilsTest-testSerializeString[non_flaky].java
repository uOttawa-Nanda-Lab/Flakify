package test.utils.serialize;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Proxy;
import java.util.Date;
import java.util.UUID;

import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;

import utils.serialize.json.GenericType;
import utils.serialize.json.JSONSerializeUtils;
import utils.serialize.json.JSONString;

public class JSONSerializeUtilsTest {
	/**
	 * 检测JSON类型的String，但不要求顺序一致
	 */
    public void assertJsonEqualsNonStrict(String json1, String json2) {
        try {
            JSONAssert.assertEquals(json1, json2, false);
        } catch (JSONException jse) {
            throw new IllegalArgumentException(jse.getMessage());
        }
    }

	/**
	 * 测试基于接口类型的序列化和反序列化；<p>
	 * 
	 */
	@Test
	public void testInterface() {
		// 测试基于接口的动态代理对象可以通过 JSONObject 对象实现属性解析；
		Car car = new Car();
		car.setCost(10);
		car.setWeight(1000);
		Wheel wheel = new Wheel();
		wheel.setBlack("Black");
		car.setWheel(wheel);

		String json = JSONSerializeUtils.serializeToJSON(car);

		ParserConfig.global.setAutoTypeSupport(true);

		JSONObject jsonObj = JSONSerializeUtils.deserializeAs(json, JSONObject.class);
		ICar decar = (ICar) Proxy.newProxyInstance(ICar.class.getClassLoader(), new Class[] { ICar.class }, jsonObj);
		System.out.println("Class of decar :" + decar.getClass().getName());
		assertNotNull(decar);
		assertEquals(car.getCost(), decar.getCost());
		assertEquals(car.getWeight(), decar.getWeight());
		Wheel deWheel = decar.getWheel();
		assertEquals(wheel.getBlack(), deWheel.getBlack());

		// 测试对象的属性声明为接口类型也能够正常地序列化与反序列化；
		People people = new People();
		people.setAsset(decar);

		// 基于 JSONSerializeUtils 配置针对接口类型的序列化和反序列化；
		// 验证是否最终能够得到一致的接口类型的结果；
		JSONSerializeUtils.configProxyInterfaces(ICar.class);

		json = JSONSerializeUtils.serializeToJSON(people, true);
		System.out.println("----------- people -----------");
		System.out.println(json);

		People people_des = JSONSerializeUtils.deserializeAs(json, People.class);
		assertNotNull(people_des.getAsset());

		ICommodity c0 = people.getAsset();
		ICommodity c1 = decar;
		ICommodity c2 = people_des.getAsset();

		assertEquals(c0.getCost(), c1.getCost());
		assertTrue(c2 instanceof ICar);
		assertEquals(c0.getCost(), c2.getCost());

		// 采用原生的 JSON 对象针对接口类型的序列化和反序列化；
		// 验证是否与 JSONSerializeUtils 得到一致的结果，验证 JSONSerializeUtils 对原生 fastJSON 的无入侵性；
		json = JSON.toJSONString(people, true);
		System.out.println("----------- people -----------");
		System.out.println(json);

		people_des = JSON.parseObject(json, People.class);
		assertNotNull(people_des.getAsset());

		c0 = people.getAsset();
		c1 = decar;
		c2 = people_des.getAsset();

		assertEquals(c0.getCost(), c1.getCost());
		assertTrue(c2 instanceof ICar);
		assertEquals(c0.getCost(), c2.getCost());

	}

	@Test
	public void tempTest() {
		Car car = new Car();
		car.setCost(10);
		car.setWeight(1000);

		String name = "john";
		boolean enable = true;

		Object[] dataArrays = new Object[] { name, car, enable, null, new Date() };
		String json = JSON.toJSONString(dataArrays);
		System.out.println("--------- json ---------");
		System.out.println(json);

		System.out.println("--------- deserialize ---------");
		Object deObj = JSON.parse(json);
		assertTrue(deObj instanceof JSONArray);
		System.out.println("deObj.type=" + deObj.getClass().getName());
		JSONArray jsonArray = (JSONArray) deObj;
		assertEquals(5, jsonArray.size());

		assertTrue(jsonArray.get(0) instanceof String);
		assertTrue(jsonArray.get(1) instanceof JSONObject);
		assertTrue(jsonArray.get(2) instanceof Boolean);
		assertNull(jsonArray.get(3));
		assertTrue(jsonArray.get(4) instanceof Long);
		System.out.println("[0]--" + jsonArray.get(0).getClass().getName());
		System.out.println("[1]--" + jsonArray.get(1).getClass().getName());
		System.out.println("[2]--" + jsonArray.get(2).getClass().getName());
		System.out.println("[3]--" + jsonArray.get(3));
		System.out.println("[4]--" + jsonArray.get(4).getClass().getName());

		System.out.println("--------- get specified type --------");
		assertTrue(jsonArray.getObject(1, Car.class) instanceof Car);
		assertTrue(jsonArray.getDate(4) instanceof Date);
		assertTrue(jsonArray.getObject(4, Date.class) instanceof Date);
		System.out.println("[1]--" + jsonArray.getObject(1, Car.class).getClass().getName());
		System.out.println("[4]--" + jsonArray.getObject(4, Date.class).getClass().getName());
	}

	@Test
	public void testSerialize() {
		Car car = new Car();
		car.setCost(10000);
		car.setWeight(600);

		String jsonCar = JSONSerializeUtils.serializeToJSON(car);

		Entity<Car> entity = new Entity<Car>();
		entity.setName("test-entity");
		entity.setValue(80);
		entity.setData(jsonCar);
		entity.setHeader(car);
		entity.setLevel(Level.LOW);

		String jsonEntity = JSONSerializeUtils.serializeToJSON(entity);
		GenericType<Entity<Car>> type = new GenericType<Entity<Car>>() {
		};
		Entity<Car> deEntity = JSONSerializeUtils.deserializeFromJSON(jsonEntity, type);
		assertEquals(entity.getName(), deEntity.getName());
		assertEquals(entity.getValue(), deEntity.getValue());
		assertEquals(entity.getData(), deEntity.getData());
		assertEquals(entity.getLevel(), deEntity.getLevel());
		assertSame(entity.getLevel(), deEntity.getLevel());
		assertNotNull(deEntity.getHeader());
		assertTrue(deEntity.getHeader() instanceof Car);
		assertEquals(car.getCost(), deEntity.getHeader().getCost());

		testGereric(entity, jsonEntity);

		Entity<?> deEntity2 = JSONSerializeUtils.deserializeFromJSON(jsonEntity, Entity.class);
		assertNotNull(deEntity2);
		assertTrue(deEntity2.getHeader() instanceof JSONObject);
		test(deEntity2, JSONObject.class);

		Entity<String> entityStr = new Entity<String>();
		entityStr.setName("test-entity");
		entityStr.setValue(80);
		entityStr.setData("StringCarData");
		entityStr.setHeader("StringCar");
		entityStr.setLevel(Level.LOW);
		String jsonEntityStr = JSONSerializeUtils.serializeToJSON(entityStr);

		Entity<?> deEntity3 = JSONSerializeUtils.deserializeFromJSON(jsonEntityStr, Entity.class);
		assertNotNull(deEntity3);
		assertTrue(deEntity3.getHeader() instanceof String);

		test(deEntity3, String.class);
	}

	private <T> void test(Entity<T> entity, Class<?> type) {
		assertNotNull(entity);
		assertNotNull(entity.getHeader());
		assertTrue(type.isAssignableFrom(entity.getHeader().getClass()));
	}

	@SuppressWarnings("unchecked")
	private <T> void testGereric(Entity<T> expectedEntity, String jsonEntity) {
		Entity<T> deEntity = JSONSerializeUtils.deserializeFromJSON(jsonEntity, Entity.class);
		assertEquals(expectedEntity.getName(), deEntity.getName());
		assertEquals(expectedEntity.getValue(), deEntity.getValue());
		assertEquals(expectedEntity.getData(), deEntity.getData());
		assertEquals(expectedEntity.getLevel(), deEntity.getLevel());
		assertSame(expectedEntity.getLevel(), deEntity.getLevel());
		assertNotNull(deEntity.getHeader());
		assertTrue(deEntity.getHeader() instanceof JSONObject);
	}

	@Test
	public void testJSONArray() {
		Car[] cars = new Car[2];

		cars[0] = new Car();
		cars[0].setCost(10);
		cars[0].setWeight(1000);

		cars[1] = new Car();
		cars[1].setCost(10);
		cars[1].setWeight(1000);

		String jsonString = JSONSerializeUtils.serializeToJSON(cars);
		Object json = JSON.parse(jsonString);
		assertTrue(json instanceof JSONArray);

		Car[] deCars = JSONSerializeUtils.deserializeAs(json, Car[].class);
		assertNotNull(deCars);
		assertEquals(cars.length, deCars.length);
		for (int i = 0; i < deCars.length; i++) {
			assertNotNull(cars[i]);
			assertEquals(cars[i].getCost(), deCars[i].getCost());
			assertEquals(cars[i].getWeight(), deCars[i].getWeight());
		}
	}

	@Test
	public void testSerializeString() {
		String origString = "test string";
		String json = JSONSerializeUtils.serializeToJSON(origString, String.class);

		String desString = JSON.parseObject(json, String.class);
		assertEquals(origString, desString);

		String desString1 = JSONSerializeUtils.deserializeAs(json, String.class);
		assertEquals(origString, desString1);

		String desString2 = JSONSerializeUtils.deserializeAs(origString, String.class);
		assertEquals(origString, desString2);

		Car car = new Car();
		car.setCost(10);
		car.setWeight(1000);

		String carJSON = JSONSerializeUtils.serializeToJSON(car);
		String carJSON1 = JSON.toJSONString(car, false);
		assertEquals(carJSON, carJSON1);

		String carString = JSON.parseObject(carJSON, String.class);
		assertJsonEqualsNonStrict(carJSON, carString);
		String carString2 = JSONSerializeUtils.deserializeAs(carJSON, String.class);
		assertJsonEqualsNonStrict(carJSON, carString2);
	}

	@Test
	public void testJSONString() {
		Car car = new Car();
		car.setCost(10);
		car.setWeight(1000);

		String carJSON = JSONSerializeUtils.serializeToJSON(car);
		JSONString jsonString = new JSONString(carJSON);
		System.out.println("1:--\r\n" + carJSON);

		String newJSONString = JSONSerializeUtils.serializeToJSON(jsonString);
		assertEquals(carJSON, newJSONString);
		System.out.println("2:--\r\n" + newJSONString);

		JSONString newJSONString2 = JSONSerializeUtils.deserializeAs(newJSONString, JSONString.class);
		assertJsonEqualsNonStrict(carJSON, newJSONString2.toString());

		String address = UUID.randomUUID().toString();
		JSONString jsonAddress = new JSONString(JSONSerializeUtils.serializeToJSON(address));
		String desAddress = JSONSerializeUtils.deserializeAs(jsonAddress, String.class);
		assertEquals(address, desAddress);

		String emptyStr = "";
		JSONString emptyJsonStr = new JSONString(JSONSerializeUtils.serializeToJSON(emptyStr));
		String desEmptyStr = JSONSerializeUtils.deserializeAs(emptyJsonStr, String.class);
		assertEquals(emptyStr, desEmptyStr);

		String nullStr = null;
		String nullJson = JSONSerializeUtils.serializeToJSON(nullStr);
		assertNull(nullJson);
	}

}
