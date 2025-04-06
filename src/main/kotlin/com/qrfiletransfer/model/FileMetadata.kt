package com.qrfiletransfer.model

import java.io.Serializable

/**
 * Metadata about the file being transferred
 * Contains information needed to reconstruct the file from chunks
 */
data class FileMetadata(
    val filename: String,
    val fileSize: Long,
    val totalChunks: Int,
    val fileChecksum: String,
    val contentType: String,
    val protocolVersion: String = "1.0",
    val creationTimestamp: Long = System.currentTimeMillis(),
    val errorCorrectionType: String = "REED_SOLOMON", // Type of error correction used
    val compressionType: String? = null // Optional compression algorithm used
) : Serializable {
    companion object {
        private const val serialVersionUID = 1L
    }
}