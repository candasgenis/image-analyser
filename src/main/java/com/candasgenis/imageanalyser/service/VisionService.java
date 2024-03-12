package com.candasgenis.imageanalyser.service;

import com.candasgenis.imageanalyser.model.Image;
import com.candasgenis.imageanalyser.model.ImageFeed;

public interface VisionService {
    void UploadImage(Image image);
    Image GetImageDetail(long imageId);
    ImageFeed GetImageFeed();
    void UpdateImageDetail(Image image);
}
