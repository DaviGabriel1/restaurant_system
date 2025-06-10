package com.davi.restaurant_burguer.interfaces;

public interface IStorageServiceAdapter {
    String uploadFile(byte[] file, String fileName, String contentType);
}
