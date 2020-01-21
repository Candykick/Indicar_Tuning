# Begin: Unity Proguard rules

-keep class bitter.jnibridge.** { *; }
-keep class com.unity3d.player.** { *; }
-keep class org.fmod.** { *; }

# 난독화 안함
-keep class com.iindicar.indicar.UnityPlayerActivity.** { *; }
-ignorewarnings

# End: Unity Proguard rules