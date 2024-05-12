package com.github.fabriciolfj.ia;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class AiController {

    private final ChatService chatService;
    private final ImageService imageService;

    @GetMapping("/api/ask-ai")
    public String asAi(@RequestParam("prompt") final String prompt) {
        return chatService.queryAi(prompt);
    }

    @GetMapping("/api/city")
    public String cityGuide(@RequestParam("city") final String city,
                            @RequestParam("interest") final String interest) {
        return chatService.getCityGuide(city, interest);
    }

    @GetMapping("generate-image")
    public void generateImage(@RequestParam("prompt") String prompt,
                              HttpServletResponse response) throws IOException {
        ImageResponse imageResponse = imageService.generateImage(prompt);

        // Get URL of the generated image
        String imageUrl = imageResponse.getResult().getOutput().getUrl();

        // Send redirect to the image URL
        response.sendRedirect(imageUrl);
    }

}
