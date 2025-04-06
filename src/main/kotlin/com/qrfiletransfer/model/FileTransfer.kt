package com.qrfiletransfer.model

/**
 * Represents the entire file to be transferred
 * Contains the file metadata and all chunks
 */
data class FileTransfer(
    val metadata: FileMetadata,
    val chunks: List<DataChunk>
)