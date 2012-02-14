/*
 * Copyright 2012 FuseSource
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fusesource.examples.greeter.impl;

import org.slf4j.*;
import com.fusesource.examples.greeter.Greeter;
import com.fusesource.examples.greeter.PingMeFault;
import com.fusesource.examples.greeter.model.FaultDetail;

public class ConcreteGreeter implements Greeter {
  private Logger log = LoggerFactory.getLogger(this.getClass());
    
  public void greetMeOneWay(String name) {
     log.info("Executing operation greetMeOneWay\n");
     log.info(greet(name));
  }

  public String sayHi(String name) {
     log.info("Executing operation sayHi\n");
     return greet(name);
  }

  public String greetMe(String me) {
     log.info("Executing operation greetMe");
     log.info("Message received: " + me + "\n");
     return greet(me);
  }

  public void pingMe() throws PingMeFault {
     FaultDetail faultDetail = new FaultDetail();
     faultDetail.setMajor((short)2);
     faultDetail.setMinor((short)1);
     log.info("Executing operation pingMe, throwing PingMeFault exception\n");
     throw new PingMeFault("PingMeFault raised by server", faultDetail);
  }

  private String greet(String name) {
    return "Hi " + name;
  }
}