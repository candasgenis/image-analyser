package com.candasgenis.imageanalyser.controller;

import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.FaceAnnotation;
import com.google.cloud.vision.v1.Feature.Type;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.cloud.spring.vision.CloudVisionTemplate;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Code sample demonstrating Cloud Vision usage within the context of Spring Framework using Spring
 * Cloud GCP libraries. The sample is written as a Spring Boot application to demonstrate a
 * practical application of this usage.
 */
@RestController
public class VisionController {

    @Autowired private ResourceLoader resourceLoader;

    @Autowired private CloudVisionTemplate cloudVisionTemplate;

    /**
     * This method downloads an image from a URL and sends its contents to the Vision API for label
     * detection.
     *
     * @param imageUrl the URL of the image
     * @param map the model map to use
     * @return a string with the list of labels and percentage of certainty
     * @throws com.google.cloud.spring.vision.CloudVisionException if the Vision API call produces an
     *     error
     */
    @GetMapping("/extractLabels")
    public ModelAndView extractLabels(String imageUrl, ModelMap map) {
        AnnotateImageResponse response =
                this.cloudVisionTemplate.analyzeImage(
                        this.resourceLoader.getResource(imageUrl), Type.FACE_DETECTION);

        // This gets the annotations of the image from the response object.
        List<FaceAnnotation> annotations = response.getFaceAnnotationsList();
        map.addAttribute("annotations", annotations);
        map.addAttribute("imageUrl", imageUrl);

        return new ModelAndView("result", map);
    }

    @GetMapping("/extractText")
    public ModelAndView extractText(String imageUrl, ModelMap map) {
        String text =
                this.cloudVisionTemplate.extractTextFromImage(this.resourceLoader.getResource(imageUrl));

        map.addAttribute("text", text);
        map.addAttribute("imageUrl", imageUrl);

        return new ModelAndView("result", map);
    }

    @GetMapping("/extractTextFromPdf")
    public ModelAndView extractTextFromPdf(String pdfUrl, ModelMap map) {
        List<String> texts =
                this.cloudVisionTemplate.extractTextFromPdf(this.resourceLoader.getResource(pdfUrl));

        map.addAttribute("texts", texts);
        map.addAttribute("pdfUrl", pdfUrl);

        return new ModelAndView("result_pdf", map);
    }

//    @Autowired private ResourceLoader resourceLoader;
//
//    // [START vision_spring_autowire]
//    @Autowired private CloudVisionTemplate cloudVisionTemplate;
//    // [END vision_spring_autowire]
//
//    /**
//     * This method downloads an image from a URL and sends its contents to the Vision API for label
//     * detection.
//     *
//     * @param imageUrl the URL of the image
//     * @param map the model map to use
//     * @return a string with the list of labels and percentage of certainty
//     */
//    @GetMapping("/extractLabels")
//    public void extractLabels(String imageUrl, ModelMap map) {
//        String imagePath = "C:/Users/canda/Desktop/stop-sign.jpg";
//        Resource imageResource = this.resourceLoader.getResource(imagePath);
//        // [START vision_spring_image_labelling]
//        AnnotateImageResponse response =
//                this.cloudVisionTemplate.analyzeImage(
//                        this.resourceLoader.getResource(imagePath), Type.FACE_DETECTION);
////        Map<String, Float> imageFaces =
////                response.getFaceAnnotationsList().stream()
////                        .collect(
////                                Collectors.toMap(
////                                        EntityAnnotation::getDescription,
////                                        EntityAnnotation::getScore,
////                                        (u, v) -> {
////                                            throw new IllegalStateException(String.format("Duplicate key %s", u));
////                                        },
////                                        LinkedHashMap::new));
//        // [END vision_spring_image_labelling]
//
////        map.addAttribute("annotations", imageFaces);
////        map.addAttribute("imageUrl", imageUrl);
//        System.out.println("Image Classification results: " + response.getFaceAnnotationsList());
////        Map<String, Float> imageLabels =
////                response.getLabelAnnotationsList().stream()
////                        .collect(
////                                Collectors.toMap(
////                                        EntityAnnotation::getDescription,
////                                        EntityAnnotation::getScore,
////                                        (u, v) -> {
////                                            throw new IllegalStateException(String.format("Duplicate key %s", u));
////                                        },
////                                        LinkedHashMap::new));
////        // [END vision_spring_image_labelling]
////
////        map.addAttribute("annotations", imageLabels);
////        map.addAttribute("imageUrl", imagePath);
////
////        return new ModelAndView("result", map);
//    }
//
//    @GetMapping("/extractText")
//    public String extractText(String imageUrl) {
//        // [START vision_spring_text_extraction]
//        String textFromImage =
//                this.cloudVisionTemplate.extractTextFromImage(this.resourceLoader.getResource(imageUrl));
//        return "Text from image: " + textFromImage;
//        // [END vision_spring_text_extraction]
//    }
}
