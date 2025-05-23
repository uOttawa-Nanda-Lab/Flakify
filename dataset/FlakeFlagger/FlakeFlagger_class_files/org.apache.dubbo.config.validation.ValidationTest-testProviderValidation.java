/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.dubbo.config.validation;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.service.GenericException;
import org.apache.dubbo.rpc.service.GenericService;

import org.junit.Assert;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * GenericServiceTest
 */
public class ValidationTest {

    @Test public void testProviderValidation(){ServiceConfig<ValidationService> service=new ServiceConfig<ValidationService>();service.setApplication(new ApplicationConfig("validation-provider"));service.setRegistry(new RegistryConfig("N/A"));service.setProtocol(new ProtocolConfig("dubbo",29582));service.setInterface(ValidationService.class.getName());service.setRef(new ValidationServiceImpl());service.setValidation(String.valueOf(true));service.export();try {ReferenceConfig<ValidationService> reference=new ReferenceConfig<ValidationService>();reference.setApplication(new ApplicationConfig("validation-consumer"));reference.setInterface(ValidationService.class);reference.setUrl("dubbo://127.0.0.1:29582");ValidationService validationService=reference.get();try {ValidationParameter parameter=new ValidationParameter();parameter.setName("liangfei");parameter.setEmail("liangfei@liang.fei");parameter.setAge(50);parameter.setLoginDate(new Date(System.currentTimeMillis() - 1000000));parameter.setExpiryDate(new Date(System.currentTimeMillis() + 1000000));validationService.save(parameter);try {parameter=new ValidationParameter();validationService.save(parameter);Assert.fail();} catch (RpcException e){Assert.assertTrue(e.getMessage().contains("ConstraintViolation"));}validationService.delete(2,"abc");try {validationService.delete(0,"abc");Assert.fail();} catch (RpcException e){Assert.assertTrue(e.getMessage().contains("ConstraintViolation"));}try {validationService.delete(2,null);Assert.fail();} catch (RpcException e){Assert.assertTrue(e.getMessage().contains("ConstraintViolation"));}try {validationService.delete(0,null);Assert.fail();} catch (RpcException e){Assert.assertTrue(e.getMessage().contains("ConstraintViolation"));}}  finally {reference.destroy();}}  finally {service.unexport();}}

}
