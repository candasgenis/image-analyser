package com.candasgenis.imageanalyser.service;

import com.example.*;
import com.google.cloud.spring.vision.CloudVisionTemplate;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.FaceAnnotation;
import com.google.cloud.vision.v1.Feature;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;

import java.util.List;

@GrpcService
public class ImageService extends ImageServiceGrpc.ImageServiceImplBase{
    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired private CloudVisionTemplate cloudVisionTemplate;

    @Override
    public void getImageDetail(GetImageDetailRequest request, StreamObserver<GetImageDetailResponse> responseObserver) {
        //TODO DATABASE E ISTEK ATIP IMAGE DETAIL ALMA
    }
    @Override
    public void uploadImage(UploadImageRequest request, StreamObserver<UploadImageResponse> responseObserver) {

        //TODO BURAYI KAFKAYLA YAP
        AnnotateImageResponse response =
                this.cloudVisionTemplate.analyzeImage(
                        this.resourceLoader.getResource(request.getImageUrl()), Feature.Type.FACE_DETECTION);

        // This gets the annotations of the image from the response object.
        List<FaceAnnotation> annotations = response.getFaceAnnotationsList();
        int joyLikelihoodValue = annotations.getFirst().getJoyLikelihoodValue();
        int sorrowLikelihoodValue = annotations.getFirst().getSorrowLikelihoodValue();
        int angerLikelihoodValue = annotations.getFirst().getAngerLikelihoodValue();
        int surpriseLikelihoodValue = annotations.getFirst().getSurpriseLikelihoodValue();
        int underExposedLikelihoodValue = annotations.getFirst().getUnderExposedLikelihoodValue();
        int blurredLikelihoodValue = annotations.getFirst().getBlurredLikelihoodValue();
        int headwearLikelihoodValue = annotations.getFirst().getHeadwearLikelihoodValue();

    }
    @Override
    public void getImageFeed(GetImageFeedRequest request, StreamObserver<GetImageFeedResponse> responseObserver) {
        //TODO ONCELIKLE YUKLENME TARIHI SONRA DA ANALIZDEKI DEGERLERIN ORTALAMASINA GORE SIRALAMA YAP, PAGINATION YAP

    }
    @Override
    public void updateImage(UpdateImageRequest request, StreamObserver<UpdateImageResponse> responseObserver) {
        //TODO DATABASE E GUNCELLEME GEC updated_at FIELDININ GUNCELLENMESI LAZIM

    }
}
