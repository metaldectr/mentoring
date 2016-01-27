javac CMSMain.java
jar cfm CMS.jar MANIFEST.MF CMSMain.class
java -XX:+UseConcMarkSweepGC -Xms2m -Xmx18m -Xmn1m -XX:PermSize=24m -XX:MaxPermSize=36m -XX:+PrintGCDetails -XX:+CMSIncrementalMode -jar CMS.jar