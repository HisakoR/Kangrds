package gui;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class mediaPlayer {
    //播放音乐的方法
    public static void playTarget(String pathToMus){
        try {
            // 指定音频文件的路径
            File audioFile = new File(pathToMus);
            System.out.println("播放音频：" + pathToMus);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
            // 获取音频格式
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public static void volumeController(){

    }
}
