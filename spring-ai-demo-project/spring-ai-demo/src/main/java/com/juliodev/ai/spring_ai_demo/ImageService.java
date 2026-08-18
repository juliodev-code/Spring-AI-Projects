package com.juliodev.ai.spring_ai_demo;

import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    private final OpenAiImageModel openAIImageModel;

    public ImageService(OpenAiImageModel openAIImageModel) {
        this.openAIImageModel = openAIImageModel;
    }

    public ImageResponse generateImage(String prompt,
                                       String quality,
                                       int n,
                                       int width,
                                       int height) {

        return openAIImageModel.call(
                new ImagePrompt(prompt,
                        OpenAiImageOptions.builder()
                                .model("dall-e-3")
                                .quality(quality)
                                .n(n)
                                .height(height)
                                .width(width).build())
        );
    }
}
