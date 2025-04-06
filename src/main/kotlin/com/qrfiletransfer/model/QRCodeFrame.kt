package com.qrfiletransfer.model

/**
 * QR code representation with additional metadata
 * Encapsulates the type of frame and its binary payload
 */
data class QRCodeFrame(
    val frameType: FrameType,
    val payload: ByteArray,
    val timestamp: Long = System.currentTimeMillis(),
    val frameId: Int = 0, // For identifying specific frames in a sequence
    val retryCount: Int = 0 // For tracking retransmissions
) {
    enum class FrameType {
        FILE_HEADER,    // Contains file metadata
        DATA_CHUNK,     // Contains file data
        END_OF_FILE,    // Signals end of transmission
        REQUEST_CHUNK,  // Request for specific chunks (used by receiver)
        ACK             // Acknowledgment of received chunks
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as QRCodeFrame

        if (frameType != other.frameType) return false
        if (!payload.contentEquals(other.payload)) return false
        if (frameId != other.frameId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = frameType.hashCode()
        result = 31 * result + payload.contentHashCode()
        result = 31 * result + frameId
        return result
    }
}