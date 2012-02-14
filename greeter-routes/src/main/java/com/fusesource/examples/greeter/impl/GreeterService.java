package com.fusesource.examples.greeter.impl;

import org.slf4j.*;
import com.fusesource.examples.greeter.Greeter;
import com.fusesource.examples.greeter.PingMeFault;
import com.fusesource.examples.greeter.model.FaultDetail;

public class GreeterService implements Greeter {
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