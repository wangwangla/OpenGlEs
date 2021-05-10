# Audio

Auido分为音乐和音效

## 音效

1.创建SoundPool

```
/**
1.最大同时流数
2.音频流类型，如AudioManager中所述
  例如，游戏应用程序通常将使用 {@link AudioManager＃STREAM_MUSIC}。
3.采样率转换器的质量。目前没有*效果。默认使用0。
*/
public SoundPool(int maxStreams, int streamType, int srcQuality) {
    this(maxStreams,
            new AudioAttributes.Builder().setInternalLegacyStreamType(streamType).build());
    PlayerBase.deprecateStreamTypeForPlayback(streamType, "SoundPool", "SoundPool()");
}
```

记忆里有三种类型

## newSound

```java
AssetFileDescriptor descriptor = 
    			aHandle.getAssetFileDescriptor();
AndroidSound sound = 
    new AndroidSound(soundPool, manager, soundPool.load(descriptor, 1));
descriptor.close();
```



## new Music

```java
MediaPlayer mediaPlayer = new MediaPlayer();

try {
   mediaPlayer.setDataSource(fd);
   mediaPlayer.prepare();

   AndroidMusic music = new AndroidMusic(this, mediaPlayer);
   synchronized (musics) {
      musics.add(music);
   }
   return music;
} catch (Exception ex) {
   throw new GdxRuntimeException("Error loading audio from FileDescriptor", ex);
}
```

音效就是简单的调用，并没有做什么其他的特殊处理。



























## AudioRecorder

AudioRecorder 录音功能



























