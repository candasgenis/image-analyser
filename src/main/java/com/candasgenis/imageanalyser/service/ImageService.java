package com.candasgenis.imageanalyser.service;

import com.candasgenis.springbootkafkaproducer.service.ProducerService;
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

    @Autowired private ProducerService service;

    @Override
    public void getImageDetail(GetImageDetailRequest request, StreamObserver<GetImageDetailResponse> responseObserver) {
        //TODO DATABASE E ISTEK ATIP IMAGE DETAIL ALMA
    }
    @Override
    public void uploadImage(UploadImageRequest request, StreamObserver<UploadImageResponse> responseObserver) {
        //TODO BURAYI KAFKAYLA YAP, BURADA SADECE KAFKA SERVICE E URL I GONDER SONRA KAFKA SERVICE
        // ICERIDE IMAGE I ANALIZ ETSIN VE BURAYA BEN BU IMAGE I ANALIZ ETTIM RESPONSE U DONSUN
        // O SIRADA KAFKA IMAGE I ANALIZ EDIP DATABASE E VERIYI YAZSIN, SONRA DA ZATEN
        // GETIMAGEFEED ILE DATABASEDEKI VERI CEKILSIN
        // KAFKA SERVICE E DIREKT BU ANALYZEIMAGE KODUNUN AYNISINI YAZ FARK ETMEZ

        System.out.println("Kafka log send starting...");
        for (int i = 0; i < 10; i++) {
            service.sendMessage(String.format("[%d] -- Hello World", i));
        }
        System.out.println("Kafka log sent...");
//
//        AnnotateImageResponse response =
//                this.cloudVisionTemplate.analyzeImage(
//                        this.resourceLoader.getResource(request.getImageUrl()), Feature.Type.FACE_DETECTION);
//
//        // This gets the annotations of the image from the response object.
//        List<FaceAnnotation> annotations = response.getFaceAnnotationsList();
//        int joyLikelihoodValue = annotations.getFirst().getJoyLikelihoodValue();
//        int sorrowLikelihoodValue = annotations.getFirst().getSorrowLikelihoodValue();
//        int angerLikelihoodValue = annotations.getFirst().getAngerLikelihoodValue();
//        int surpriseLikelihoodValue = annotations.getFirst().getSurpriseLikelihoodValue();
//        int underExposedLikelihoodValue = annotations.getFirst().getUnderExposedLikelihoodValue();
//        int blurredLikelihoodValue = annotations.getFirst().getBlurredLikelihoodValue();
//        int headwearLikelihoodValue = annotations.getFirst().getHeadwearLikelihoodValue();

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
