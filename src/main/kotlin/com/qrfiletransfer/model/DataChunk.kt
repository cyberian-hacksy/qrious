package com.qrfiletransfer.model

import java.io.Serializable

/**
 * A single chunk of the file data
 * Contains the actual binary data and metadata for this chunk
 */
data class DataChunk(
    val chunkId: Int,
    val totalChunks: Int,
    val data: ByteArray,
    val checksum: String,
    val errorCorrectionData: ByteArray? = null,
    val priority: Int = 0 // For implementing prioritized chunks
) : Serializable {

    /**
     * Returns the total size of this chunk in bytes (data + error correction)
     */
    val size: Int
        get() = data.size + (errorCorrectionData?.size ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DataChunk

        if (chunkId != other.chunkId) return false
        if (totalChunks != other.totalChunks) return false
        if (!data.contentEquals(other.data)) return false
        if (checksum != other.checksum) return false
        if (errorCorrectionData != null) {
            if (other.errorCorrectionData == null) return false
            if (!errorCorrectionData.contentEquals(other.errorCorrectionData)) return false
        } else if (other.errorCorrectionData != null) return false
        if (priority != other.priority) return false

        return true
    }

    override fun hashCode(): Int {
        var result = chunkId
        result = 31 * result + totalChunks
        result = 31 * result + data.contentHashCode()
        result = 31 * result + checksum.hashCode()
        result = 31 * result + (errorCorrectionData?.contentHashCode() ?: 0)
        result = 31 * result + priority
        return result
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}