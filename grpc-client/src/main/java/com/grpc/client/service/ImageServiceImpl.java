package com.grpc.client.service;


//import com.example.Book;
import com.example.UploadImageRequest;
import com.example.UploadImageResponse;
import com.example.ImageServiceGrpc;
import com.google.protobuf.Descriptors;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
public class ImageServiceImpl implements ImageService {

    @GrpcClient("grpc-service")
    ImageServiceGrpc.ImageServiceBlockingStub synchronousClient;

    @GrpcClient("grpc-service")
    ImageServiceGrpc.ImageServiceStub asynchronousClient;

    public Map<Descriptors.FieldDescriptor, Object> uploadImage(String imageUrl) {
//        Author.newBuilder().setAuthorId(authorId).build();
        UploadImageRequest uploadImageRequest = UploadImageRequest.newBuilder().setImageUrl(imageUrl).build();
        UploadImageResponse uploadImageResponse = synchronousClient.uploadImage(uploadImageRequest);
        return uploadImageResponse.getAllFields();
    }

//    public CompletableFuture<List<Map<Descriptors.FieldDescriptor, Object>>> getBooksByAuthor(int authorId) throws InterruptedException {
//        CompletableFuture <List<Map<Descriptors.FieldDescriptor, Object>>> completableFuture = new CompletableFuture<>();
//        final Author authorRequest = Author.newBuilder().setAuthorId(authorId).build();
//        final List<Map<Descriptors.FieldDescriptor, Object>> response = new ArrayList<>();
//        asynchronousClient.getBooksByAuthor(authorRequest, new StreamObserver<Book>() {
//            @Override
//            public void onNext(Book book) {
//                response.add(book.getAllFields());
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//                completableFuture.completeExceptionally(throwable);
//            }
//
//            @Override
//            public void onCompleted() {
//                completableFuture.complete(response);
//            }
//        });
//        return completableFuture;
//    }

}
