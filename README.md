This projects contains a set of OSGi bundles that demonstrate web services in JBoss Fuse using CXF and Camel.

The following patterns are shown:

* generating Java objects from a WSDL using the `cxf-codegen-plugin` `wsdl2java` command
* a concrete CXF web service implementation
* a Camel route that dynamically acts as a web service implementation
* a Camel route that invokes a SOAP web service
* a Camel route that acts as a web service proxy, taking requests and passing them to another web service


Project layout
==============
The Maven projects contained within are as follows:

* `greeter-api` - Contains a WSDL file from which Java objects are generated at build time. 
* `ws-cxf-service` - Contains a bundle with a concrete "naked" CXF service implementation; no Camel is used here.
* `ws-camel-service` - Contains a bundle with a Camel CXF endpoint that picks up all requests from a web service and dynamically responds. No concrete implementation ins necessary here.
* `ws-camel-proxy` - Contains a bundle with a Camel route that proxies requests from the bundle's CXF endpoint to a remote instance of the service.
* `ws-camel-client` - Contains a Camel route that sends request on a round-robin basis between the services from the bundles above.

All of the above `ws-` bundles are based on Spring DM.

There are two additional parent projects that simplify the Maven project configuration:

* `ws-parent` - used as parent by the web service bundles
* `ws-examples` - Contains an XML features file used to install the rest of the bundles.

Prerequisites
=============
Set up JBoss Fuse by downloading the latest (6.3.0) version from [Red Hat](https://access.redhat.com/jbossnetwork/). The installation guide can be reached from the Documentation tab on that page.

Ensure that Maven is set up on your system. 

Installation
============
Download this project and run

	smx-ws-examples> mvn clean install

Start up Fuse

	$JBOSS_FUSE_HOME> bin/fuse console

          _ ____                  ______
         | |  _ \                |  ____|
         | | |_) | ___  ___ ___  | |__ _   _ ___  ___
     _   | |  _ < / _ \/ __/ __| |  __| | | / __|/ _ \
    | |__| | |_) | (_) \__ \__ \ | |  | |_| \__ \  __/
     \____/|____/ \___/|___/___/ |_|   \__,_|___/\___|

      JBoss Fuse (6.2.0.redhat-059)
      http://www.redhat.com/products/jbossenterprisemiddleware/fuse/

    Hit '<tab>' for a list of available commands
    and '[cmd] --help' for help on a specific command.
    Hit '<ctrl-d>' or 'osgi:shutdown' to shutdown JBoss Fuse.


Install the `smx-ws-examples` features file. This gets pulled out from your local Maven repository, and defines which bundles you mean to install for the example project.

	JBossFuse:karaf@root> features:addurl mvn:com.fusesource.examples/ws-features/1.0-SNAPSHOT/xml/features
	
Install all of the necessary OSGi bundles by installing the `smx-ws-examples` feature

	JBossFuse:karaf@root> features:install smx-ws-examples
	JBossFuse:karaf@root> list | grep Examples
	[ 225] [Active     ] [            ] [Started] [   60] ServiceMix Web Service Examples :: greeter-api (1.0.0.SNAPSHOT)
	[ 226] [Active     ] [            ] [Started] [   60] ServiceMix Web Service Examples :: ws-cxf-service (1.0.0.SNAPSHOT)
	[ 227] [Active     ] [            ] [Started] [   60] ServiceMix Web Service Examples :: ws-camel-service (1.0.0.SNAPSHOT)
	[ 228] [Active     ] [            ] [Started] [   60] ServiceMix Web Service Examples :: ws-camel-proxy (1.0.0.SNAPSHOT)
	[ 229] [Active     ] [            ] [Started] [   60] ServiceMix Web Service Examples :: ws-camel-client (1.0.0.SNAPSHOT)

Tail the logs and you should see the CXF invocations ticking over.

	JBossFuse:karaf@root> log:display

You can also verify that the web services are available by accessing the following from your browser:

* `ws-cxf-service` - http://localhost:9090/greeterImpl?wsdl 
* `ws-camel-service` - http://localhost:9090/greeter?wsdl 
* `ws-camel-proxy` - http://localhost:9091/greeterProxy?wsdl
