package com.qrfiletransfer.service

import com.qrfiletransfer.model.QRCodeFrame
import java.awt.image.BufferedImage
import java.util.concurrent.CompletableFuture

/**
 * Interface for camera operations
 * Handles camera access and QR code scanning from video
 */
interface CameraService {
    /**
     * Start the camera and begin looking for QR codes
     * @param callback Function to call when a QR code is detected
     */
    fun startCamera(callback: (QRCodeFrame) -> Unit): CompletableFuture<Boolean>

    /**
     * Stop the camera
     */
    fun stopCamera(): CompletableFuture<Boolean>

    /**
     * Get a snapshot from the camera
     */
    fun getSnapshot(): CompletableFuture<BufferedImage?>

    /**
     * Check if a camera is available
     */
    fun isCameraAvailable(): CompletableFuture<Boolean>

    /**
     * Set the desired camera resolution
     */
    fun setResolution(width: Int, height: Int): CompletableFuture<Boolean>

    /**
     * List available cameras
     */
    fun listCameras(): CompletableFuture<List<String>>

    /**
     * Select a specific camera by index or id
     */
    fun selectCamera(cameraId: String): CompletableFuture<Boolean>
}