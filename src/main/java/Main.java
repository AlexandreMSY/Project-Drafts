import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderException;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;
import ws.schild.jave.encode.VideoAttributes;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.Scanner;

public class Main {
    public static void convertToMkv(String sourcePath, String targetPath, String name) throws EncoderException {
        File source = new File(sourcePath);
        File target = new File(targetPath + "\\" + name + ".mkv");

        AudioAttributes audio = new AudioAttributes();

        VideoAttributes video = new VideoAttributes();
        video.setCodec("h264");
        video.setBitRate(42000);
        video.setFrameRate(25);

        EncodingAttributes attributes = new EncodingAttributes();
        attributes.setOutputFormat("matroska");
        attributes.setAudioAttributes(audio);
        attributes.setVideoAttributes(video);

        Encoder encoder = new Encoder();
        encoder.encode(new MultimediaObject(source), target, attributes);
    }
    public static void main(String[] args) throws EncoderException {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Video Files",
                "mp4", "flv", "avi");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(filter);

        JFileChooser saveChooser = new JFileChooser();
        saveChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int sourceValue = fileChooser.showOpenDialog(null);
        int targetValue = saveChooser.showSaveDialog(null);

        if (sourceValue == JFileChooser.APPROVE_OPTION || targetValue == JFileChooser.APPROVE_OPTION){
            File source = fileChooser.getSelectedFile();
            File target = saveChooser.getSelectedFile();

            String sourcePath = source.getAbsolutePath();
            String targetPath = target.getAbsolutePath();
            String newFileName = JOptionPane.showInputDialog(null,"Set the file name");

            System.out.println(sourcePath);
            System.out.println(targetPath);

            convertToMkv(sourcePath, targetPath, newFileName);
        }
    }
}
