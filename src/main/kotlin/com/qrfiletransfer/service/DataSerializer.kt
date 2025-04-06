package com.qrfiletransfer.service

import com.qrfiletransfer.model.DataChunk
import com.qrfiletransfer.model.FileMetadata
import com.qrfiletransfer.model.QRCodeFrame

/**
 * Interface for serialization/deserialization of data structures
 * Handles conversion between data objects and binary formats
 */
interface DataSerializer {
    /**
     * Convert file metadata to binary format
     */
    fun fileMetadataToBytes(metadata: FileMetadata): ByteArray

    /**
     * Parse binary data back into file metadata
     */
    fun bytesToFileMetadata(bytes: ByteArray): FileMetadata

    /**
     * Convert a data chunk to binary format
     */
    fun dataChunkToBytes(chunk: DataChunk): ByteArray

    /**
     * Parse binary data back into a data chunk
     */
    fun bytesToDataChunk(bytes: ByteArray): DataChunk

    /**
     * Create a QR code frame with the specified type and data
     */
    fun createQRCodeFrame(frameType: QRCodeFrame.FrameType, data: ByteArray, frameId: Int = 0): QRCodeFrame

    /**
     * Parse a QR code frame from binary data
     */
    fun bytesToQRCodeFrame(bytes: ByteArray): QRCodeFrame

    /**
     * Convert a QR code frame to binary format
     */
    fun qrCodeFrameToBytes(frame: QRCodeFrame): ByteArray
}