package com.juliodev.ai.spring_ai_demo;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class GenAIController {

    private final ChatService chatService;
    private final ImageService imageService;
    private final RecipeService recipeService;

    public GenAIController(ChatService chatService, ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.chatService = chatService;
        this.recipeService = recipeService;
    }

    @GetMapping("/ask-ai")
    public String getResponse(@RequestParam String prompt) {
        return this.chatService.getResponse(prompt);
    }

    @GetMapping("/ask-ai-options")
    public String getResponseWithOptions(@RequestParam String prompt){
        return this.chatService.getResponseOptions(prompt);
    }

    @GetMapping("/generate-image")
    public List<String> generateImages(HttpServletResponse response,
                                       @RequestParam String prompt,
                                       @RequestParam(defaultValue="hd")String quality,
                                       @RequestParam(defaultValue="1")int n,
                                       @RequestParam(defaultValue="500")int width,
                                       @RequestParam(defaultValue="500")int height
                               ) throws IOException {
        ImageResponse imageResponse = this.imageService.generateImage(prompt, quality, n, width, height);
        return imageResponse.getResults()
                .stream().map(result -> result.getOutput().getUrl()).toList();
    }

    @GetMapping("/recipe-creator")
    public String recipeCreator(@RequestParam String ingredients,
                                @RequestParam(defaultValue = "any") String cuisine,
                                @RequestParam(defaultValue = "") String dietaryRestriction){
        return this.recipeService.createRecipe(ingredients, cuisine, dietaryRestriction);
    }
}
