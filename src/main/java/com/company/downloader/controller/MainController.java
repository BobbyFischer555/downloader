package com.company.downloader.controller;

import com.company.downloader.dto.VideoDetails;
import com.company.downloader.dto.formatDTO.AudioFormatDTO;
import com.company.downloader.dto.formatDTO.VideoFormatDTO;
import com.company.downloader.service.AudioService;
import com.company.downloader.service.VideoService;
import com.github.kiulian.downloader.YoutubeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@Controller
public class MainController {

    @Autowired
    private VideoService videoService;
    @Autowired
    private AudioService audioService;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String getIndex() {
        return "main";
    }

    @RequestMapping(value = "/searchResult", method = RequestMethod.POST)
    public String getResult(@RequestParam String uniqueId, Model model, @RequestParam String type) {
        if (uniqueId != null && !uniqueId.equals("")) {

            if (type.equals("video")) {
                try {
                    VideoDetails details = videoService.getCommonVideoDetails(uniqueId);
                    List<VideoFormatDTO> formats = videoService.getVideoFormats(uniqueId);
                    model.addAttribute("details", details);
                    model.addAttribute("videoFormats", formats);
                    return "result";
                } catch (IOException | YoutubeException e) {
                    e.printStackTrace();
                    model.addAttribute("error", "wrong something");
                    return "videoSearch";
                }
            } else {
                try {
                    VideoDetails details = videoService.getCommonVideoDetails(uniqueId);
                    List<AudioFormatDTO> formats = audioService.getAudioFormats(uniqueId);
                    model.addAttribute("details", details);
                    model.addAttribute("audioFormats", formats);
                    return "result";
                } catch (IOException | YoutubeException e) {
                    e.printStackTrace();
                    model.addAttribute("error", "wrong something");
                    return "audioSearch";
                }

            }
        } else {
            model.addAttribute("empty", "input search is empty");
            return "result";
        }

    }

    @RequestMapping(value = "downloadVideo", method = RequestMethod.POST)
    public String downloadVideo(@RequestParam String id, Model model, @RequestParam String listIndex) {
        try {
            videoService.download(id, listIndex);
            model.addAttribute("success", "video download completed successfully!!! please check output directory");
            return "videoSearch";
        } catch (IOException | YoutubeException e) {
            e.printStackTrace();
            model.addAttribute("error", "error downloading");
            return "videoSearch";
        }

    }

    @RequestMapping(value = "downloadAudio", method = RequestMethod.POST)
    public String downloadAudio(@RequestParam String id, Model model, @RequestParam String listIndex) {
        try {
            audioService.download(id, listIndex);
            model.addAttribute("success", "audio download completed successfully!!! please check output directory");
            return "audioSearch";
        } catch (IOException | YoutubeException e) {
            e.printStackTrace();
            model.addAttribute("error", "error downloading");
            return "audioSearch";
        }

    }
}
