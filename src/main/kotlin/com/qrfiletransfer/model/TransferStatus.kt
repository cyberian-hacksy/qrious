package com.qrfiletransfer.model

/**
 * Represents the status of chunks during reception
 * Used to track progress and identify missing chunks
 */
data class TransferStatus(
    val fileMetadata: FileMetadata,
    val receivedChunks: MutableMap<Int, DataChunk> = mutableMapOf(),
    val missingChunks: MutableSet<Int> = mutableSetOf(),
    val startTime: Long = System.currentTimeMillis(),
    val errors: MutableList<String> = mutableListOf()
) {
    /**
     * True if all chunks have been received
     */
    val isComplete: Boolean
        get() = receivedChunks.size == fileMetadata.totalChunks

    /**
     * Progress percentage (0-100)
     */
    val progressPercentage: Double
        get() = (receivedChunks.size.toDouble() / fileMetadata.totalChunks) * 100

    /**
     * Estimated time remaining in milliseconds based on the current transfer rate
     */
    val estimatedTimeRemaining: Long
        get() {
            val elapsedTime = System.currentTimeMillis() - startTime
            if (receivedChunks.isEmpty() || elapsedTime <= 0) return -1

            val chunkRate = receivedChunks.size.toDouble() / elapsedTime
            val chunksRemaining = fileMetadata.totalChunks - receivedChunks.size

            return (chunksRemaining / chunkRate).toLong()
        }

    /**
     * Current transfer rate in chunks per second
     */
    val transferRate: Double
        get() {
            val elapsedTime = System.currentTimeMillis() - startTime
            if (elapsedTime <= 0) return 0.0
            return receivedChunks.size.toDouble() / (elapsedTime / 1000.0)
        }

    /**
     * Add an error message to the transfer status
     */
    fun addError(error: String) {
        errors.add(error)
    }

    /**
     * Returns the most critical missing chunks to prioritize
     */
    fun getCriticalMissingChunks(count: Int): List<Int> {
        return missingChunks.take(count)
    }
}