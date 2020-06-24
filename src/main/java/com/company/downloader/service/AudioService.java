package com.company.downloader.service;

import com.company.downloader.dto.formatDTO.AudioFormatDTO;
import com.github.kiulian.downloader.YoutubeDownloader;
import com.github.kiulian.downloader.YoutubeException;
import com.github.kiulian.downloader.model.YoutubeVideo;
import com.github.kiulian.downloader.model.formats.AudioFormat;
import com.github.kiulian.downloader.model.formats.AudioVideoFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AudioService {
    final YoutubeDownloader youtubeDownloader;
    @Value("${app.musicDownload.dir}")
    private String audioDownloadDir;

    public AudioService(YoutubeDownloader youtubeDownloader) {
        this.youtubeDownloader = youtubeDownloader;
    }

    public List<AudioFormatDTO> getAudioFormats(String id) throws IOException, YoutubeException {
        YoutubeVideo video=youtubeDownloader.getVideo(id);
        List<AudioFormat>audioFormats=video.audioFormats();
        List<AudioFormatDTO> formats=new ArrayList<>();
        int index=0;
        for (AudioFormat f:audioFormats){
            AudioFormatDTO audioFormatDTO=new AudioFormatDTO();
            audioFormatDTO.setExtension(f.extension().value());
            audioFormatDTO.setQuality(f.audioQuality().name());
            audioFormatDTO.setBitrate(f.bitrate().toString());
            audioFormatDTO.setListIndex(String.valueOf(index));
            formats.add(audioFormatDTO);
            index++;
        }

        return formats;

    }

    public void download(String id, String listIndex) throws IOException, YoutubeException {
        YoutubeVideo video=youtubeDownloader.getVideo(id);
        List<AudioFormat> audioFormats=video.audioFormats();
        video.download(audioFormats.get(Integer.parseInt(listIndex)),new File(audioDownloadDir));
    }
}
