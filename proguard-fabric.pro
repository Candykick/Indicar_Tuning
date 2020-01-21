# Begin: Fabric Proguard rules

-keepattributes *Annotation*

#in order to provide the most meaningful crash reports
-keepattributes SourceFile,LineNumberTable

#If you are using custom exceptions, add this line so that custom exception types are skipped during obfuscation
-keep public class * extends java.lang.Exception

#If using DexGuard, also add the following line to your configuration file to keep DexGuard from removing your Fabric API key
#-keepresourcexmlelements manifest/application/meta-data@name=io.fabric.ApiKey

-keep class io.fabric.** { *; }
-keep interface io.fabric.** { *; }
-dontwarn io.fabric.**

-keep class com.crashlytics.** { *; }
-keep interface com.crashlytics.** { *; }
-dontwarn com.crashlytics.**

# End: Fabric Proguard rules
