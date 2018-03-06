wget http://download.jboss.org/wildfly/9.0.1.Final/wildfly-9.0.1.Final.zip
unzip wildfly-9.0.1.Final.zip -d /opt/
Create symbolic link in order to simplify WildFly updates in future

ln -s /opt/wildfly-9.0.1.Final /opt/wildfly
Copy and edit init script configuration

cp /opt/wildfly/bin/init.d/wildfly.conf /etc/default/wildfly.conf
Edit variables in /etc/default/wildfly.conf

## Location of JDK
JAVA_HOME="/usr/lib/jvm/java-1.8.0"

## Location of WildFly
JBOSS_HOME="/opt/wildfly"

## The username who should own the process.
JBOSS_USER=wildfly

## The mode WildFly should start, standalone or domain
JBOSS_MODE=standalone

## Configuration for standalone mode
JBOSS_CONFIG=standalone-full-ha.xml

## Configuration for domain mode
# JBOSS_DOMAIN_CONFIG=domain.xml
# JBOSS_HOST_CONFIG=host-master.xml

## The amount of time to wait for startup
STARTUP_WAIT=60

## The amount of time to wait for shutdown
SHUTDOWN_WAIT=60

## Location to keep the console log
JBOSS_CONSOLE_LOG="/var/log/wildfly/console.log"

## Additionals args to include in startup
# JBOSS_OPTS="--admin-only -b 172.0.0.1"

##Copy init script
cp /opt/wildfly/bin/init.d/wildfly-init-redhat.sh /etc/init.d/wildfly

##Add WildFly as a service
chkconfig --add wildfly
chkconfig wildfly on

##Create directory for logs
mkdir -p /var/log/wildfly

##Add user to run WildFly
adduser wildfly

##Change the owner of WildFly directories
chown -R wildfly:wildfly /opt/wildfly-9.0.1.Final
chown -R wildfly:wildfly /opt/wildfly
chown -R wildfly:wildfly /var/log/wildfly

##Start WildFly
service wildfly start