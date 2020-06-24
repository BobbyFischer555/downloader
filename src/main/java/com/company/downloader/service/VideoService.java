package com.company.downloader.service;

import com.company.downloader.dto.VideoDetails;
import com.company.downloader.dto.formatDTO.VideoFormatDTO;
import com.github.kiulian.downloader.YoutubeDownloader;
import com.github.kiulian.downloader.YoutubeException;
import com.github.kiulian.downloader.model.YoutubeVideo;
import com.github.kiulian.downloader.model.formats.AudioVideoFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class VideoService {
    final YoutubeDownloader youtubeDownloader;
    @Value("${app.videoDownload.dir}")
    private String videoDownloadDir;




    public VideoService(YoutubeDownloader youtubeDownloader) {
        this.youtubeDownloader = youtubeDownloader;
    }


     public VideoDetails getCommonVideoDetails(String uniqueId) throws IOException, YoutubeException {
         YoutubeVideo video=youtubeDownloader.getVideo(uniqueId);
         com.github.kiulian.downloader.model.VideoDetails details=video.details();
         VideoDetails videoDetails =new VideoDetails();
         videoDetails.setAuthor(details.author());
         videoDetails.setTitle(details.title());
         int seconds=details.lengthSeconds()%60;
         int minutes=details.lengthSeconds()/60;
         videoDetails.setLengt(minutes+" minutes  "+seconds+" seconds");
         videoDetails.setId(uniqueId);
         return videoDetails;
     }

     public void download(String id,String listIndex) throws IOException, YoutubeException {
         YoutubeVideo video=youtubeDownloader.getVideo(id);
         List<AudioVideoFormat> audioVideoFormats=video.videoWithAudioFormats();
         video.download(audioVideoFormats.get(Integer.parseInt(listIndex)),new File(videoDownloadDir));

     }

     public List<VideoFormatDTO> getVideoFormats(String id) throws IOException, YoutubeException {
         YoutubeVideo video=youtubeDownloader.getVideo(id);
         List<AudioVideoFormat>audioVideoFormats=video.videoWithAudioFormats();
         List<VideoFormatDTO>formats=new ArrayList<>();
         int index=0;
         for (AudioVideoFormat f:audioVideoFormats){
             VideoFormatDTO videoFormatDTO=new VideoFormatDTO();
             videoFormatDTO.setExtension(f.extension().value());
             videoFormatDTO.setBitrate(f.bitrate().toString());
             videoFormatDTO.setResolution(f.qualityLabel());
             videoFormatDTO.setQuality(f.videoQuality().name());
             videoFormatDTO.setListIndex(String.valueOf(index));
             formats.add(videoFormatDTO);
             index++;
         }
         return formats;
    }
}
