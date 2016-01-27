javac G1Main.java
jar cfm G1.jar MANIFEST.MF G1Main.class
java -XX:+UseG1GC -Xms4m -Xmx16m -Xmn2m  -XX:PermSize=12m -XX:MaxPermSize=18m -XX:+PrintGCDetails -jar G1.jar