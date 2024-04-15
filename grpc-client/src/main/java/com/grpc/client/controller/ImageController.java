package com.grpc.client.controller;

import com.grpc.client.service.ImageServiceImpl;
import com.google.protobuf.Descriptors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ImageController {

    private final ImageServiceImpl imageServiceImpl;

    public ImageController(ImageServiceImpl bookAuthorServiceImpl) {
        this.imageServiceImpl = bookAuthorServiceImpl;
    }

    @GetMapping("/author/{id}")
    public Map<Descriptors.FieldDescriptor, Object> getAuthor(@PathVariable String id) {
        return imageServiceImpl.uploadImage(id);
    }

//    @GetMapping("/book/{authorId}")
//    public CompletableFuture<List<Map<Descriptors.FieldDescriptor, Object>>> getBookByAuthor(@PathVariable String authorId) throws InterruptedException {
//        return imageServiceImpl.getBooksByAuthor(Integer.parseInt(authorId));
//    }
}
